package com.th.contact.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.th.contact.R
import com.th.contact.data.entity.Contact
import com.th.contact.feature.base.BaseFragment
import com.th.contact.feature.save.SaveContactFragment
import com.th.contact.util.CommonUtil
import com.th.contact.util.CommonUtil.CONTACT_DATA
import com.th.contact.util.CommonUtil.INPUT_TYPE
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
     * go to Edit Page
     */
    fun goToEditPage() {
        val bundle = Bundle()
        bundle.putString(INPUT_TYPE,CommonUtil.EDIT_CONTACT)
        bundle.putParcelable(CONTACT_DATA, viewModel.contactDetails.value?.data)
        addFragment(SaveContactFragment(), CommonUtil.CONTACT_SAVE, bundle)
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
        // nameTxt.text = contactDetails?.firstName
        fNameDetailsWidget.setValue(contactDetails?.firstName)
        lNameDetailsWidget.setValue(contactDetails?.lastName)
        emailDetailsWidget.setValue(contactDetails?.email)

        fullNameTxt.text = "${contactDetails?.firstName} ${contactDetails?.lastName}"

        Picasso.get()
            .load(contactDetails?.avatar)
            .into(profileImg)
    }
}