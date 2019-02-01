package com.olynyk.baking.recipedetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View {

    private RecipeDetailContract.Presenter mPresenter;

    public RecipeDetailFragment() {

    }

    public static RecipeDetailFragment newInstance() {
        return new RecipeDetailFragment();
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
