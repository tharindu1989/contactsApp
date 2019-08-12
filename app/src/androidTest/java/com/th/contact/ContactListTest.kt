package com.th.contact

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.th.contact.feature.base.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By Tharindu on 8/12/2019
 *
 */
@RunWith(AndroidJUnit4::class)
class ContactListTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testContactList() {
        IdlingRegistry.getInstance().register(activityTestRule.activity.countingIdlingResource)
        onView(withId(R.id.contactListRv)).check(RecyclerViewItemCountAssertion())
    }
}