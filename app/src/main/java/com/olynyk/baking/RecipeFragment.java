package com.olynyk.baking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olynyk.baking.domain.Recipe;
import com.olynyk.baking.recipedetail.RecipeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        mPresenter = new RecipePresenter(this);
        mRecipeAdapter = new RecipeAdapter(new ArrayList<Recipe>(), mRecipeItemListener);
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

        RecyclerView recyclerView = root.findViewById(R.id.recipe_recycler_view);
        recyclerView.setAdapter(mRecipeAdapter);
        if(getResources().getBoolean(R.bool.isTablet)) {
            recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(root.getContext(), 1));
        }
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

    private RecipeAdapter.RecipeItemLisener mRecipeItemListener = new RecipeAdapter.RecipeItemLisener() {
        @Override
        public void onRecipeClick(Recipe recipe) {
            mOnCardClickListener.onCardSelected(recipe);
        }
    };

    public interface OnCardClickListener {
        void onCardSelected(Recipe recipe);
    }
}
