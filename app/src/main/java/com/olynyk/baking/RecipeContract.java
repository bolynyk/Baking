package com.olynyk.baking;

import android.content.Context;

import com.olynyk.baking.domain.Recipe;

import java.util.List;

public interface RecipeContract {

    interface View {
        Context getContext();
        void showRecipes(List<Recipe> recipes);
        void showRecipeDetailUi(Recipe recipe);
    }

    interface Presenter {
        void loadRecipes();
        void loadRecipeDetail(Recipe recipe);
    }
}
