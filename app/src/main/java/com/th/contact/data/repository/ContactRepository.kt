package com.th.contact.data.repository

import com.th.contact.data.entity.Contact
import com.th.contact.data.entity.ContactData
import com.th.contact.data.entity.ContactDetails
import com.th.contact.util.CommonUtil
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created By Tharindu on 8/7/2019
 *
 */
interface ContactRepository {

    @GET("users?per_page=10")
    fun getContactList(
        @Query("page") numberOfItems: Int,
        @Query("per_page") itemsPerPage: Int = CommonUtil.ITEMS_PER_PAGE
    ): Observable<ContactData>

    @GET("users/{id}")
    fun getContactDetails(@Path("id") contactId: Int): Observable<ContactDetails>

    @POST("users")
    fun saveContact(@Body params: Contact): Observable<Contact>

    @PUT("users")
    fun updateContact(@Body params: Contact): Observable<Contact>
}