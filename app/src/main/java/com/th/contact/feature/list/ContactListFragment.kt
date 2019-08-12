package com.th.contact.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.th.contact.R
import com.th.contact.data.entity.Contact
import com.th.contact.feature.base.BaseFragment
import com.th.contact.feature.details.ContactDetailsFragment
import com.th.contact.util.PageUtil
import kotlinx.android.synthetic.main.fragment_contact_list.*

/**
 * Created By Tharindu on 8/7/2019
 *
 */
class ContactListFragment : BaseFragment() {

    lateinit var viewModel: ContactListViewModel
    private val contactAdapter = ContactAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.let {
            ViewModelProviders.of(it).get(ContactListViewModel::class.java)
        } ?: throw Exception("In valid Activity")

        setObservers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()

        setListeners()
    }

    private fun setListeners() {

        contactAdapter?.clickListener = object : ContactViewHolder.ClickListener {
            override fun onClick(contact: Contact) {
                val bundle = Bundle()
                viewModel.selectedContact.value = contact
                bundle.putInt("ID", contact.id ?: -1)
                addFragment(ContactDetailsFragment(), PageUtil.CONTACT_DETAILS, bundle)
            }
        }
    }

    private fun initLayout() {

        contactListRv?.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    /**
     * set Observers from View model
     */
    private fun setObservers() {
        viewModel.getContactData().observe(this, Observer {
            refreshData(it.data)
        })
        viewModel.showProgress.observe(this, Observer {
            showOrHideProgress(it)
        })
        viewModel.onError.observe(this, Observer {
            showErrorMessage(it)
        })
    }

    private fun refreshData(list: List<Contact>) {
        contactAdapter.refreshAdapter(list)
    }
}