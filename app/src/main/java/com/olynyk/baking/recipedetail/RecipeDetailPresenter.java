package com.olynyk.baking.recipedetail;

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter {

    private RecipeDetailContract.View mView;

    public RecipeDetailPresenter(RecipeDetailContract.View view) {
        this.mView = view;
    }
}
