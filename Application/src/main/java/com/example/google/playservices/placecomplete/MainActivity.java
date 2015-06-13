/*
 * Copyright (C) 2015 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.google.playservices.placecomplete;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import com.example.android.common.activities.SampleActivityBase;
import com.example.android.common.logger.Log;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

public class MainActivity extends SampleActivityBase
        implements GoogleApiClient.OnConnectionFailedListener {

    /**
     * GoogleApiClient wraps our service connection to Google Play Services and provides access
     * to the user's sign in state as well as the Google's APIs.
     */
    protected GoogleApiClient mGoogleApiClient;

    private PlaceAutocompleteAdapter mAdapter;

    private AutoCompleteTextView mAutocompleteView;

    private TextView mPlaceDetailsText;

    private TextView mPlaceDetailsAttribution;

    private static final LatLngBounds BOUNDS_GREATER_DELHI = new LatLngBounds(
            new LatLng(28.2754, 76.3948), new LatLng(29.0129, 77.7901));
    String origin="";
    String destination="";
    String rs="seek";
    String time="";

    //EditText tv;
    TextView jdt;
    Button o;
    //Button d;

    public static String uname="";
    String nl="now";
    String sll="";
    String dll="";
    Integer c=0;
    String ll="";
    Button s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        uname = intent.getStringExtra(UserLogin.MESS);

        // Construct a GoogleApiClient for the {@link Places#GEO_DATA_API} using AutoManage
        // functionality, which automatically sets up the API client to handle Activity lifecycle
        // events. If your activity does not extend FragmentActivity, make sure to call connect()
        // and disconnect() explicitly.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        setContentView(R.layout.activity_main);

        // Retrieve the AutoCompleteTextView that will display Place suggestions.
        mAutocompleteView = (AutoCompleteTextView)
                findViewById(R.id.autocomplete_places);

        // Register a listener that receives callbacks when a suggestion has been selected
        mAutocompleteView.setOnItemClickListener(mAutocompleteClickListener);

        // Retrieve the TextViews that will display details and attributions of the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceDetailsAttribution = (TextView) findViewById(R.id.place_attribution);

        // Set up the adapter that will retrieve suggestions from the Places Geo Data API that cover
        // the entire world.
        mAdapter = new PlaceAutocompleteAdapter(this, android.R.layout.simple_list_item_1,
                mGoogleApiClient, BOUNDS_GREATER_DELHI, null);
        mAutocompleteView.setAdapter(mAdapter);

        //tv=(EditText) findViewById(R.id.timev);
        jdt=(TextView) findViewById(R.id.finalj);
        o=(Button) findViewById(R.id.button_set);
        s=(Button) findViewById(R.id.button5);
        //d=(Button) findViewById(R.id.button_des);
        //d.setEnabled(false);
        //setO();
        c=0;
        o.setEnabled(false);
        s.setEnabled(false);
    }

    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     *
     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
     * String...)
     */
    //Set Origin
    /*public void setO() {
        origin = "Terminal 1, Indira Gandhi International Airport, New Delhi, Delhi, India";
        sll = "28.562696,77.119783";
        c++;

        Toast.makeText(this, "The origin is set to Terminal 1, Indira Gandhi International Airport, New Delhi, Delhi, India", Toast.LENGTH_SHORT);
        o.setEnabled(false);
        d.setEnabled(true);
    }*/
    public void set(View view)
    {
        if (c==0)
            CandO();
        else if (c==1)
            CandD();

    }
    public void CandO() {
        AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.autocomplete_places);
        origin = editText.getText().toString();
        editText.setText("");
        Toast.makeText(this,
                origin,
                Toast.LENGTH_LONG).show();
        Log.i(TAG, "Origin details received: " + origin);
        c++;
        //o.setEnabled(false);
        //d.setEnabled(true);
        //sll=ll;
        o.setText("Destination");
        o.setEnabled(false);

        //sll=place.getLatLng().toString();

    }

    public void CandD() {

        AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.autocomplete_places);
        destination = editText.getText().toString();
        editText.setText("");
        Toast.makeText(this,
                destination,
                Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Destination details received: " + destination);
        o.setEnabled(false);
        s.setEnabled(true);
        //dll=ll;
        //dll=place.getLatLng().toString();
    }

    /*public void Drive(View view) {
        rs="drive";
        Toast.makeText(this,
                rs,
                Toast.LENGTH_SHORT).show();

    }

    public void Ride(View view) {
        rs="ride";
        Toast.makeText(this,
                rs,
                Toast.LENGTH_SHORT).show();
    }*/

    public void setNow()
    {
        Calendar c = Calendar.getInstance();
        long seconds = c.getTimeInMillis();
        //tv.setText("" +seconds, TextView.BufferType.EDITABLE);
        //tv.setEnabled(false);
        time=""+seconds;
        nl="now";
    }

    /*public void setTime(View view)
    {
        setNow();

    }*/

    public void jDet(View view)
    {
        /*time=""+tv.getText().toString();
        jdt.setText("You are a " + rs + " in system from " + origin + " to " + destination + " at " + time);*/
        setNow();
        sendD();
        //jdt.setText("Hello");

    }

    public void sendD()
    {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.201:8090/RideSave?source="+URLEncoder.encode(origin)+"&destination="+URLEncoder.encode(destination)+"&timestamp="+URLEncoder.encode(time)+"&userid="+URLEncoder.encode(uname)+"&type="+rs+"&schedule="+nl+"&sourcell="+URLEncoder.encode(sll)+"&destinationll="+URLEncoder.encode(dll);
        Log.i(TAG, url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Log.i(TAG, response.toString());

                        start(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        //Log.i(TAG, "Request send");
        //c=0;
        reset();
    }

    public void start(String a)
    {
        Intent intent = new Intent(this, ShowDet.class);
        intent.putExtra("JSONArray", a);
        startActivity(intent);
        //finish();
    }

    public void reset()
    {
        origin="";
        destination="";
        rs="";
        time="";
        o.setEnabled(false);
        o.setText("Origin");
        //d.setEnabled(false);
        //tv.setText("");
        jdt.setText("");
        //tv.setEnabled(true);
        nl="later";
        sll="";
        dll="";
        c=0;
        s.setEnabled(false);
        //setO();
    }



    private AdapterView.OnItemClickListener mAutocompleteClickListener
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            /*
             Retrieve the place ID of the selected item from the Adapter.
             The adapter stores each Place suggestion in a PlaceAutocomplete object from which we
             read the place ID.
              */
            final PlaceAutocompleteAdapter.PlaceAutocomplete item = mAdapter.getItem(position);
            final String placeId = String.valueOf(item.placeId);
            Log.i(TAG, "Autocomplete item selected: " + item.description);

            /*
             Issue a request to the Places Geo Data API to retrieve a Place object with additional
              details about the place.
              */
            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);

            Toast.makeText(getApplicationContext(), "Clicked: " + item.description,
                    Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Called getPlaceById to get Place details for " + item.placeId);
        }
    };

    /**
     * Callback for results from a Places Geo Data API query that shows the first place result in
     * the details view on screen.
     */
    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
            = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                // Request did not complete successfully
                Log.e(TAG, "Place query did not complete. Error: " + places.getStatus().toString());
                places.release();
                return;
            }
            // Get the Place object from the buffer.
            final Place place = places.get(0);

            // Format details of the place for display and show it in a TextView.
            /*mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(),
                    place.getId(), place.getAddress(), place.getPhoneNumber(),
                    place.getWebsiteUri()));*/

            //String ll=place.getLatLng().toString();
            ll=place.getLatLng().latitude+","+place.getLatLng().longitude;
            // Display the third party attributions if set.
            final CharSequence thirdPartyAttribution = places.getAttributions();
           /* if (thirdPartyAttribution == null) {
                mPlaceDetailsAttribution.setVisibility(View.GONE);
            } else {
                mPlaceDetailsAttribution.setVisibility(View.VISIBLE);
                mPlaceDetailsAttribution.setText(Html.fromHtml(thirdPartyAttribution.toString()));
            }*/
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Log.i(TAG, c.toString());
                    if (c == 0) {
                        sll = ll;
                        Log.i(TAG, sll);
                        o.setEnabled(true);
                    }
                    if (c == 1) {
                        o.setEnabled(true);
                        dll = ll;
                    }
                }
            }, 1800);


            Log.i(TAG, "Place details received: " + place.getName());

            //mPlaceDetailsText.setText(ll);

            places.release();
        }
    };

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
            CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }

    /**
     * Called when the Activity could not connect to Google Play services and the auto manager
     * could resolve the error automatically.
     * In this case the API is not available and notify the user.
     *
     * @param connectionResult can be inspected to determine the cause of the failure
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }



}
