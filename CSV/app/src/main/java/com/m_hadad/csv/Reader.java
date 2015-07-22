package com.m_hadad.csv;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by M-Hadad on 21-Jul-15.
 */

public class Reader extends AsyncTask<Void, Void, Void>{
    TextView tv;
    AssetManager assets;
    public Reader(TextView tv, AssetManager assets) {
        Log.e("CSV"," in csv");
        this.tv = tv;
        this.assets = assets;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.e("CSV ", "In csv reader");
        try {


            InputStream csvStream = assets.open("test.csv");
            InputStreamReader csvStreamReader = new InputStreamReader(csvStream);
            CsvReader products = new CsvReader(csvStreamReader);


            products.readHeaders();

            while (products.readRecord())
            {
                String Branchname = products.get("Branch name");
                int lat = Integer.parseInt(products.get("lat"));
                int long_ =Integer.parseInt(products.get("long") );
                // perform program logic here

                tv.setText(tv.getText() + "\n" + Branchname);
            }

            products.close();

        } catch (FileNotFoundException e) {
            Log.e("CSV ","FileNotFoundException " + e.toString());
        } catch (IOException e) {
            Log.e("CSV ","IOException " + e.toString());
        }


        return null;
    }
}
