package com.laboremus.santecoffee

import android.content.Intent
import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.laboremus.santecoffee.adapter.FarmerAdapter
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {
    @Rule
    @JvmField
    var mainActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun startMainActivity(){
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        mainActivity.activity.startActivity(intent)
    }

    private fun openDrawer(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()) // Open Drawer
    }

    private fun navigateToHome() {
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_home))
    }

    private fun navigateToAddFarmer() {
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_gallery))
    }

    private fun navigateToAudits() {
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_slideshow))
    }

    @Before
    fun setUp(){
        startMainActivity()
    }

    @Test
    fun isUIDisplayed(){
        //Is Home Displayed
        onView(withId(R.id.farmer_recycler_view)).check(matches(isDisplayed()))

        //Is Edit Displayed
        onView(withId(R.id.farmer_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<FarmerAdapter.FarmerViewHolder>(2, click()))
        onView(withId(R.id.edit_name_textView)).check(matches(isDisplayed()))

        startMainActivity()

        //Is Add Farmer Displayed
        openDrawer()
        navigateToAddFarmer()
        onView(withId(R.id.addFarmerbutton))
                .check(matches(isDisplayed()))

        //Is Audits Displayed
        openDrawer()
        navigateToAudits()
        onView(withId(R.id.audits_layout))
                .check(matches(isDisplayed()))

    }

    @Test
    fun isRecordEdited(){
        onView(withId(R.id.farmer_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<FarmerAdapter.FarmerViewHolder>(2, click()))
        onView(withId(R.id.edit_name_textView)).check(matches(isDisplayed()))

        onView(withId(R.id.edit_number_editText))
                .perform(clearText())
                .perform(typeText("11111"), closeSoftKeyboard())

        onView(withId(R.id.submit_edit_button)).perform(click())


        onView(withId(R.id.farmer_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<FarmerAdapter.FarmerViewHolder>(2, click()))

        onView(withId(R.id.edit_number_editText)).check(matches(withText(containsString("1111"))))
    }

    @Test
    fun isFarmerAdded(){
        openDrawer()
        navigateToAddFarmer()
        onView(withId(R.id.name))
                .perform(typeText("Test Name"), closeSoftKeyboard())
        onView(withId(R.id.gender))
                .perform(typeText("Male"), closeSoftKeyboard())
        onView(withId(R.id.birthCertificate))
                .perform(typeText("BC_URL"), closeSoftKeyboard())
        onView(withId(R.id.nin))
                .perform(typeText("NIN"), closeSoftKeyboard())
        onView(withId(R.id.phone))
                .perform(typeText("07777777"), closeSoftKeyboard())
        onView(withId(R.id.addFarmerbutton))
                .perform(click())

        openDrawer()
        navigateToHome()

        onView(withId(R.id.farmer_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition<FarmerAdapter.FarmerViewHolder>(0, click()))
        onView(withId(R.id.edit_name_textView)).check(matches(withText("Test Name")))
    }
}