package com.m_hadad.fawry;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.csvreader.CsvReader;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by M-Hadad on 21-Jul-15.
 */

public class Reader extends AsyncTask<Void, Void, Void>{
    private ArrayList<MarkerOptions> markers;
    private AssetManager assets;

    public Reader(ArrayList<MarkerOptions> markers, AssetManager assets) {
        Log.e("Fawry"," in csv");
        this.markers = markers;
        this.assets = assets;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.e("Fawry ", "In csv reader");
        try {


            InputStream csvStream = assets.open("test.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CsvReader products = new CsvReader(csvStreamReader);


            products.readHeaders();

            while (products.readRecord())
            {
                String branchName = products.get("Branch name");
                String email = products.get("email");
                double lat = Double.parseDouble(products.get("latitude"));
                double long_ =Double.parseDouble(products.get("longitude"));
                //  Log.e("Fawry ", branchName);

                addMarker(markers, new LatLng(lat, long_), branchName, email);
            }

            products.close();

        } catch (FileNotFoundException e) {
            Log.e("Fawry ","FileNotFoundException " + e.toString());
        } catch (IOException e) {
            Log.e("Fawry ","IOException " + e.toString());
        }


        Log.e("Fawry Wait", "wait finished");
        MapsActivity.wait = false;
        Log.e("Fawry num of branches", markers.size()+"");

        return null;
    }
///////////////////////////////////////////////////////////////////////////////////////

    private void addMarker(ArrayList<MarkerOptions> markers, LatLng latLng, String branchName, String email) {
        // do filtering


        markers.add(new MarkerOptions().position(latLng).title(branchName));
    }
}
