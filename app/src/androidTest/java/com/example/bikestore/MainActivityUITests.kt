package com.example.bikestore

import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewFinder
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.Description
import org.junit.runner.RunWith
import java.util.regex.Matcher

@RunWith(AndroidJUnit4::class)
class MainActivityUITests {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testUIElements() {
        // Test TextViews
        onView(withId(R.id.bike_name)).check(matches(isDisplayed()))
        onView(withId(R.id.bike_description)).check(matches(isDisplayed()))
        onView(withId(R.id.bike_rate_per_day)).check(matches(isDisplayed()))

        // Test Buttons
        onView(withId(R.id.btn_borrow)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_next)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_borrow)).perform(click())

    }

    @Test
    fun testFeatureTextViews() {
        onView(withId(R.id.contains_basket)).check(matches(isDisplayed()))
        onView(withId(R.id.contains_panniers)).check(matches(isDisplayed()))
        onView(withId(R.id.is_city_bike)).check(matches(isDisplayed()))
    }

    @Test
    fun testRatingBar() {
        // Test the RatingBar
        onView(withId(R.id.bike_rating)).check(matches(isDisplayed()))
    }

    @Test
    fun testNextButtonClickable() {
        // Test clicking a button
        onView(withId(R.id.btn_next)).perform(click())
        onView(withId(R.id.btn_next)).check(matches(isClickable()))
    }

}
