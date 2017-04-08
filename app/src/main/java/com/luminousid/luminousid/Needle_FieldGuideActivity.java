package com.luminousid.luminousid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.layout.speciesnamelist;

public class Needle_FieldGuideActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // Placing Firebase data into listview
    // Taken from Stackoverflow http://stackoverflow.com/questions/41434475/how-to-list-data-from-firebase-database-in-listview
    // And a little from here http://stackoverflow.com/questions/38284915/populate-listview-with-firebase-adapter
    // But just check out the Firebase UI library. Good stuff.

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needle_field_guide);

        // Get firebase reference for just needles.
        DatabaseReference needleRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/needle");
        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // Using Firebase UI library to list all the needle plants (species name, common name)
        FirebaseRecyclerAdapter speciesUIAdapter = new FirebaseRecyclerAdapter<needleDetails, needleHolder>(needleDetails.class, speciesnamelist, needleHolder.class, needleRef)
        {
            @Override
            protected void populateViewHolder(needleHolder holder, needleDetails speciesnameobj, int position)
            {

                holder.bindPlant(speciesnameobj);

            }
        };

        mRecyclerView.setAdapter(speciesUIAdapter);


    }
}
