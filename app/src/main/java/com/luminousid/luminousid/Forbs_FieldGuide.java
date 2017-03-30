package com.luminousid.luminousid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Forbs_FieldGuide extends AppCompatActivity {

    // Placing Firebase data into listview
    // Taken from Stackoverflow http://stackoverflow.com/questions/41434475/how-to-list-data-from-firebase-database-in-listview
    // Or maybe from here http://stackoverflow.com/questions/38284915/populate-listview-with-firebase-adapter

    private ArrayList<speciesName> speciesArray = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forbs_field_guide);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        // DatabaseReference forbs = mDatabase.child("speciesid").child("field_guide").child("forbs");
        // DatabaseReference forbsRef = new FirebaseDatabase("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        ListView mListView = (ListView) findViewById(R.id.fieldguideListView);

        /*
        ArrayAdapter<speciesName> speciesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, speciesArray);
        mListView.setAdapter(speciesAdapter);

        forbs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapShot : dataSnapshot.getChildren()){

                    String species_name = (String) childSnapShot.child("species_name").getValue();
                    String common_name = (String) childSnapShot.child("common_name").getValue();

                    speciesName speciesnameobj = new speciesName(species_name, common_name);

                    speciesArray.add(speciesnameobj);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError);
            }
        });
        */

        // Using Firebase UI library to list all the forbs plants (species name, common name)
        ListAdapter speciesUIAdapter = new FirebaseListAdapter<speciesName>(this, speciesName.class, android.R.layout.two_line_list_item, forbsRef)
        {
            @Override
            protected void populateView(View view, speciesName speciesnameobj, int position)
            {
                TextView speciesnameText = (TextView) view.findViewById(android.R.id.text1);
                speciesnameText.setText(speciesnameobj.getSpecies_name());
                speciesnameText.setTextColor(Color.WHITE);
                // Set size relative to screen size. (SP unit)
                speciesnameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

                TextView commonnameText = (TextView) view.findViewById(android.R.id.text2);
                commonnameText.setText(speciesnameobj.getCommon_name());
                commonnameText.setTextColor(Color.WHITE);
                // Set size relative to screen size. (SP unit)
                commonnameText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            }
        };
        mListView.setAdapter(speciesUIAdapter);


    }

}
