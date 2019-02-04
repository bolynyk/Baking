package com.olynyk.baking.recipedetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olynyk.baking.R;
import com.olynyk.baking.domain.Step;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeDetailStepAdapter extends RecyclerView.Adapter<RecipeDetailStepAdapter.ViewHolder> {

    private List<Step> mSteps;
    private StepItemListener mStepItemListener;

    public RecipeDetailStepAdapter(List<Step> steps, StepItemListener stepItemListener) {
        if (steps != null) {
            this.mSteps = steps;
        } else {
            this.mSteps = new ArrayList<>();
        }
        this.mStepItemListener = stepItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View recipeDetailStepView = layoutInflater.inflate(R.layout.item_recipe_detail_step, parent, false);

        return new ViewHolder(recipeDetailStepView, mStepItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = mSteps.get(position);

        holder.mTextView.setText(step.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public Step getItem(int position) {
        return mSteps.get(position);
    }

    public void replaceData(List<Step> steps) {
        if (steps != null) {
            mSteps.clear();
            mSteps.addAll(steps);
        } else {
            mSteps.clear();
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;
        private StepItemListener mStepItemListener;

        public ViewHolder(View itemView, StepItemListener stepItemListener) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.recipe_detail_step_item_title);
            mStepItemListener = stepItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Step step = getItem(position);
            mStepItemListener.onStepClick(step);
        }
    }

    public interface StepItemListener {
        void onStepClick(Step step);
    }
}
