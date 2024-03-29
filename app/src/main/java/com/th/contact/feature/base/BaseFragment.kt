package com.th.contact.feature.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.th.contact.R
import com.th.contact.util.CommonUtil

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

    fun showErrorMessage(error: Throwable) {
        Toast.makeText(context, "Error ${error.message}", Toast.LENGTH_LONG).show()
    }

    fun showOrHideProgress(show: Boolean) {
        mActivity?.showOrHideProgress(show)
    }

    /**
     * Add Fragment
     * @param fragment : Fragment to Add
     * @param tag : TAG
     * @param bundle : Extra Data
     */
    fun addFragment(fragment: Fragment, tag: String, bundle: Bundle? = null) {

        mActivity?.addFragment(fragment, tag, bundle)
        when (tag) {
            CommonUtil.CONTACT_DETAILS -> {
                mActivity?.hideActionMenu(R.id.actionAdd)
                mActivity?.showActionMenu(R.id.actionEdit)
            }
            CommonUtil.CONTACT_SAVE -> {
                mActivity?.hideActionMenu(R.id.actionEdit)
                mActivity?.showActionMenu(R.id.actionDone)
            }
        }
    }
}