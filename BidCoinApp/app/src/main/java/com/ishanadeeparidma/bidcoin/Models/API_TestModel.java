package com.ishanadeeparidma.bidcoin.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class API_TestModel {
    @SerializedName("isAlive")
    private String isAlive = "no";

    public String getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(String isAlive) {
        this.isAlive = isAlive;
    }
}
