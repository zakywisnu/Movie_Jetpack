package com.zeroemotion.bfaa_kotlin_tmdb.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.zeroemotion.bfaa_kotlin_tmdb.R
import com.zeroemotion.bfaa_kotlin_tmdb.data.DataDummy
import com.zeroemotion.bfaa_kotlin_tmdb.util.EspressoIdlingResource
import org.junit.*
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    fun test1LoadMoviesAndTvShows() {
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
    fun test2LoadMoviesAndTvShowsDetail() {
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

        pressBack()
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

    @Test
    fun test3InsertAndUpdateFavorite(){
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
        onView(withId(R.id.favorites)).perform(click())

        pressBack()
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
        onView(withId(R.id.favorites)).perform(click())
    }

    @Test
    fun test4LoadAndDeleteFavorites(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rvFavMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.favorites)).perform(click())
        pressBack()

        onView(withText(R.string.favorite_tv)).perform(click())
        onView(withId(R.id.rvFavTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            click()))
        onView(withId(R.id.favorites)).perform(click())
        pressBack()
    }


}