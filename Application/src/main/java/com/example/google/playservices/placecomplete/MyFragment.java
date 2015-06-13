package com.example.google.playservices.placecomplete;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MyFragment extends Fragment {

    private static final String TAG ="Testing: " ;
    int mCurrentPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Getting the arguments to the Bundle object */
        //Bundle data = getArguments();

        /** Getting integer data of the key current_page from the bundle */
        //mCurrentPage = data.getInt("current_page", 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myfragment_layout, container,false);
       //extView tv = (TextView) v.findViewById(R.id.tv);
        // dp=new DataParsing();
        //MainActivity ma=new MainActivity();
        //String data=parse(mCurrentPage);

        //tv.setText("Hello There!");
        return v;
    }


}
