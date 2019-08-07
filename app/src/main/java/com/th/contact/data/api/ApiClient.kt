package com.th.contact.data.api

import com.th.contact.config.AppConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By Tharindu on 8/7/2019
 *
 */
object ApiClient{

    val retrofit = Retrofit.Builder()
        .baseUrl(AppConfig.BaseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}