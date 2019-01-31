package com.olynyk.baking.domain;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Step implements Parcelable {
    private int mId;
    private String mShortDescription;
    private String mLongDescription;
    private String mVideoUrl;
    private String mImageUrl;

    public Step(JSONObject jsonObject) throws JSONException {
        this.mId = jsonObject.getInt("id");
        this.mShortDescription = jsonObject.getString("shortDescription");
        this.mLongDescription = jsonObject.getString("description");
        this.mVideoUrl = jsonObject.optString("viewURL", null);
        this.mImageUrl = jsonObject.optString("thumbnailURL", null);
    }

    public Step(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mShortDescription = parcel.readString();
        this.mLongDescription = parcel.readString();
        this.mVideoUrl = parcel.readString();
        this.mImageUrl = parcel.readString();
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.mShortDescription = shortDescription;
    }

    public String getLongDescription() {
        return mLongDescription;
    }

    public void setLongDescription(String longDescription) {
        this.mLongDescription = longDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.mVideoUrl = videoUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mLongDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mImageUrl);
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
