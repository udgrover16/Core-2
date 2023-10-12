package com.example.bikestore

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class BorrowActivityUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(BorrowActivity::class.java)

    @Test
    fun testSeekBar() {
        onView(withId(R.id.progress_days))
            .check(matches(isDisplayed()))
            .check(matches(withProgress(1)))
            .perform(swipeRight())
    }

    @Test
    fun testSaveButton() {
        // Test clicking the "Save" button
        onView(withId(R.id.btn_save))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    private fun withProgress(expectedProgress: Int) =
        object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("SeekBar with progress: $expectedProgress")
            }

            override fun matchesSafely(item: View?): Boolean {
                if (item is androidx.appcompat.widget.AppCompatSeekBar) {
                    return item.progress == expectedProgress
                }
                return false
            }
        }
}