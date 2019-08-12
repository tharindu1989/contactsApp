package com.th.contact.feature.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.th.contact.R
import com.th.contact.feature.list.ContactListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)

        addFragment(ContactListFragment(), "ContactList")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.th.contact.R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.actionAdd -> {

            }
            R.id.actionAdd -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun hideActioninMenu(id: Int) {
        toolBar.menu.findItem(id).isVisible = false
    }

    fun showActioninMenu(id: Int) {
        toolBar.menu.findItem(id).isVisible = true
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
