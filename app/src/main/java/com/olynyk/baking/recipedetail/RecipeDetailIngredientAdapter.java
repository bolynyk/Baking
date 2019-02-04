package com.olynyk.baking.recipedetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailIngredientAdapter extends RecyclerView.Adapter<RecipeDetailIngredientAdapter.ViewHolder> {

    private List<Ingredient> mIngredients;

    public RecipeDetailIngredientAdapter(List<Ingredient> ingredients) {
        if (ingredients != null) {
            mIngredients = ingredients;
        } else {
            mIngredients = new ArrayList<>();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View recipeDetailIngredientView = layoutInflater.inflate(R.layout.item_recipe_detail_ingredient, parent, false);

        return new ViewHolder(recipeDetailIngredientView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);

        holder.mTextView.setText(ingredient.toString());
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public void replaceData(List<Ingredient> ingredients) {
        if (ingredients != null) {
            mIngredients.clear();
            mIngredients.addAll(ingredients);
        } else {
            mIngredients.clear();
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.recipe_detail_ingredient_item_title);
        }
    }
}
