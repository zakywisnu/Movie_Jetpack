package com.zeroemotion.bfaa_kotlin_tmdb.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvs = DataDummy.generateDummyTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun start() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMoviesAndTvShows() {
        onView(withId(R.id.rvMovie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))

        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rvTvShow))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvs.size))
    }

    @Test
    fun loadMoviesAndTvShowsDetail() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detailTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitle)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.detailRelease)).check(matches(isDisplayed()))
        onView(withId(R.id.detailRelease)).check(matches(withText(dummyMovie[0].releaseDate)))

        Espresso.pressBack()
        onView(withId(R.id.tvShowFragment)).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detailTitleTv)).check(matches(isDisplayed()))
        onView(withId(R.id.detailTitleTv)).check(matches(withText(dummyTvs[0].name)))
        onView(withId(R.id.detailReleaseTv)).check(matches(isDisplayed()))
        onView(withId(R.id.detailReleaseTv)).check(matches(withText(dummyTvs[0].firstAirDate)))
    }
}