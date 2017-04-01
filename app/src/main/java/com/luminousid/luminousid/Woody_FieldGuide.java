package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.luminousid.luminousid.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Woody_FieldGuide extends AppCompatActivity implements View.OnClickListener {

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woody__field_guide);

        findViewById(R.id.deciduousButton).setOnClickListener(this);
        findViewById(R.id.needleButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if(i == R.id.deciduousButton) {
            gotoDeciduous();
        }

        else if(i == R.id.needleButton) {
            gotoNeedle();
        }
    }

    public void gotoDeciduous(){
        Intent intent = new Intent(Woody_FieldGuide.this, Deciduous_FieldGuide.class);
        startActivity(intent);
    }

    public void gotoNeedle(){
        Intent intent = new Intent(Woody_FieldGuide.this, Needle_FieldGuide.class);
        startActivity(intent);
    }
}
