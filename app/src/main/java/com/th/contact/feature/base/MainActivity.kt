package com.th.contact.feature.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.th.contact.R
import com.th.contact.data.api.ContactApi
import com.th.contact.feature.list.ContactListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(ContactListFragment(), "ContactList")

        /*ContactApi().getContactList(1).map {
            Log.e("Items", "$it")
        }.onErrorReturn {
            Log.e("Error", "$it")
        }.subscribe()*/
    }

    /**
     * add Fragment to Container
     */
    fun addFragment(fragment: Fragment, tag: String, bundle: Bundle? = null) {

        bundle?.let {
            fragment.arguments = bundle
        }
        val fragmentManager = this.supportFragmentManager
        val ft = fragmentManager?.beginTransaction()
        ft?.add(R.id.container, fragment, tag)
        ft?.addToBackStack(tag)
        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft?.commit()
    }
}
