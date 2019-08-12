package com.th.contact.data.repository

import com.th.contact.data.entity.ContactData
import com.th.contact.data.entity.ContactDetails
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By Tharindu on 8/7/2019
 *
 */
interface ContactRepository {

    @GET("users")
    fun getContactList(@Query("page") numberOfItems: Int): Observable<ContactData>

    @GET("users/{id}")
    fun getContactDetails(@Path("id") contactId: Int): Observable<ContactDetails>
}