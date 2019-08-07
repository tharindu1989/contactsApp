package com.th.contact.feature.details

import androidx.lifecycle.MutableLiveData
import com.th.contact.data.api.ContactApi
import com.th.contact.data.entity.ContactDetails
import com.th.contact.feature.base.BaseViewModel

/**
 * Created By Tharindu on 8/8/2019
 *
 */

class ContactDetailsViewModel : BaseViewModel() {

    val contactId: MutableLiveData<Int>  by lazy {
        MutableLiveData<Int>()
    }

    val contactDetails: MutableLiveData<ContactDetails>  by lazy {
        MutableLiveData<ContactDetails>()
    }


    /**
     * get Contact Details by ID
     */
    fun getContactDetails() {
        showProgress()
        ContactApi().getContactDetails(contactId.value ?: -1).map {
            hideProgress()
            contactDetails.value = it
        }.onErrorReturn {
            onError(it)
        }.subscribe()
    }
}