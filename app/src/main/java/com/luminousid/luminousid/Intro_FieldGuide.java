package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.luminousid.luminousid.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Intro_FieldGuide extends AppCompatActivity implements View.OnClickListener {

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__field_guide);

        findViewById(R.id.forbsButton).setOnClickListener(this);
        findViewById(R.id.graminoidsButton).setOnClickListener(this);
        findViewById(R.id.woodyButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == R.id.forbsButton){
            gotoForbs();
        }

        else if(i == R.id.graminoidsButton){
            gotoGraminoids();
        }

        else if(i == R.id.woodyButton){
            gotoWoody();
        }
    }

    public void gotoForbs(){
        Intent intent = new Intent(Intro_FieldGuide.this, Forbs_FieldGuide.class);
        startActivity(intent);
    }

    public void gotoGraminoids(){
        Intent intent = new Intent(Intro_FieldGuide.this, Graminoids_FieldGuide.class);
        startActivity(intent);
    }

    public void gotoWoody(){
        Intent intent = new Intent(Intro_FieldGuide.this, Woody_FieldGuide.class);
        startActivity(intent);
    }
}
