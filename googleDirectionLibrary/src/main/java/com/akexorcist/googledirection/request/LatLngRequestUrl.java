/*

Copyright 2015 Akexorcist

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package com.akexorcist.googledirection.request;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.LatLngCallback;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.LatLngAddress;
import com.akexorcist.googledirection.network.DirectionAndPlaceConnection;
import com.akexorcist.googledirection.network.LatLngConnection;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatLngRequestUrl {
    protected LatLngAddressParam param;

    public LatLngRequestUrl(String apiKey, String address) {
        param = new LatLngAddressParam().setApiKey(apiKey).setAddress(address);
    }

    public void execute(final LatLngCallback callback) {
        Call<LatLngAddress> latLng = LatLngConnection.getInstance()
                .createService()
                .getLatLng(param.getAddress(),
                           param.getApiKey());

        latLng.enqueue(new Callback<LatLngAddress>() {
            @Override
            public void onResponse(Call<LatLngAddress> call, Response<LatLngAddress> response) {
                if (callback != null) {
                    callback.onLatLngSuccess(response.body(), new Gson().toJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<LatLngAddress> call, Throwable t) {
                callback.onLatLngFailure(t);
            }
        });
    }
}
