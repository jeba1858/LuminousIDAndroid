package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.logoutButton;

public class Home_screenActivity extends AppCompatActivity implements View.OnClickListener {

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Get snapshot of Field Guide database
        System.out.println("Attempting to take snapshot");
        takeFieldGuideSnapshot();
        System.out.println("Snapshot taken");
        System.out.println("Size: " + PlantArrayManager.getInstance().getglobalForbsArray().size());

        // Buttons on screen
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);

        findViewById(R.id.fieldguideButton).setOnClickListener(this);
        findViewById(R.id.observationsButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);
        findViewById(R.id.glossaryButton).setOnClickListener(this);
        findViewById(R.id.aboutButton).setOnClickListener(this);

        //Textview for Login Status
        TextView loginStatus = (TextView) findViewById(R.id.loginStatus);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            loginStatus.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        else{
            loginStatus.setText("Guest");
            logoutButton.setText("Log-In/Sign-up");
        }

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            System.out.println("Currently signed in: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

    }

    public void onStart(){
        super.onStart();

        // Get snapshot of Field Guide database
        System.out.println("Attempting to take snapshot");
        takeFieldGuideSnapshot();
        System.out.println("Snapshot taken");
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        // Check what button user presses, go there.

        if(i == logoutButton){

            if(FirebaseAuth.getInstance().getCurrentUser() != null){
                FirebaseAuth.getInstance().signOut();
                System.out.println("Previous user signed out.");
                gotoOpen();
            }
            else{
                gotoOpen();
            }

        }

        else if(i == R.id.aboutButton){
            gotoAbout();
        }

        else if(i == R.id.fieldguideButton){
            gotoFieldGuide();
        }


    }

    public void gotoOpen(){
        Intent intent = new Intent(Home_screenActivity.this, open_screenActivity.class);
        startActivity(intent);
        super.finish();
    }

    public void gotoAbout(){
        Intent intent = new Intent(Home_screenActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void gotoFieldGuide(){
        Intent intent = new Intent(Home_screenActivity.this, Intro_FieldGuideActivity.class);
        startActivity(intent);
    }


    public void takeFieldGuideSnapshot(){

        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        DatabaseReference cyperRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/cyperaceae");
        DatabaseReference juncaRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/juncaceae");
        DatabaseReference poaRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/poaceae");
        DatabaseReference deciRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/deciduous");
        DatabaseReference needleRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/needle");


        forbsRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<forbsDetails> newForbArray = new ArrayList<forbsDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newForbArray.add(snapshot.getValue(forbsDetails.class));
                }

                PlantArrayManager.getInstance().setglobalForbsArray(newForbArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cyperRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<cyperaceaeDetails> newCyperArray = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newCyperArray.add(snapshot.getValue(cyperaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalCyperArray(newCyperArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        juncaRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<juncaceaeDetails> newJuncaArray = new ArrayList<juncaceaeDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newJuncaArray.add(snapshot.getValue(juncaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalJuncaArray(newJuncaArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        poaRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<poaceaeDetails> newPoaArray = new ArrayList<poaceaeDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newPoaArray.add(snapshot.getValue(poaceaeDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalPoaArray(newPoaArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        deciRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<deciduousDetails> newDeciArray = new ArrayList<deciduousDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newDeciArray.add(snapshot.getValue(deciduousDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalDeciArray(newDeciArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        needleRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<needleDetails> newNeedleArray = new ArrayList<needleDetails>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println("Snapshot: " + snapshot);
                    newNeedleArray.add(snapshot.getValue(needleDetails.class));
                }

                PlantArrayManager.getInstance().setGlobalNeedleArray(newNeedleArray);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
