package com.olynyk.baking.recipedetail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Ingredient;
import com.olynyk.baking.domain.Recipe;
import com.olynyk.baking.domain.Step;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailFragment extends Fragment implements RecipeDetailContract.View {

    private static final String ARGUMENT_RECIPE = "ARGUMENT_RECIPE";

    private RecipeDetailContract.Presenter mPresenter;
    private RecipeDetailIngredientAdapter mRecipeDetailIngredientAdapter;
    private RecipeDetailStepAdapter mRecipeDetailStepAdapter;
    private StepItemClickListener mStepItemClickListener;

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
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mStepItemClickListener = (StepItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement StepItemClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RecipeDetailPresenter(this);
        mRecipeDetailIngredientAdapter = new RecipeDetailIngredientAdapter(new ArrayList<Ingredient>());
        mRecipeDetailStepAdapter = new RecipeDetailStepAdapter(new ArrayList<Step>(), mStepItemListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        RecyclerView ingredientsRecyclerView = root.findViewById(R.id.recipe_detail_ingredients_recycler_view);
        ingredientsRecyclerView.setAdapter(mRecipeDetailIngredientAdapter);
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerView stepsRecyclerView = root.findViewById(R.id.recipe_detail_steps_recycler_view);
        stepsRecyclerView.setAdapter(mRecipeDetailStepAdapter);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Recipe recipe = getArguments().getParcelable(ARGUMENT_RECIPE);
        mPresenter.openRecipeDetail(recipe);
    }

    @Override
    public void showRecipeDetail(Recipe recipe) {
        mRecipeDetailIngredientAdapter.replaceData(recipe.getIngredients());
        mRecipeDetailStepAdapter.replaceData(recipe.getSteps());
    }

    private RecipeDetailStepAdapter.StepItemListener mStepItemListener = new RecipeDetailStepAdapter.StepItemListener() {
        @Override
        public void onStepClick(Step step) {
            mStepItemClickListener.onStepItemClicked(step);
        }
    };

    public interface StepItemClickListener {
        void onStepItemClicked(Step step);
    }
}
