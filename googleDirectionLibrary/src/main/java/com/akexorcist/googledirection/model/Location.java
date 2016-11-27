
package com.akexorcist.googledirection.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;


@Parcel(parcelsIndex = false)
public class Location {
    @SerializedName("lat")
    double lat;
    @SerializedName("lng")
    double lng;

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public LatLng getLatLng(){return new LatLng(lat,lng); }
}
