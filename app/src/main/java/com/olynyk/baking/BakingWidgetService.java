package com.olynyk.baking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.olynyk.baking.domain.Ingredient;
import com.olynyk.baking.domain.Recipe;

import org.json.JSONArray;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BakingWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class BakingWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final String TAG = BakingWidgetRemoteViewsFactory.class.getCanonicalName();

    private List<Recipe> mRecipes = new ArrayList<>();
    private Context mContext;

    public BakingWidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonObjectRequest;

        try {
            RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
            jsonObjectRequest = new JsonArrayRequest(generateUrl(), requestFuture, requestFuture);

            requestQueue.add(jsonObjectRequest);
            JSONArray jsonArray = requestFuture.get();
            List<Recipe> recipes = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Recipe recipe = new Recipe(jsonArray.getJSONObject(i));
                Log.v(BakingWidgetRemoteViewsFactory.class.getCanonicalName(), "Recipe " + recipe.getName());
                recipes.add(recipe);
            }
            mRecipes.addAll(recipes);

        } catch (Exception e) {
            Log.v(TAG, "", e);
        }
    }

    @Override
    public void onDestroy() {
        mRecipes.clear();
    }

    @Override
    public int getCount() {
        return mRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.baking_widget_item);
        remoteViews.setTextViewText(R.id.baking_widget_item_title, mRecipes.get(position).getName());
        StringBuilder stringBuilder = new StringBuilder();
        for (Ingredient ingredient : mRecipes.get(position).getIngredients()) {
            stringBuilder.append(ingredient.toString() + "\n");
        }
        remoteViews.setTextViewText(R.id.baking_widget_item, stringBuilder.toString());

        Bundle bundle = new Bundle();
        bundle.putInt(BakingWidget.EXTRA_ITEM, position);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        remoteViews.setOnClickFillInIntent(R.id.baking_widget_item, intent);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private String generateUrl() throws URISyntaxException, MalformedURLException {
        URI uri = new URI(RECIPE_URL);
        return uri.toURL().toString();
    }
}
