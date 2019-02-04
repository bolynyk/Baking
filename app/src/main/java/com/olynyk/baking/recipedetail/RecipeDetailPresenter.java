package com.olynyk.baking.recipedetail;

import com.olynyk.baking.domain.Recipe;

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter {

    private RecipeDetailContract.View mView;

    public RecipeDetailPresenter(RecipeDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void openRecipeDetail(Recipe recipe) {
        mView.showRecipeDetail(recipe);
    }
}
