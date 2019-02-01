package com.olynyk.baking.recipedetail;

import android.os.Bundle;

import com.olynyk.baking.domain.Recipe;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View {

    private static final String ARGUMENT_RECIPE = "ARGUMENT_RECIPE";

    private RecipeDetailContract.Presenter mPresenter;

    public RecipeDetailFragment() {

    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_RECIPE, recipe);
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setArguments(arguments);
        return recipeDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RecipeDetailPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }
}
