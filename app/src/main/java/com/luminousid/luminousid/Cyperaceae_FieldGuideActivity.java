package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class Cyperaceae_FieldGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private plantDividerItemDecoration mDividerItemDecoration;

    // So we can close this later.
    public static Cyperaceae_FieldGuideActivity cyperFGObj;

    // Get Calibri Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cyperceae_field_guide);

        // Set lock to portrait mode.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // For closing this later
        cyperFGObj = this;

        // If coming from filters, get all the filter category values
        Intent previousIntent = getIntent();
        String filterStatus = previousIntent.getStringExtra("filterStatus");
        String stemcrosssection = previousIntent.getStringExtra("stemcrossSection");
        String leafblade = previousIntent.getStringExtra("leafBlade");
        String inflorescence = previousIntent.getStringExtra("inflorescence");
        String spikecolor = previousIntent.getStringExtra("spikeColor");
        String habitat = previousIntent.getStringExtra("habitat");

        // Set filter button on screen
        Button filterButton = (Button) findViewById(R.id.filterButton);
        filterButton.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.fieldguideListView);
        mRecyclerView.setHasFixedSize(true);

        // Set layout manager. Use the custom item divider, plantdivider class.
        mLayoutManager = new LinearLayoutManager(this);
        mDividerItemDecoration = new plantDividerItemDecoration(this, R.drawable.plantdivider);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        ArrayList<cyperaceaeDetails> tempCyperArray = PlantArrayManager.getInstance().getGlobalCyperArray();

        // If the last screen was the filter options, we then attempt to filter with the chosen categories.
        // Else, just populate the screen with the global cyperaceae array.
        if(filterStatus == null){
            System.out.println("No filters selected, using global array");
            cyperFilterableAdapter speciesFilterAdapter = new cyperFilterableAdapter(this, tempCyperArray);
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }
        else {
            System.out.println("Filters selected, attempting to filter the array");
            ArrayList<cyperaceaeDetails> filteredCyperArray = FilterPopulate(tempCyperArray, stemcrosssection,
                    leafblade, inflorescence, spikecolor, habitat);
            cyperFilterableAdapter speciesFilterAdapter = new cyperFilterableAdapter(this, filteredCyperArray);
            speciesFilterAdapter.notifyItemRangeChanged(0, speciesFilterAdapter.getItemCount());
            mRecyclerView.setAdapter(speciesFilterAdapter);
        }

    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        mRecyclerView.clearDisappearingChildren();
    }

    public ArrayList<cyperaceaeDetails> FilterPopulate(ArrayList<cyperaceaeDetails> filterCyperArray, final String stemcrosssection,
                                                  final String leafblade, final String inflorescence,
                                                  final String spikecolor, final String habitat){

        ArrayList<cyperaceaeDetails> filteredCyperArray = new ArrayList<>();

        System.out.println("Attempting to start filtering");

        for(int i = 0; i < filterCyperArray.size(); i++){

            System.out.println("Filtering plant at index: " + i);

            cyperaceaeDetails tofilterCyper = filterCyperArray.get(i);

            if(!stemcrosssection.equalsIgnoreCase("All") && !tofilterCyper.getStem_cross_section().toLowerCase().contains(stemcrosssection.toLowerCase())){
                // Do nothing
            }
            else if(!leafblade.equalsIgnoreCase("All") && !tofilterCyper.getLeaf_blade().toLowerCase().contains(leafblade.toLowerCase())){
                // Do nothing
            }
            else if(!inflorescence.equalsIgnoreCase("All") && !tofilterCyper.getInflorescence().toLowerCase().contains(inflorescence.toLowerCase())){
                // Do nothing
            }
            else if(!spikecolor.equalsIgnoreCase("All") && !tofilterCyper.getSpike_color().toLowerCase().contains(spikecolor.toLowerCase())){
                // Do nothing
            }
            else if(!habitat.equalsIgnoreCase("All") && !tofilterCyper.getHabitat().toLowerCase().contains(habitat.toLowerCase())){
                // Do nothing
            }
            else{
                filteredCyperArray.add(tofilterCyper);
            }

        }

        System.out.println("Returning filtered array");
        return filteredCyperArray;
    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        if(i == filterButton){
            gotoFilter();
        }

    }

    public void gotoFilter(){
        Intent intent = new Intent(Cyperaceae_FieldGuideActivity.this, PlantFilterActivity.class);
        intent.putExtra("plantType", "cyperaceae");
        startActivity(intent);
    }
}
