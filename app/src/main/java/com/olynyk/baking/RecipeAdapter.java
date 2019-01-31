package com.olynyk.baking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.olynyk.baking.domain.Recipe;

import java.util.List;

public class RecipeAdapter extends BaseAdapter {

    private Context mContext;
    private List<Recipe> mRecipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        this.mContext = context;
        this.mRecipes = recipes;
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRecipes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_recipe, parent, false);
        }

        Recipe recipe = (Recipe) getItem(position);

        TextView textView = convertView.findViewById(R.id.recipe_item_title);
        textView.setText(recipe.getName());

        return convertView;
    }

    public void replaceData(List<Recipe> recipes) {
        mRecipes.clear();
        mRecipes.addAll(recipes);
        notifyDataSetChanged();
    }
}
