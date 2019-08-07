package com.th.contact.feature.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.th.contact.data.api.ContactApi
import com.th.contact.data.entity.Contact
import com.th.contact.data.entity.ContactData
import com.th.contact.feature.base.BaseViewModel

/**
 * Created By Tharindu on 8/7/2019
 *
 */
class ContactListViewModel : BaseViewModel() {

    private val contactLiveData: MutableLiveData<ContactData> by lazy {
        MutableLiveData<ContactData>().also {
            getContactListData()
        }
    }

    fun getContactData(): LiveData<ContactData> {
        return contactLiveData
    }

    private fun getContactListData() {
        showProgress()
        ContactApi().getContactList(1).map {
            hideProgress()
            contactLiveData.value = it
        }.onErrorReturn {
            onError(it)
        }.subscribe()
    }

}