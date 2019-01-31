package com.olynyk.baking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.olynyk.baking.com.olynyk.domain.Recipe;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeFragment extends Fragment implements RecipeContract.View {

    private RecipeAdapter mRecipeAdapter;
    private RecipeContract.Presenter mPresenter;

    public RecipeFragment() {
    }

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipeAdapter = new RecipeAdapter(getContext(), new ArrayList<Recipe>());
        mPresenter = new RecipePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadRecipes();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_recipe, container, false);

        GridView gridView = root.findViewById(R.id.recipes_grid_view);
        gridView.setAdapter(mRecipeAdapter);

        return root;
    }

    public void showRecipes(List<Recipe> recipes) {
        mRecipeAdapter.replaceData(recipes);
    }
}
