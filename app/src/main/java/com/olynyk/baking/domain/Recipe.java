package com.olynyk.baking.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private int mId;
    private String mName;
    private int mServings;
    private List<Ingredient> mIngredients;
    private List<Step> mSteps;

    public Recipe(JSONObject jsonObject) throws JSONException {
        this.mId = jsonObject.getInt("id");
        this.mName = jsonObject.getString("name");
        this.mServings = jsonObject.getInt("servings");
        this.mIngredients = new ArrayList<>();
        JSONArray ingredients = jsonObject.getJSONArray("ingredients");
        for (int i = 0; i < ingredients.length(); i++) {
            mIngredients.add(new Ingredient(ingredients.getJSONObject(i)));
        }
        this.mSteps = new ArrayList<>();
        JSONArray steps = jsonObject.getJSONArray("steps");
        for (int i = 0; i < steps.length(); i++) {
            mSteps.add(new Step(steps.getJSONObject(i)));
        }
    }

    public Recipe(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
        this.mServings = parcel.readInt();
        this.mIngredients = new ArrayList<>();
        parcel.readTypedList(this.mIngredients, Ingredient.CREATOR);
        this.mSteps = new ArrayList<>();
        parcel.readTypedList(this.mSteps, Step.CREATOR);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getServings() {
        return mServings;
    }

    public void setServings(int servings) {
        this.mServings = servings;
    }

    public List<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.mIngredients = ingredients;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public void setSteps(List<Step> steps) {
        this.mSteps = steps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeInt(mServings);
        dest.writeTypedList(mIngredients);
        dest.writeTypedList(mSteps);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
