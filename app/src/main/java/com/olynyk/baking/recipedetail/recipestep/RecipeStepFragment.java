package com.olynyk.baking.recipedetail.recipestep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Step;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RecipeStepFragment extends Fragment implements RecipeStepContract.View {

    private static final String ARGUMENT_RECIPE_STEP = "ARGUMENT_RECIPE_STEP";

    private RecipeStepContract.Presenter mPresenter;
    private TextView recipeStepDescription;

    public RecipeStepFragment() {

    }

    public static RecipeStepFragment newInstance(Step step) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_RECIPE_STEP, step);
        RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
        recipeStepFragment.setArguments(arguments);
        return recipeStepFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new RecipeStepPresenter(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        recipeStepDescription = root.findViewById(R.id.recipe_step_long_description);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Step step = getArguments().getParcelable(ARGUMENT_RECIPE_STEP);
        recipeStepDescription.setText(step.getLongDescription());
    }
}
