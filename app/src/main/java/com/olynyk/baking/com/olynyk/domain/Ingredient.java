package com.olynyk.baking.com.olynyk.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Ingredient implements Parcelable {
    private int mQuantity;
    private String mUnit;
    private String mName;

    public Ingredient(JSONObject jsonObject) throws JSONException {
        this.mQuantity = jsonObject.getInt("quantity");
        this.mUnit = jsonObject.getString("measure");
        this.mName = jsonObject.getString("ingredient");
    }

    public Ingredient(Parcel parcel) {
        this.mQuantity = parcel.readInt();
        this.mUnit = parcel.readString();
        this.mName = parcel.readString();
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int quantity) {
        this.mQuantity = quantity;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        this.mUnit = unit;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mQuantity);
        dest.writeString(mUnit);
        dest.writeString(mName);
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel source) {
            return new Ingredient(source);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
}
