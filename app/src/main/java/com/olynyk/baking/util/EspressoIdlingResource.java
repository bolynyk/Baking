package com.olynyk.baking.util;

import androidx.test.espresso.IdlingResource;

public class EspressoIdlingResource {

    private static SimpleIdlingResource mSimpleIdlingResource = new SimpleIdlingResource();

    public static void activate() {
        mSimpleIdlingResource.setIdleState(true);
    }

    public static void deactivate() {
        mSimpleIdlingResource.setIdleState(false);
    }

    public static IdlingResource getIdlingResource() {
        return mSimpleIdlingResource;
    }
}
