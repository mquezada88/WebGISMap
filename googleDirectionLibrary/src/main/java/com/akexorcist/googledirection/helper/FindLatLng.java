package com.akexorcist.googledirection.helper;


import com.akexorcist.googledirection.GoogleLatLng;
import com.akexorcist.googledirection.LatLngCallback;
import com.akexorcist.googledirection.model.LatLngAddress;
import com.google.android.gms.maps.model.LatLng;

public class FindLatLng implements LatLngCallback{
    private LatLng addressLatLng;
    private String address;

    public FindLatLng(String geoCorderKey, String address){
        GoogleLatLng.withAPIKey(geoCorderKey)
                .address(address)
                .execute(this);
    }

    public LatLng getAddressLatLng(){return addressLatLng;}

    public String getAddress(){return address;}

    @Override
    public void onLatLngSuccess(LatLngAddress lladdress, String rawBody) {

        if (lladdress.isOK()) {
            LatLng latLng= lladdress.getResults().get(0).getGeometry().getLocation().getLatLng();
            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
            addressLatLng = latLng;
            address = lladdress.getResults().get(0).getFormattedAddress();
            System.out.println(address);
        }
        else {addressLatLng = null;
              address = null;
        }
    }

    @Override
    public void onLatLngFailure(Throwable t) {
    }
}
