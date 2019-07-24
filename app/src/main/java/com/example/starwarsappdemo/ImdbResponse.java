package com.example.starwarsappdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class ImdbResponse implements Parcelable {

    private double rating;
    private String title;

    public ImdbResponse(double rating, String title) {
        this.rating = rating;
        this.title = title;
    }

    protected ImdbResponse(Parcel in) {
        rating = in.readDouble();
        title = in.readString();
    }

    public static final Creator<ImdbResponse> CREATOR = new Creator<ImdbResponse>() {
        @Override
        public ImdbResponse createFromParcel(Parcel in) {
            return new ImdbResponse(in);
        }

        @Override
        public ImdbResponse[] newArray(int size) {
            return new ImdbResponse[size];
        }
    };

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(rating);
        parcel.writeString(title);
    }
}
