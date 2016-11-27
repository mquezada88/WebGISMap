package com.akexorcist.googledirection.model;

import com.akexorcist.googledirection.constant.RequestResult;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(parcelsIndex = false)
public class Result {
    @SerializedName("formatted_address")
    String formattedAddress;
    @SerializedName("geometry")
    Geometry geom;

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public Geometry getGeometry() {
        return geom;
    }

}
