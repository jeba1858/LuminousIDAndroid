package com.luminousid.luminousid;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_COMMON_NAME;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_FAMILY_NAME;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_FLOWER_COLOR;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_FLOWER_SHAPE;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_GROWTH_FORM;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_HABITAT;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_LEAF_ARRANGEMENT;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_LEAF_SHAPE_FILTER;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_NOTES;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_PETAL_NUMBER;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_PHOTO_CREDIT;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_PLANT_CODE;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_SPECIES_NAME;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.KEY_SYNONYMS;
import static com.luminousid.luminousid.localFieldGuideContract.forbs.TABLE_NAME;

public class open_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "LoginActivity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create our Database Helper
        FG_DB_Helper dbHelper = new FG_DB_Helper(getApplicationContext());

        // Gets Database. If it doesn't exist, creates a new one.
        SQLiteDatabase fieldGuideDB = dbHelper.getWritableDatabase();

        // Fill Database with given .csv files.
        // Grabs .csv files from assets folder (main->assets)

        String forbsCSVfile = "forbs_species_rawdata.csv";
        String gramCSVfile = "grams_species_rawdata.csv";
        String woodyCSVfile = "woody_species_rawdata.csv";

        AssetManager csvmanager = dbHelper.context.getAssets();
        InputStream forbsStream = null;
        InputStream gramsStream = null;
        InputStream woodyStream = null;

        try {
            forbsStream = csvmanager.open(forbsCSVfile);
            gramsStream = csvmanager.open(gramCSVfile);
            woodyStream = csvmanager.open(woodyCSVfile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start with forbs .csv file
        BufferedReader forbsbuffer = new BufferedReader(new InputStreamReader(forbsStream));
        String line = "";
        fieldGuideDB.beginTransaction();
            try {
                while ((line = forbsbuffer.readLine()) != null) {
                    String[] colums = line.split(",");
                        if (colums.length != 13) {
                            Log.d("CSVParser", "Skipping Bad CSV Row");
                            continue;
                        }
                        ContentValues cv = new ContentValues();
                        cv.put(KEY_GROWTH_FORM, colums[0].trim());
                        cv.put(KEY_PLANT_CODE, colums[1].trim());
                        cv.put(KEY_SPECIES_NAME, colums[2].trim());
                        cv.put(KEY_COMMON_NAME, colums[3].trim());
                        cv.put(KEY_FAMILY_NAME, colums[4].trim());
                        cv.put(KEY_SYNONYMS, colums[5].trim());
                        cv.put(KEY_FLOWER_COLOR, colums[6].trim());
                        cv.put(KEY_PETAL_NUMBER, colums[7].trim());
                        cv.put(KEY_FLOWER_SHAPE, colums[8].trim());
                        cv.put(KEY_LEAF_ARRANGEMENT, colums[9].trim());
                        cv.put(KEY_LEAF_SHAPE_FILTER, colums[10].trim());
                        cv.put(KEY_HABITAT, colums[11].trim());
                        cv.put(KEY_NOTES, colums[12].trim());
                        cv.put(KEY_PHOTO_CREDIT, colums[13].trim());
                        fieldGuideDB.insert(TABLE_NAME, null, cv);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        // Then we do Graminoids
        BufferedReader gramsbuffer = new BufferedReader(new InputStreamReader(gramsStream));
        try {
            while ((line = gramsbuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 15) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();
                cv.put(localFieldGuideContract.graminoids.KEY_GROWTH_FORM, colums[0].trim());
                cv.put(localFieldGuideContract.graminoids.KEY_SPECIES_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.graminoids.KEY_FAMILY_NAME, colums[4].trim());
                fieldGuideDB.insert(localFieldGuideContract.graminoids.TABLE_NAME, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Then we do the woody table
        BufferedReader woodybuffer = new BufferedReader(new InputStreamReader(woodyStream));
        try {
            while ((line = woodybuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 15) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();
                cv.put(localFieldGuideContract.woody.KEY_GROWTH_FORM, colums[0].trim());
                cv.put(localFieldGuideContract.woody.KEY_SPECIES_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.woody.KEY_LEAF_TYPE, colums[6].trim());
                fieldGuideDB.insert(localFieldGuideContract.woody.TABLE_NAME, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Finally done
        fieldGuideDB.setTransactionSuccessful();
        fieldGuideDB.endTransaction();
    }

    //

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
