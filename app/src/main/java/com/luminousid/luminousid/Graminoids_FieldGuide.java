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

public class Graminoids_FieldGuide extends AppCompatActivity {

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
        setContentView(R.layout.activity_graminoids__field_guide);

        // Get firebase reference for cyperceae.
        DatabaseReference cyperRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/graminoids/cyperaceae");
        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        // Set layout manager. Use the custom item divider, plantdivider class.
        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        // Using Firebase UI library to list all the graminoid(cyperceae) plants (species name, common name)
        FirebaseRecyclerAdapter speciesUIAdapter = new FirebaseRecyclerAdapter<speciesName, graminoidsHolder>(speciesName.class, speciesnamelist, graminoidsHolder.class, cyperRef)
        {
            @Override
            protected void populateViewHolder(graminoidsHolder holder, speciesName speciesnameobj, int position)
            {

                holder.bindPlant(speciesnameobj);

            }
        };

        mRecyclerView.setAdapter(speciesUIAdapter);


    }
}
