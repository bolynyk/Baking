package com.olynyk.baking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.olynyk.baking.domain.Recipe;
import com.olynyk.baking.recipedetail.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeFragment extends Fragment implements RecipeContract.View {

    private RecipeAdapter mRecipeAdapter;
    private RecipeContract.Presenter mPresenter;

    private OnCardClickListener mOnCardClickListener;

    public RecipeFragment() {
        mOnCardClickListener = new OnCardClickListener() {
            @Override
            public void onCardSelected(Recipe recipe) {
                mPresenter.loadRecipeDetail(recipe);
            }
        };
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

        GridView gridView = root.findViewById(R.id.recipe_grid_view);
        gridView.setAdapter(mRecipeAdapter);
        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) mRecipeAdapter.getItem(position);
                mOnCardClickListener.onCardSelected(recipe);
            }
        });

        return root;
    }

    public void showRecipes(List<Recipe> recipes) {
        mRecipeAdapter.replaceData(recipes);
    }

    public void showRecipeDetailUi(Recipe recipe) {
        Intent intent = new Intent(getContext(), RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE, recipe);
        startActivity(intent);
    }

    public interface OnCardClickListener {
        void onCardSelected(Recipe recipe);
    }
}
