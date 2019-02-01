package com.olynyk.baking.recipedetail;

import android.os.Bundle;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Recipe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        toolbar.setTitle(R.string.recipe_toolbar_title);
        setSupportActionBar(toolbar);

        Recipe recipe = (Recipe) getIntent().getParcelableExtra(EXTRA_RECIPE);

        if (savedInstanceState == null) {
            initFragment(RecipeDetailFragment.newInstance());
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.recipe_detail_frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
