package com.th.contact.feature.save

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.th.contact.R
import com.th.contact.data.entity.Contact
import com.th.contact.feature.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_save_contact.*

/**
 * Created By Tharindu on 8/8/2019
 *
 */
class SaveContactFragment : BaseFragment() {

    lateinit var viewModel: SaveContactViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_save_contact, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.let {
            ViewModelProviders.of(this).get(SaveContactViewModel::class.java)
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
        viewModel.inputType.value = arguments?.getString("ID")
        viewModel.contact.value = arguments?.getParcelable<Contact>("contact")
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
        viewModel.inputType.observe(this, Observer {
            //viewModel.getContactDetails()
        })
        viewModel.contact.observe(this, Observer {
            it?.let { contact ->
                fNameDetailsWidget?.setValue(contact.firstName)
                lNameDetailsWidget?.setValue(contact.lastName)
                emailDetailsWidget?.setValue(contact.email)
            } ?: run {

            }
        })
        viewModel.showProgress.observe(this, Observer {
            showOrHideProgress(it)
        })
    }

    fun clickDoneButton() {
        Toast.makeText(context, "TEST", Toast.LENGTH_LONG).show()
    }

    /**
     * set Contact Details
     */
    private fun setContactDetails(contactDetails: Contact?) {
        // nameTxt.text = contactDetails?.firstName
        /* fNameDetailsWidget.setValue(contactDetails?.firstName)
         lNameDetailsWidget.setValue(contactDetails?.lastName)
         emailDetailsWidget.setValue(contactDetails?.email)

         fullNameTxt.text = "${contactDetails?.firstName} ${contactDetails?.lastName}"

         Picasso.get()
             .load(contactDetails?.avatar)
             .into(profileImg)*/
    }
}