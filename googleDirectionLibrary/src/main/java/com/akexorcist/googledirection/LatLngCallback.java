
package com.akexorcist.googledirection;

import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.LatLngAddress;
import com.google.android.gms.maps.model.LatLng;

public interface LatLngCallback {
    void onLatLngSuccess(LatLngAddress lla, String rawBody);
    void onLatLngFailure(Throwable t);
}
