package com.th.contact.feature.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.th.contact.R
import com.th.contact.component.CircleTransform
import com.th.contact.data.entity.Contact
import com.th.contact.feature.base.BaseFragment
import com.th.contact.util.CommonUtil
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
        viewModel.inputType.value = arguments?.getString(CommonUtil.INPUT_TYPE)
        viewModel.contact.value = arguments?.getParcelable<Contact>(CommonUtil.CONTACT_DATA)
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

        viewModel.contact.observe(this, Observer {
            it?.let { contact ->
                setContactDetails(it)
            } ?: run {
                emailDetailsWidget.enableValue()
            }
        })
        viewModel.savedContact.observe(this, Observer {
            Toast.makeText(context, "SuccessFully Saved", Toast.LENGTH_LONG).show()
        })
        viewModel.updatedContact.observe(this, Observer {
            Toast.makeText(context, "SuccessFully Updated", Toast.LENGTH_LONG).show()
        })
        viewModel.showProgress.observe(this, Observer {
            showOrHideProgress(it)
        })
    }

    fun clickDoneButton() {
        when (viewModel.inputType.value) {
            CommonUtil.ADD_CONTACT -> {
                viewModel.saveNewContact(getNewContactData())
            }
            CommonUtil.EDIT_CONTACT -> {
                viewModel.updateContact(getUpdatedContactData())
            }
        }
    }

    /**
     * set Contact Details
     */
    private fun setContactDetails(contact: Contact) {
        fNameDetailsWidget?.setValue(contact.firstName)
        lNameDetailsWidget?.setValue(contact.lastName)
        emailDetailsWidget?.setValue(contact.email)
        Picasso.get()
            .load(contact?.avatar)
            .transform(CircleTransform())
            .into(profileImg)
    }

    private fun getNewContactData(): Contact {
        return Contact(
            firstName = fNameDetailsWidget.getValue(),
            lastName = lNameDetailsWidget.getValue(),
            email = emailDetailsWidget.getValue()
        )
    }

    private fun getUpdatedContactData(): Contact {
        return Contact(
            firstName = fNameDetailsWidget.getValue(),
            lastName = lNameDetailsWidget.getValue()
        )
    }
}