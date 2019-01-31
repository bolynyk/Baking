package com.olynyk.baking;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.olynyk.baking.com.olynyk.domain.Recipe;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecipePresenter implements RecipeContract.Presenter {

    private static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final String TAG = RecipePresenter.class.getCanonicalName();

    private final RecipeContract.View mView;

    public RecipePresenter(final RecipeContract.View view) {
        this.mView = view;
    }

    @Override
    public void loadRecipes() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(mView.getContext());

            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, generateUrl(), null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        List<Recipe> recipes = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            recipes.add(new Recipe(response.getJSONObject(i)));
                        }
                        mView.showRecipes(recipes);
                    } catch (JSONException e) {
                        Log.d(TAG, "", e);
                        mView.showRecipes(new ArrayList<Recipe>());
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, error.getMessage());
                    mView.showRecipes(new ArrayList<Recipe>());
                }
            });

            requestQueue.add(jsonObjectRequest);
        } catch (URISyntaxException e) {
            Log.d(TAG, "", e);
            mView.showRecipes(new ArrayList<Recipe>());
        } catch (MalformedURLException e) {
            Log.d(TAG, "", e);
            mView.showRecipes(new ArrayList<Recipe>());
        }
    }

    private String generateUrl() throws URISyntaxException, MalformedURLException {
        URI uri = new URI(RECIPE_URL);
        return uri.toURL().toString();
    }
}
