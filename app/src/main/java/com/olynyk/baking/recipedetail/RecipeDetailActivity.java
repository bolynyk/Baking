package com.olynyk.baking.recipedetail;

import android.content.Intent;
import android.os.Bundle;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Recipe;
import com.olynyk.baking.domain.Step;
import com.olynyk.baking.recipedetail.recipestep.RecipeStepActivity;
import com.olynyk.baking.recipedetail.recipestep.RecipeStepFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.olynyk.baking.recipedetail.recipestep.RecipeStepActivity.EXTRA_RECIPE_STEP;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailFragment.StepItemClickListener {

    public static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    private boolean mTwoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Recipe recipe = getIntent().getParcelableExtra(EXTRA_RECIPE);

        Toolbar toolbar = findViewById(R.id.recipe_detail_toolbar);
        toolbar.setTitle(recipe.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (findViewById(R.id.recipe_step_frame_layout) != null) {
            mTwoPane = true;
        }

        if (savedInstanceState == null) {
            initFragment(R.id.recipe_detail_frame_layout, RecipeDetailFragment.newInstance(recipe));
            if (mTwoPane) {
                initFragment(R.id.recipe_step_frame_layout, RecipeStepFragment.newInstance(recipe.getSteps().get(0)));
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onStepItemClicked(Step step) {
        if (mTwoPane) {
            Fragment fragment = RecipeStepFragment.newInstance(step);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_step_frame_layout, fragment).commit();
        } else {
            Intent intent = new Intent(this, RecipeStepActivity.class);
            intent.putExtra(EXTRA_RECIPE_STEP, step);
            startActivity(intent);
        }
    }

    private void initFragment(int containerViewId, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }


}
