package com.olynyk.baking.recipedetail.recipestep;

public class RecipeStepPresenter implements RecipeStepContract.Presenter {

    private RecipeStepContract.View mView;

    public RecipeStepPresenter(RecipeStepContract.View view) {
        this.mView = view;
    }
}
