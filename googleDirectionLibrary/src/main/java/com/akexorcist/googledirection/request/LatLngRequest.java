
package com.akexorcist.googledirection.request;


public class LatLngRequest {
    String apiKey;
    String origin;

    public LatLngRequest(String apiKey) {
        this.origin = origin;
    }

    public LatLngRequestUrl address(String address) {
        return new LatLngRequestUrl(apiKey, address);
    }
}
