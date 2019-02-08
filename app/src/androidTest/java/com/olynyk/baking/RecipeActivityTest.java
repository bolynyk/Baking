package com.olynyk.baking;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> mActivityTestRule =
            new ActivityTestRule<>(RecipeActivity.class);

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(mActivityTestRule.getActivity().getIdlingResource());
    }

    @After
    public void deregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mActivityTestRule.getActivity().getIdlingResource());
    }

    @Test
    public void clickGridViewItem_OpensRecipeDetailActivity() {
        onData(anything()).inAdapterView(withId(R.id.recipe_grid_view)).atPosition(0).perform(click());

        onView(
                allOf(instanceOf(TextView.class),
                        withParent(withId(R.id.recipe_detail_toolbar)))).check(matches(withText("Nutella Pie")));

        onView(withId(R.id.recipe_detail_ingredients_title)).check(matches(withText("Ingredients")));

        ViewInteraction textView = onView(
                allOf(withId(R.id.recipe_detail_ingredient_item_title),
                        withText("2-CUP Graham Cracker crumbs"),
                        childAtPosition(childAtPosition(withId(R.id.recipe_detail_ingredients_recycler_view), 0), 0),
                        isDisplayed()));

        textView.check(matches(withText("2-CUP Graham Cracker crumbs")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " +
                        position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup
                        && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
