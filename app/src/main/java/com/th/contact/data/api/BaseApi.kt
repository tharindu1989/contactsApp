package com.th.contact.data.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created By Tharindu on 8/7/2019
 *
 */
open class BaseApi {

    fun <T> getData(observer: Observable<T>): Observable<T> {
        return observer.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}