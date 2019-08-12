package com.th.contact

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert

/**
 * Created By Tharindu on 8/12/2019
 *
 */
class RecyclerViewItemCountAssertion : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        val rvView: RecyclerView? = view as? RecyclerView
        val itemSize = rvView?.adapter?.itemCount ?: 0
        Assert.assertNotEquals(itemSize, 0)
    }
}