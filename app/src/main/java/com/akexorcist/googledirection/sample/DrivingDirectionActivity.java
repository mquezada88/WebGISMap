package com.akexorcist.googledirection.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.GoogleLatLng;
import com.akexorcist.googledirection.LatLngCallback;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.helper.PlacesAutoCompleteAdapter;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.LatLngAddress;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class DrivingDirectionActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback, LatLngCallback {
    private ImageView btnRequestDirection;
    private GoogleMap googleMap;
    private CardView dirCard;
    private Button dirBtn;
    private String directionsServerKey = "AIzaSyAXAyEwR16JBLsrhdAdv2y0vjeJqJuTv-k";
    private String geocorderKey = "AIzaSyB0VFsMEgYOyss5LJ7G_5-CjcjLqsAoc0s";
    private LatLng camera = new LatLng(40.8200471,-73.9492724);
    private LatLng originLatLng;
    private String destination;
    private String origin;
    private AutoCompleteTextView starting;
    private AutoCompleteTextView dest;
    private AutoCompleteTextView autocompleteViewDest;
    private AutoCompleteTextView autocompleteViewOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transit_direction);

        starting = (AutoCompleteTextView) findViewById(R.id.start);
        dest = (AutoCompleteTextView) findViewById(R.id.dest);

        btnRequestDirection = (ImageView) findViewById(R.id.btn_request_direction);
        dirCard = (CardView) findViewById(R.id.cardview);
        dirBtn = (Button) findViewById(R.id.btncard);
        dirBtn.setBackgroundColor(Color.WHITE);

        dirBtn.setVisibility(View.GONE);

        dirBtn.setOnClickListener(this);
        btnRequestDirection.setOnClickListener(this);

        autocompleteViewOrigin = (AutoCompleteTextView) findViewById(R.id.start);
        autocompleteViewOrigin.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));
        autocompleteViewOrigin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                Toast.makeText(DrivingDirectionActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });

        autocompleteViewDest = (AutoCompleteTextView) findViewById(R.id.dest);
        autocompleteViewDest.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));
        autocompleteViewDest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                Toast.makeText(DrivingDirectionActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        });

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
        if (id == R.id.btn_request_direction) {
            requestDirection();
        }
        if(id == R.id.btncard){
            autocompleteViewOrigin.setText("");
            autocompleteViewDest.setText("");
            dirCard.setVisibility(View.VISIBLE);
            dirBtn.setVisibility(View.GONE);
        }
    }


    public void requestDirection() {
        String orig = starting.getText().toString();
        String desti = dest.getText().toString();

        if (orig.isEmpty()) {
            Toast.makeText(this, "Please enter starting address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (desti.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        dirCard.setVisibility(View.GONE);
        dirBtn.setVisibility(View.VISIBLE);

        GoogleLatLng.withAPIKey(geocorderKey)
                .address(orig)
                .execute(this);

        Snackbar.make(btnRequestDirection, "Searching Route...", Snackbar.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(directionsServerKey)
                .from(orig)
                .to(desti)
                .transportMode(TransportMode.DRIVING)
                .execute(this);
    }


    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        //-----
        Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(directionPositionList.get(0), 14));
            googleMap.addMarker(new MarkerOptions().position(directionPositionList.get(0))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            googleMap.addMarker(new MarkerOptions().position(directionPositionList.get(directionPositionList.size()-1))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.BLUE));
        }

    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onLatLngSuccess(LatLngAddress lladdress, String rawBody) {
        Snackbar.make(btnRequestDirection, "Address Found. Status: " + lladdress.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (lladdress.isOK()) {
            LatLng latLng= lladdress.getResults().get(0).getGeometry().getLocation().getLatLng();
        }
    }

    @Override
    public void onLatLngFailure(Throwable t) {
        Snackbar.make(btnRequestDirection, t.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

}

