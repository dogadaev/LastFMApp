package org.dogadaev.lastfm.search.presentation.view.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.dogadaev.lastfm.search.R
import org.dogadaev.lastfm.search.presentation.view.activity.SearchActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SearchActivity::class.java, true, false)

    @Before
    fun setup() {

        activityRule.launchActivity(null)

//        val fragment = SearchFragment.Screen().fragment
//        activityRule.activity.supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    @Test
    fun user_can_enter_search_query() {
        onView(withId(R.id.searchField)).perform(typeText("Macklemore"))
    }
}