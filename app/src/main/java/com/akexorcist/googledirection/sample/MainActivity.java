package com.akexorcist.googledirection.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {
    private GoogleMap googleMap;
    private LatLng camera = new LatLng(40.8200471,-73.9492724);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_walking).setOnClickListener(this);
        findViewById(R.id.btn_transit).setOnClickListener(this);
        findViewById(R.id.btn_driving).setOnClickListener(this);
        findViewById(R.id.btn_bicycling).setOnClickListener(this);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(camera, 14));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_walking) {
            goToWalkingDirection();
        } else if (id == R.id.btn_transit) {
            goToTransitDirection();
        } else if (id == R.id.btn_driving) {
            goToDrivingDirection();
        } else if (id == R.id.btn_bicycling) {
            goToBicyclingDirection();
        }
    }

    public void goToDrivingDirection() {
        openActivity(DrivingDirectionActivity.class);
    }

    public void goToTransitDirection() {
        openActivity(TransitDirectionActivity.class);
    }

    public void goToWalkingDirection() {
        openActivity(WalkingDirectionActivity.class);
    }

    public void goToBicyclingDirection() {
        openActivity(BicyclingDirectionActivity.class);
    }

    public void openActivity(Class<?> cs) {
        startActivity(new Intent(this, cs));
    }
}