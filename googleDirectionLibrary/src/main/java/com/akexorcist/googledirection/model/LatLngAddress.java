package com.akexorcist.googledirection.model;

import com.akexorcist.googledirection.constant.RequestResult;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel(parcelsIndex = false)
public class LatLngAddress {
    @SerializedName("results")
    List<Result> results ;
    String status;
    @SerializedName("error_message")
    String errorMessage;

    public List<Result> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    public boolean isOK() {
        return status.equals(RequestResult.OK);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
