package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class plantDetailActivity extends AppCompatActivity {

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // See what plant list you're coming from.
        // This will allow the activity to display the correct layout with the correct info.
        Intent previousIntent = getIntent();
        String plantType = previousIntent.getStringExtra("plantType").toString();
        System.out.println(plantType);

        if(plantType.equalsIgnoreCase("forbs")){
            setContentView(R.layout.activity_forbs_detail);
        }
        else if(plantType.equalsIgnoreCase("cyperceae")){
            setContentView(R.layout.activity_cyperceae_detail);
        }
        else if(plantType.equalsIgnoreCase("deciduous")){
            setContentView(R.layout.activity_deciduous_detail);
        }
        else if(plantType.equalsIgnoreCase("needle")){
            setContentView(R.layout.activity_needle_detail);
        }
        else{
            setContentView(R.layout.activity_error_detail);
        }
    }
}
