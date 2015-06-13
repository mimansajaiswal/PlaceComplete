package com.example.google.playservices.placecomplete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;
//import com.example.google.playservices.placecomplete.MyFragmentPageAdapter;


public class ShowDet extends FragmentActivity {
    public static JSONArray jsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        try {
            String s=intent.getStringExtra("JSONArray");
            JSONObject jsao= new JSONObject(s);
            jsa=(JSONArray)jsao.get("data");
            //jsa = new JSONArray(intent.getStringExtra("JSONArray"));
            //String s="{\"data\":[{\"0\":[{\"sex\":\"Male\",\"destination\":\"Akshardham, New Delhi, Delhi, India\",\"source\":\"IIIT Delhi, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Male\",\"destination\":\"Kalu Sarai, New Delhi, Delhi, India\",\"source\":\"Jia Sarai, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Female\",\"destination\":\"Rewla Khanpur, New Delhi, India\",\"source\":\"Toy City, New Delhi, India\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Jain International Organisation (JIO), Block 59, New Delhi, India\",\"source\":\"Yrttipellontie, Oulu, Finland\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Fshoke Builder, Grand Trunk Road, Ghaziabad, Uttar Pradesh, India\",\"source\":\"Learn Tying Fast, National Highway 91, Madhopura, Ghaziabad, Uttar Pradesh, India\",\"userName\":\"mimansa4\",\"age\":\"19\"}]},{\"1\":[{\"sex\":\"Male\",\"destination\":\"Akshardham, New Delhi, Delhi, India\",\"source\":\"IIIT Delhi, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Male\",\"destination\":\"Kalu Sarai, New Delhi, Delhi, India\",\"source\":\"Jia Sarai, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Female\",\"destination\":\"Rewla Khanpur, New Delhi, India\",\"source\":\"Toy City, New Delhi, India\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Jain International Organisation (JIO), Block 59, New Delhi, India\",\"source\":\"Yrttipellontie, Oulu, Finland\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Fshoke Builder, Grand Trunk Road, Ghaziabad, Uttar Pradesh, India\",\"source\":\"Learn Tying Fast, National Highway 91, Madhopura, Ghaziabad, Uttar Pradesh, India\",\"userName\":\"mimansa4\",\"age\":\"19\"}]},{\"2\":[{\"sex\":\"Male\",\"destination\":\"Akshardham, New Delhi, Delhi, India\",\"source\":\"IIIT Delhi, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Male\",\"destination\":\"Kalu Sarai, New Delhi, Delhi, India\",\"source\":\"Jia Sarai, New Delhi, Delhi, India\",\"userName\":\"kireet5\",\"age\":\"18\"},{\"sex\":\"Female\",\"destination\":\"Rewla Khanpur, New Delhi, India\",\"source\":\"Toy City, New Delhi, India\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Jain International Organisation (JIO), Block 59, New Delhi, India\",\"source\":\"Yrttipellontie, Oulu, Finland\",\"userName\":\"mimansa4\",\"age\":\"19\"},{\"sex\":\"Female\",\"destination\":\"Fshoke Builder, Grand Trunk Road, Ghaziabad, Uttar Pradesh, India\",\"source\":\"Learn Tying Fast, National Highway 91, Madhopura, Ghaziabad, Uttar Pradesh, India\",\"userName\":\"mimansa4\",\"age\":\"19\"}]}]}";
            //JSONObject jsao= new JSONObject(s);
            //jsa=(JSONArray)jsao.get("data");
        }
        catch (Exception e)
        {

            System.out.println("s /n"+"Array Problem"+e.toString());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_det);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        /** Getting fragment manager */
        FragmentManager fm = getSupportFragmentManager();

        /** Instantiating FragmentPagerAdapter */
        MyFragmentPageAdapter pageAdapter= new MyFragmentPageAdapter(fm);


        /** Setting the pagerAdapter to the pager object */
        pager.setAdapter(pageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_det, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
