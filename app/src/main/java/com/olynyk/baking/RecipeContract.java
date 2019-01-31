package com.olynyk.baking;

import android.content.Context;

import com.olynyk.baking.com.olynyk.domain.Recipe;

import java.util.List;

public interface RecipeContract {

    interface View {
        Context getContext();
        void showRecipes(List<Recipe> recipes);
    }

    interface Presenter {
        void loadRecipes();
    }
}
