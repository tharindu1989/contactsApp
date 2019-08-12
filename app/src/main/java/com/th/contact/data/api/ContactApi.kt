package com.th.contact.data.api

import com.th.contact.data.entity.ContactData
import com.th.contact.data.entity.ContactDetails
import com.th.contact.data.repository.ContactRepository
import io.reactivex.Observable

/**
 * Created By Tharindu on 8/7/2019
 *
 */
class ContactApi : BaseApi() {

    private val contactRepo: ContactRepository by lazy {
        ApiClient.retrofit.create(ContactRepository::class.java)
    }

    /**
     * get Contact List
     * @param numberOfItems : Number of Items
     */
    fun getContactList(numberOfItems: Int): Observable<ContactData> {
        return getData(contactRepo.getContactList(numberOfItems))
    }

    /**
     * get Contact Details by ID
     * @param contactID : ID
     */
    fun getContactDetails(contactID: Int): Observable<ContactDetails> {
        return getData(contactRepo.getContactDetails(contactID))
    }
}