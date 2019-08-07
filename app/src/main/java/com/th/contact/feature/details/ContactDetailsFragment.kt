package com.th.contact.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.th.contact.R
import com.th.contact.data.entity.Contact
import com.th.contact.data.entity.ContactDetails
import com.th.contact.feature.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contact_details.*

/**
 * Created By Tharindu on 8/8/2019
 *
 */
class ContactDetailsFragment : BaseFragment() {

    lateinit var viewModel: ContactDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contact_details, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.let {
            ViewModelProviders.of(this).get(ContactDetailsViewModel::class.java)
        } ?: throw Exception("In valid Activity")

        setObservers()

        getData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initLayout()

        setListeners()
    }

    /**
     * get External Data
     */
    private fun getData() {
        viewModel.contactId.value = arguments?.getInt("ID") ?: -1
    }

    /**
     * set Listeners
     */
    private fun setListeners() {


    }

    /**
     * init Layout
     */
    private fun initLayout() {

    }

    /**
     * set Observers from View model
     */
    private fun setObservers() {
        viewModel.contactId.observe(this, Observer {
            viewModel.getContactDetails()
        })
        viewModel.contactDetails.observe(this, Observer {
            setContactDetails(it?.data)
        })
        viewModel.showProgress.observe(this, Observer {
            showOrHideProgress(it)
        })
    }

    /**
     * set Contact Details
     */
    private fun setContactDetails(contactDetails: Contact?) {
        nameTxt.text = contactDetails?.firstName
    }
}