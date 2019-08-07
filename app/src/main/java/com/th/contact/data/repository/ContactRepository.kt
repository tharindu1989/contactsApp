package com.th.contact.data.repository

import com.th.contact.data.entity.ContactData
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By Tharindu on 8/7/2019
 *
 */
interface ContactRepository {

    @GET("users")
    fun getContactList(@Query("page") numberOfItems: Int): Observable<ContactData>
}