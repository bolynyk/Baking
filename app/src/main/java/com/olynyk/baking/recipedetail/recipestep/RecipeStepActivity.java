package com.olynyk.baking.recipedetail.recipestep;

import android.os.Bundle;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Step;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class RecipeStepActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_STEP = "EXTRA_RECIPE_STEP";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        Toolbar toolbar = findViewById(R.id.recipe_step_toolbar);
        toolbar.setTitle(R.string.recipe_toolbar_title);
        setSupportActionBar(toolbar);

        Step step = getIntent().getParcelableExtra(EXTRA_RECIPE_STEP);

        if (savedInstanceState == null) {
            initFragment(RecipeStepFragment.newInstance(step));
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.recipe_step_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
