package com.th.contact.feature.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created By Tharindu on 8/7/2019
 *
 */
open class BaseViewModel : ViewModel() {

    var onError: MutableLiveData<Throwable> = MutableLiveData()
    var showProgress: MutableLiveData<Boolean> = MutableLiveData()

    protected fun onError(err: Throwable) {
        hideProgress()
        showProgress.value = false
        onError.value = err
    }

    protected fun showProgress() {
        showProgress.value = true
    }

    protected fun hideProgress() {
        showProgress.value = false
    }

}