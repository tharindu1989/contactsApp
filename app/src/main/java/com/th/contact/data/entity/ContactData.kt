package com.th.contact.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created By Tharindu on 8/7/2019
 *
 */
data class ContactData(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val data: List<Contact>
)