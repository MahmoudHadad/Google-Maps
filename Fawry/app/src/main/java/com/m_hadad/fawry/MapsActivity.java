package com.m_hadad.fawry;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<MarkerOptions> branches = new ArrayList<MarkerOptions>();
    public static  boolean wait = true;

    private final LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras){}
        @Override
        public void onProviderEnabled(String provider) {}
        @Override
        public void onProviderDisabled(String provider) {}
        public void onLocationChanged(final Location location){


        }
    };
    ////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("on create", "");
        Log.e("on create", "");

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();



    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                Reader r =  new Reader(branches , getAssets());
                r.execute();

                while (wait);
                wait = true;
                
                Log.e("Fawry Wait", "back to mapAct");
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        Log.e("set markers", "");

//        try {
//            r.get(6, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }


        mMap.setMyLocationEnabled(true);
        for (MarkerOptions marker : branches)
            mMap.addMarker(marker);
        // get location
        if(!branches.isEmpty())
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(branches.get(0).getPosition(), 4));

    }
}
