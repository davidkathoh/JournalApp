package com.example.david.journalapp.uitest;

import android.support.test.runner.AndroidJUnit4;

import com.example.david.journalapp.R;
import com.example.david.journalapp.launcher.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.rule.ActivityTestRule;

/**
 * Created by david on 6/30/18.
 */
@RunWith(AndroidJUnit4.class)
public class UITest {

    @Rule
    public ActivityTestRule<LauncherActivity> mHomeActivityActivityTestRule
            = new ActivityTestRule<LauncherActivity>(LauncherActivity.class);

    @Test
    public void test_Launcher() throws Exception{

       onView(withId(R.id.btn_signup)).check(matches(isDisplayed()));
       onView(withId(R.id.btn_login)).check(matches(isDisplayed()));


    }
    @Test
    public void launch_sign_up() throws Exception{
        onView(withId(R.id.btn_signup)).perform(click());

    }
    @Test
    public void launch_signin() throws Exception{
        onView(withId(R.id.btn_login)).perform(click());

    }
    @Test
    public void create_user_with_email() throws Exception{
    String name =" test";
     String mail = "test@kathoh.com";
     String password = "test_password";
        onView(withId(R.id.btn_signup)).perform(click());
        onView(withId(R.id.ed_name)).perform(typeText(name));
        onView(withId(R.id.ed_mail)).perform(typeText(mail));
        onView(withId(R.id.ed_password)).perform(typeText(password));
        //onView(withId(R.id.btn_create_account)).perform(click());

    }
}
