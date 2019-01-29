package com.olynyk.baking;

import androidx.fragment.app.Fragment;

public class RecipeFragment extends Fragment implements RecipeContract.View {

    public RecipeFragment() {}

    public static RecipeFragment newInstance() {
        return new RecipeFragment();
    }
}
