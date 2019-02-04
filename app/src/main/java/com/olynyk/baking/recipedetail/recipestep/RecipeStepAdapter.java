package com.olynyk.baking.recipedetail.recipestep;

import com.olynyk.baking.domain.Step;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class RecipeStepAdapter extends FragmentStatePagerAdapter {

    private List<Step> mSteps;

    public RecipeStepAdapter(FragmentManager fragmentManager, List<Step> steps) {
        super(fragmentManager);
        this.mSteps = steps;
    }

    @Override
    public Fragment getItem(int position) {
        return RecipeStepFragment.newInstance(mSteps.get(position));
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }
}
