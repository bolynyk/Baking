package com.olynyk.baking;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.olynyk.baking.com.olynyk.domain.Recipe;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeFragment extends Fragment implements RecipeContract.View {



    public RecipeFragment() {}

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_recipe, container, false);

        GridView gridView = (GridView) root.findViewById(R.id.recipes_grid_view);

        //TODO Retrieve Recipe List
        RecipeAdapter recipeAdapter = new RecipeAdapter(getContext(), new ArrayList<Recipe>());

        gridView.setAdapter(recipeAdapter);

        return root;
    }
}
