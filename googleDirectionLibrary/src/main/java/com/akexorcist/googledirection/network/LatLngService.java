
package com.akexorcist.googledirection.network;

import com.akexorcist.googledirection.constant.DirectionUrl;
import com.akexorcist.googledirection.constant.LatLngUrl;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.LatLngAddress;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LatLngService {

    @GET(LatLngUrl.LATLNG_GEOCODE_URL)
    Call<LatLngAddress> getLatLng(@Query("address") String address,
                                  @Query("key") String apiKey);
}
