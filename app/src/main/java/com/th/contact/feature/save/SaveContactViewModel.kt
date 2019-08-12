package com.th.contact.feature.save

import androidx.lifecycle.MutableLiveData
import com.th.contact.data.api.ContactApi
import com.th.contact.data.entity.Contact
import com.th.contact.data.entity.ContactDetails
import com.th.contact.feature.base.BaseViewModel

/**
 * Created By Tharindu on 8/8/2019
 *
 */

class SaveContactViewModel : BaseViewModel() {

    val inputType: MutableLiveData<String>  by lazy {
        MutableLiveData<String>()
    }

    val contact: MutableLiveData<Contact>  by lazy {
        MutableLiveData<Contact>()
    }

    val savedContact: MutableLiveData<Contact>  by lazy {
        MutableLiveData<Contact>()
    }

    val updatedContact: MutableLiveData<Contact>  by lazy {
        MutableLiveData<Contact>()
    }

    /**
     * save New Contact Data
     * @param contact : New Data
     */
    fun saveNewContact(contact: Contact) {
        showProgress()
        ContactApi().saveContact(contact).map {
            hideProgress()
            savedContact.value = it
        }.onErrorReturn {
            onError(it)
        }.subscribe()
    }

    /**
     * update existing Data
     * @param contact : data
     */
    fun updateContact(contact: Contact) {
        showProgress()
        ContactApi().updateContact(contact).map {
            hideProgress()
            updatedContact.value = it
        }.onErrorReturn {
            onError(it)
        }.subscribe()
    }
}