package com.olynyk.baking.recipedetail;

import android.os.Bundle;

import com.olynyk.baking.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE = "EXTRA_RECIPE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        toolbar.setTitle(R.string.recipe_toolbar_title);
        setSupportActionBar(toolbar);

    }
}
