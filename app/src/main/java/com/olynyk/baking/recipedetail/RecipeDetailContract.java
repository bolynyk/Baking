package com.olynyk.baking.recipedetail;

import com.olynyk.baking.domain.Recipe;

public interface RecipeDetailContract {

    interface View {
        void showRecipeDetail(Recipe recipe);
    }

    interface Presenter {
        void openRecipeDetail(Recipe recipe);
    }
}
