package com.th.contact.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created By Tharindu on 8/8/2019
 *
 */
data class ContactDetails(
    @SerializedName("data") val data: Contact? = null
)