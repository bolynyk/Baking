package com.olynyk.baking;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olynyk.baking.domain.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> mRecipes;
    private RecipeItemLisener mRecipeItemListener;

    public RecipeAdapter(List<Recipe> recipes, RecipeItemLisener recipeItemLisener) {
        if (recipes != null) {
            this.mRecipes = recipes;
        } else {
            this.mRecipes = new ArrayList<>();
        }
        this.mRecipeItemListener = recipeItemLisener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View recipeView = layoutInflater.inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(recipeView, mRecipeItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);

        holder.mTextView.setText(recipe.getName());
        if (recipe.getImage() != null && !recipe.getImage().isEmpty()) {
            holder.mImageView.setVisibility(View.VISIBLE);
            Picasso.get().load(Uri.parse(recipe.getImage())).into(holder.mImageView);
        } else {
            holder.mImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public Recipe getItem(int position) {
        return mRecipes.get(position);
    }

    public void replaceData(List<Recipe> recipes) {
        if (recipes != null) {
            mRecipes.clear();
            mRecipes.addAll(recipes);
        } else {
            mRecipes.clear();
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageView;
        private TextView mTextView;
        private RecipeItemLisener mRecipeItemListener;

        public ViewHolder(View itemView, RecipeItemLisener recipeItemLisener) {
            super(itemView);
            this.mRecipeItemListener = recipeItemLisener;
            mImageView = itemView.findViewById(R.id.recipe_item_image_view);
            mTextView = itemView.findViewById(R.id.recipe_item_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Recipe recipe = getItem(position);
            mRecipeItemListener.onRecipeClick(recipe);
        }
    }

    public interface RecipeItemLisener {
        void onRecipeClick(Recipe recipe);
    }
}
