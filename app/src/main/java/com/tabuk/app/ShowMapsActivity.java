package com.tabuk.app;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera
        LatLng tabuk = new LatLng(25.174848907056965,37.276403456926346);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(tabuk, 16.5f));

        mMap.setOnMapClickListener(this);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(25.1736752359273,37.276811487972736))
                .title("Football stadium"));

    }

    private static final String TAG = "VisitorMapsActivity";
    @Override
    public void onMapClick(LatLng latLng) {
        Log.i(TAG, "onMapClick: " + latLng.latitude +"," + latLng.longitude);
    }


    public void showPlaces(View view) {
        new PlaceListDialogFragment().show(getSupportFragmentManager(), "dialog");
    }

}