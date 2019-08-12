package com.th.contact.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created By Tharindu on 8/7/2019
 *
 */
@Parcelize
data class Contact(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("first_name") val firstName: String? = null,
    @SerializedName("last_name") val lastName: String? = null,
    @SerializedName("avatar") val avatar: String? = null
) : Parcelable