
package com.akexorcist.googledirection.request;

public class LatLngAddressParam {
    String address;
    String apiKey;

    public String getAddress() {
        return address;
    }

    public LatLngAddressParam setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public LatLngAddressParam setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }


}
