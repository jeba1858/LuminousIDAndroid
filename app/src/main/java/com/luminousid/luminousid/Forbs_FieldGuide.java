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
    // And a little from here http://stackoverflow.com/questions/38284915/populate-listview-with-firebase-adapter
    // But just check out the Firebase UI library. Good stuff.

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forbs_field_guide);

        // Get firebase reference for just forbs.
        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        ListView mListView = (ListView) findViewById(R.id.fieldguideListView);

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
