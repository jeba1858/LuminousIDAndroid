package com.luminousid.luminousid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.layout.speciesnamelist;

// This code is inspired by https://www.learnhowtoprogram.com/android/data-persistence/firebase-recycleradapter
// This code lists all the forbs plants in a recycler list view.
// It will display the species name, common name, and the thumbnail image.

public class Forbs_FieldGuide extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

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
        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // Using Firebase UI library to list all the forbs plants (species name, common name)
        FirebaseRecyclerAdapter speciesUIAdapter = new FirebaseRecyclerAdapter<speciesName, plantHolder>(speciesName.class, speciesnamelist, plantHolder.class, forbsRef)
        {
            @Override
            protected void populateViewHolder(plantHolder holder, speciesName speciesnameobj, int position)
            {

                holder.bindPlant(speciesnameobj);

            }
        };

        mRecyclerView.setAdapter(speciesUIAdapter);


    }

}
