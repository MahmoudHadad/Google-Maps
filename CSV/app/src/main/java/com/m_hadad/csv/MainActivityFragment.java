package com.m_hadad.csv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Log.e("CSV"," view");
        TextView t1 = (TextView)view.findViewById(R.id.t1);
        t1.setText("Text changed");

        new Reader(t1, getActivity().getAssets()).execute();

        return view;
    }
}
