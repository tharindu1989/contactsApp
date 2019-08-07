package com.th.contact.feature.base

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created By Tharindu on 8/7/2019
 *
 */
open class BaseFragment : Fragment() {

    var mActivity: MainActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as? MainActivity
    }

    fun showErrorMessage() {

    }

    fun showOrHideProgress(isHide: Boolean) {

    }

    /**
     * Add Fragment
     * @param fragment : Fragment to Add
     * @param tag : TAG
     * @param bundle : Extra Data
     */
    fun addFragment(fragment: Fragment, tag: String, bundle: Bundle? = null) {
        mActivity?.addFragment(fragment, tag, bundle)
    }
}