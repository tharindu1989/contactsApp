package com.th.contact.feature.base

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.test.espresso.idling.CountingIdlingResource
import com.th.contact.R
import com.th.contact.component.ProgressDialog
import com.th.contact.feature.details.ContactDetailsFragment
import com.th.contact.feature.list.ContactListFragment
import com.th.contact.feature.list.ContactListViewModel
import com.th.contact.feature.save.SaveContactFragment
import com.th.contact.util.CommonUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val countingIdlingResource: CountingIdlingResource = CountingIdlingResource("API_LOADING")
    private var progressBar: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressBar = ProgressDialog(this)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)

        addFragment(
            fragment = ContactListFragment(),
            tag = CommonUtil.CONTACT_LIST,
            isAddToBackStack = false
        )

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(com.th.contact.R.menu.contact_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.actionAdd -> {
                hideActionMenu(R.id.actionAdd)
                hideActionMenu(R.id.actionEdit)
                showActionMenu(R.id.actionDone)
                val bundle = Bundle()
                bundle.putString(CommonUtil.INPUT_TYPE, CommonUtil.ADD_CONTACT)
                addFragment(SaveContactFragment(), CommonUtil.CONTACT_SAVE, bundle)
            }
            R.id.actionEdit -> {
                val detailsFragment = getFragmentByTag<ContactDetailsFragment>(CommonUtil.CONTACT_DETAILS)
                detailsFragment?.goToEditPage()
            }
            R.id.actionDone -> {
                val saveFragment = getFragmentByTag<SaveContactFragment>(CommonUtil.CONTACT_SAVE)
                saveFragment?.clickDoneButton()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun hideActionMenu(id: Int) {
        toolBar.menu.findItem(id).isVisible = false
    }

    fun showActionMenu(id: Int) {
        toolBar.menu.findItem(id).isVisible = true
    }

    /**
     * add Fragment to Container
     */
    fun addFragment(
        fragment: Fragment,
        tag: String,
        bundle: Bundle? = null,
        isAddToBackStack: Boolean = true
    ) {

        bundle?.let {
            fragment.arguments = bundle
        }
        val fragmentManager = this.supportFragmentManager
        val ft = fragmentManager?.beginTransaction()
        if (isAddToBackStack) {
            ft?.add(R.id.container, fragment, tag)
            ft?.addToBackStack(tag)
        } else {
            ft?.replace(R.id.container, fragment, tag)
        }
        ft?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft?.commit()
    }

    /**
     * get Fragment By TAG
     * @param tag : Fragment TAG
     */
    private fun <T> getFragmentByTag(tag: String): T? {
        val fragmentManager = this.supportFragmentManager
        return fragmentManager.findFragmentByTag(tag) as? T
    }

    override fun onBackPressed() {
        super.onBackPressed()
        checkCurrentFragment()
    }

    /**
     * check Current Fragment and change Toolbar item
     */
    private fun checkCurrentFragment() {

        getFragmentByTag<SaveContactFragment>(CommonUtil.CONTACT_SAVE)?.let {
            if (it.isVisible) {
                hideActionMenu(R.id.actionEdit)
                hideActionMenu(R.id.actionAdd)
                showActionMenu(R.id.actionDone)
                return
            }
        }
        getFragmentByTag<ContactDetailsFragment>(CommonUtil.CONTACT_DETAILS)?.let {
            if (it.isVisible) {
                hideActionMenu(R.id.actionDone)
                hideActionMenu(R.id.actionAdd)
                showActionMenu(R.id.actionEdit)
                return
            }
        }

        hideActionMenu(R.id.actionDone)
        hideActionMenu(R.id.actionEdit)
        showActionMenu(R.id.actionAdd)
    }

    /**
     * show or Hide Progress
     */
    fun showOrHideProgress(show: Boolean) {

        if (show) {
            if (progressBar?.isShowing == false) {
                countingIdlingResource.increment()
                progressBar?.showDialog()
            }
        } else {
            if (progressBar?.isShowing == true) {
                if (!countingIdlingResource.isIdleNow) {
                    countingIdlingResource.decrement()
                }
                progressBar?.hideDialog()
            }
        }
    }
}
