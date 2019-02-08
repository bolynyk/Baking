package com.olynyk.baking;

import android.os.Bundle;

import com.olynyk.baking.util.EspressoIdlingResource;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.espresso.IdlingResource;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        toolbar.setTitle(R.string.recipe_toolbar_title);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            initFragment(RecipeFragment.newInstance());
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.recipe_frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
