
package com.akexorcist.googledirection.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel(parcelsIndex = false)
public class Geometry {
    @SerializedName("location")
    Location location;

    public Location getLocation() {
        return location;
    }

}
