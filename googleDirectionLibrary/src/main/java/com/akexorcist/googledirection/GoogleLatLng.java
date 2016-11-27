
package com.akexorcist.googledirection;

import com.akexorcist.googledirection.request.DirectionOriginRequest;
import com.akexorcist.googledirection.request.LatLngRequest;
import com.google.android.gms.maps.model.LatLng;

public class GoogleLatLng {
    public static LatLngRequest withAPIKey(String apiKey) {
        return new LatLngRequest(apiKey);
    }
}
