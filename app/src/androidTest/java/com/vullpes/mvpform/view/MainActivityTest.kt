package com.vullpes.mvpform.view


import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.vullpes.mvpform.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class MainActivityTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    private var decorView: View? = null


    @Before
    fun setup(){
        hiltRule.inject()

    }




    @Test
    fun testarButtonSalvarIsDisabled(){
        onView(withId(R.id.btnSalvar)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
    }

    @Test
    fun testarOCampoCepDeMascara(){
        onView(withId(R.id.txtCep)).perform(ViewActions.typeText("08588608"))
        onView(withId(R.id.txtCep)).check(matches(withText("08588-608")));
    }



    @Test
    fun testarEnabledButtonWhenIsMissingAFieldNumero(){
        onView(withId(R.id.txtNome)).perform(ViewActions.typeText("trump"))
        onView(withId(R.id.txtCargo)).perform(ViewActions.typeText("president"))
        onView(withId(R.id.txtIdade)).perform(ViewActions.typeText("70"))
        onView(withId(R.id.txtCep)).perform(ViewActions.typeText("08588608"))
        onView(withId(R.id.btnSalvar)).check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
    }


    @Test
    fun testarEnabledButtonWhenFillTheForm(){
        onView(withId(R.id.txtNome)).perform(ViewActions.typeText("trump"))
        onView(withId(R.id.txtCargo)).perform(ViewActions.typeText("president"))
        onView(withId(R.id.txtIdade)).perform(ViewActions.typeText("70"))
        onView(withId(R.id.txtCep)).perform(ViewActions.typeText("08588608"))
        onView(withId(R.id.txtNumero)).perform(ViewActions.typeText("10"))
        onView(withId(R.id.btnSalvar)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }



    @Test
    fun checkIfAToastIsFired(){
        var activity: MainActivity? = null
        activityRule.scenario.onActivity { mainActivity ->
            activity = mainActivity
        }


        onView(withId(R.id.txtNome)).perform(ViewActions.typeText("trump"))
        onView(withId(R.id.txtCargo)).perform(ViewActions.typeText("president"))
        onView(withId(R.id.txtIdade)).perform(ViewActions.typeText("70"))
        onView(withId(R.id.txtCep)).perform(ViewActions.typeText("08588608"))
        onView(withId(R.id.txtNumero)).perform(ViewActions.typeText("10"))
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btnSalvar)).perform(click())

        onView(withText("Formulario Salvo")).inRoot(
            withDecorView(
                not(
                    `is`(
                        activity?.window?.decorView
                    )
                )
            )
        ).check(
            matches(
                isDisplayed()
            )
        )

    }



}


