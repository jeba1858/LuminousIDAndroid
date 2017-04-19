package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.filterButton;
import static com.luminousid.luminousid.R.layout.speciesnamelist;

public class Needle_FieldGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // So we can close this later.
    public static Needle_FieldGuideActivity needleFGObj;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needle_field_guide);

        // For closing this later
        needleFGObj = this;

        // If coming from filters, get all the filter category values
        Intent previousIntent = getIntent();
        String filterStatus = previousIntent.getStringExtra("filterStatus");
        String family = previousIntent.getStringExtra("family");
        String leaftype = previousIntent.getStringExtra("leafType");
        String needlearrangement = previousIntent.getStringExtra("needleArrangement");
        String needlesperfascicle = previousIntent.getStringExtra("needlesperFascicle");
        String needleapex = previousIntent.getStringExtra("needleApex");
        String cone = previousIntent.getStringExtra("cone");

        // Set filter button on screen
        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(this);

        // Get firebase reference for just needles.
        DatabaseReference needleRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/woody/needle");
        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        ArrayList<needleDetails> tempNeedleArray = PlantArrayManager.getInstance().getGlobalNeedleArray();

        // If the last screen was the filter options, we then attempt to filter with the chosen categories.
        // Else, just populate the screen with the global needle array.
        if(filterStatus == null){
            System.out.println("No filters selected, using global array");
            needleFilterableAdapter speciesFilterAdapter = new needleFilterableAdapter(this, tempNeedleArray);
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
        else {
            System.out.println("Filters selected, attempting to filter the array");
            ArrayList<needleDetails> filteredNeedleArray = FilterPopulate(tempNeedleArray, family,
                    leaftype, needlearrangement, needlesperfascicle, needleapex, cone);
            needleFilterableAdapter speciesFilterAdapter = new needleFilterableAdapter(this, filteredNeedleArray);
            speciesFilterAdapter.notifyItemRangeChanged(0, speciesFilterAdapter.getItemCount());
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

    public ArrayList<needleDetails> FilterPopulate(ArrayList<needleDetails> filterNeedleArray, final String family,
                                                       final String leaftype, final String needlearrangement,
                                                       final String needlesperfascicle, final String needleapex, final String cone){

        ArrayList<needleDetails> filteredNeedleArray = new ArrayList<>();

        System.out.println("Attempting to start filtering");

        for(int i = 0; i < filterNeedleArray.size(); i++){

            System.out.println("Filtering plant at index: " + i);

            needleDetails tofilterNeedle = filterNeedleArray.get(i);

            if(!family.equalsIgnoreCase("All") && !tofilterNeedle.getFamily_name().toLowerCase().contains(family.toLowerCase())){
                // Do nothing
            }
            else if(!leaftype.equalsIgnoreCase("All") && !tofilterNeedle.getLeaf_type().toLowerCase().contains(leaftype.toLowerCase())){
                // Do nothing
            }
            else if(!needlearrangement.equalsIgnoreCase("All") && !tofilterNeedle.getNeedle_arrangement().toLowerCase().contains(needlearrangement.toLowerCase())){
                // Do nothing
            }
            else if(!needlesperfascicle.equalsIgnoreCase("All") && !tofilterNeedle.getNeedle_per_fascile().toLowerCase().contains(needlesperfascicle.toLowerCase())){
                // Do nothing
            }
            else if(!needleapex.equalsIgnoreCase("All") && !tofilterNeedle.getNeedle_apex().toLowerCase().contains(needleapex.toLowerCase())){
                // Do nothing
            }
            else if(!cone.equalsIgnoreCase("All") && !tofilterNeedle.getCone().toLowerCase().contains(cone.toLowerCase())){
                // Do nothing
            }
            else{
                filteredNeedleArray.add(tofilterNeedle);
            }

        }

        System.out.println("Returning filtered array");
        return filteredNeedleArray;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == filterButton){
            gotoFilter();
        }

    }

    public void gotoFilter(){
        Intent intent = new Intent(Needle_FieldGuideActivity.this, PlantFilterActivity.class);
        intent.putExtra("plantType", "needle");
        startActivity(intent);
    }
}
