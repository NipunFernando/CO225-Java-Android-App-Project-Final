package com.ishanadeeparidma.bidcoin.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDateTime;

public class StartNewBidByAdminModel implements Parcelable {
    private String email;
    private String password;
    private String startDate;
    private String endDate;
    private String cryptoName;

    public StartNewBidByAdminModel(String email, String password, String startDate, String endDate, String cryptoName) {
        this.email = email;
        this.password = password;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cryptoName = cryptoName;
    }

    protected StartNewBidByAdminModel(Parcel in) {
        email = in.readString();
        password = in.readString();
        cryptoName = in.readString();
    }

    public static final Creator<StartNewBidByAdminModel> CREATOR = new Creator<StartNewBidByAdminModel>() {
        @Override
        public StartNewBidByAdminModel createFromParcel(Parcel in) {
            return new StartNewBidByAdminModel(in);
        }

        @Override
        public StartNewBidByAdminModel[] newArray(int size) {
            return new StartNewBidByAdminModel[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(cryptoName);
    }
}
