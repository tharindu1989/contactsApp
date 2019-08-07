package com.th.contact.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created By Tharindu on 8/7/2019
 *
 */
data class Contact(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val last_name: String? = null,
    @SerializedName("avatar") val avatar: String? = null
)