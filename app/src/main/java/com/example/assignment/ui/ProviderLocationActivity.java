

package com.example.assignment.ui;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.assignment.R;
import com.example.assignment.helpers.DatabaseManager;
import com.example.assignment.models.Provider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ProviderLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String providerId;
    private String lat;
    private String lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_location);
        DatabaseManager databaseManager = new DatabaseManager(ProviderLocationActivity.this);
        ArrayList<Provider> providers = databaseManager.getAllProviders();
        Bundle extras = getIntent().getExtras();
        try {
            providerId = extras.getString("Provider");
        } catch (NullPointerException exception) {
            // pass
        }
        for (Provider provider : providers){
            if (provider.getId().equals(providerId)){
                lat = provider.getLat();
                lng = provider.getLng();
            }
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(Double.valueOf(this.lat), Double.valueOf(this.lng));
        mMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }

    public void goBack(View view) {
        Intent intent = new Intent(ProviderLocationActivity.this, ProvidersActivity.class);
        ProviderLocationActivity.this.startActivity(intent);
    }

}

      