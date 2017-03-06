package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;

import android.content.ContentValues;
import android.content.res.AssetManager;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.luminousid.luminousid.localFieldGuideContract.*;

public class DBTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        // Need a cursor to query from database
        Cursor queryCursor = null;

        // Create our Database Helper
        FG_DB_Helper dbHelper = new FG_DB_Helper(getApplicationContext());

        // Gets Database. If it doesn't exist, creates a new one.
        SQLiteDatabase fieldGuideDB = dbHelper.getWritableDatabase();

        // Species Number totals
        int FORBS_TOTAL         = 97;

        int GRAM_TOTAL          = 42;
        int JUNACEAE_TOTAL      = 7;
        int CYPERCEAE_TOTAL     = 25;
        int POACEAE_TOTAL       = 10;

        int WOODY_TOTAL         = 27;
        int NEEDLE_TOTAL        = 8;
        int DECIDUOUS_TOTAL     = 18;

        int PHOTO_TOTAL         = 167;


        // Test indexes for file loading
        int forbsIndexTest      = 0;

        int gramIndexTest       = 0;
        int junaceaeIndexTest   = 0;
        int cyperceaeIndexTest  = 0;
        int poaceaeIndexTest    = 0;

        int woodyIndexTest      = 0;
        int needleIndexTest     = 0;
        int deciduousIndexTest  = 0;

        int photoIndexTest      = 0;

        // Fill Database with given .csv files.
        // Grabs .csv files from assets folder (main->assets)

        /*
        String forbsCSVfile         = "forbs_species_rawdata.csv";

        String gramCSVfile          = "gram_table_rawdata.csv";
        String junaceaeCSVfile      = "junaceae_species_rawdata.csv";
        String cyperceaeCSVfile     = "cyperceae_species_rawdata.csv";
        String poaceaeCSVfile       = "poaceae_species_rawdata.csv";

        String woodyCSVfile         = "woody_table_rawdata.csv";
        String needleCSVfile        = "needle_species_rawdata.csv";
        String deciduousCSVfile     = "deciduous_species_rawdata.csv";

        String photoCSVfile         = "fg_photos_rawdata.csv";

        AssetManager csvmanager = dbHelper.context.getAssets();

        InputStream forbsStream         = null;

        InputStream gramsStream         = null;
        InputStream cyperceaeStream     = null;
        InputStream junaceaeStream      = null;
        InputStream poaceaeStream       = null;

        InputStream woodyStream         = null;
        InputStream needleStream        = null;
        InputStream deciduousStream     = null;

        InputStream photoStream        = null;

        try {
            forbsStream         = csvmanager.open(forbsCSVfile);

            gramsStream         = csvmanager.open(gramCSVfile);
            cyperceaeStream     = csvmanager.open(cyperceaeCSVfile);
            junaceaeStream      = csvmanager.open(junaceaeCSVfile);
            poaceaeStream       = csvmanager.open(poaceaeCSVfile);

            woodyStream         = csvmanager.open(woodyCSVfile);
            needleStream        = csvmanager.open(needleCSVfile);
            deciduousStream     = csvmanager.open(deciduousCSVfile);

            photoStream         = csvmanager.open(photoCSVfile);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot load CSVs");
        }

        // Start with forbs .csv file
        BufferedReader forbsbuffer = new BufferedReader(new InputStreamReader(forbsStream));
        String line = "";
        fieldGuideDB.beginTransaction();
        try {
            while ((line = forbsbuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.forbs.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.forbs.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.forbs.KEY_PLANT_CODE, colums[2].trim());
                cv.put(localFieldGuideContract.forbs.KEY_COMMON_NAME, colums[3].trim());
                cv.put(localFieldGuideContract.forbs.KEY_FAMILY_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.forbs.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.forbs.KEY_FLOWER_COLOR, colums[6].trim());
                cv.put(localFieldGuideContract.forbs.KEY_PETAL_NUMBER, colums[7].trim());
                cv.put(localFieldGuideContract.forbs.KEY_FLOWER_SHAPE, colums[8].trim());
                cv.put(localFieldGuideContract.forbs.KEY_LEAF_ARRANGEMENT, colums[9].trim());
                cv.put(localFieldGuideContract.forbs.KEY_LEAF_SHAPE_FILTER, colums[10].trim());
                cv.put(localFieldGuideContract.forbs.KEY_HABITAT, colums[11].trim());
                cv.put(localFieldGuideContract.forbs.KEY_NOTES, colums[12].trim());
                cv.put(localFieldGuideContract.forbs.KEY_PHOTO_CREDIT, colums[13].trim());

                fieldGuideDB.insert(localFieldGuideContract.forbs.TABLE_NAME, null, cv);

                forbsIndexTest++;
                System.out.println("Forbs: Loaded " + forbsIndexTest + " out of " + FORBS_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + forbsIndexTest);
        }

        // Graminoids Table population
        BufferedReader gramsbuffer = new BufferedReader(new InputStreamReader(gramsStream));
        try {
            while ((line = gramsbuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.graminoids.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.graminoids.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.graminoids.KEY_FAMILY_NAME, colums[2].trim());

                fieldGuideDB.insert(localFieldGuideContract.graminoids.TABLE_NAME, null, cv);

                gramIndexTest++;
                System.out.println("Graminoids: Loaded " + gramIndexTest + " out of " + GRAM_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + gramIndexTest);
        }

        // Cyperceae Species population
        BufferedReader cyperceaebuffer = new BufferedReader(new InputStreamReader(cyperceaeStream));
        try {
            while ((line = cyperceaebuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.cyperceae.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_FAMILY_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_PLANT_CODE, colums[3].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_COMMON_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_STEM_CROSS_SECTION, colums[6].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_LEAF_BLADE, colums[7].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_INFLORESCENCE, colums[8].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_SPIKE_COLOR, colums[9].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_HABITAT, colums[10].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_NOTES, colums[11].trim());
                cv.put(localFieldGuideContract.cyperceae.KEY_PHOTO_CREDIT, colums[12].trim());

                fieldGuideDB.insert(localFieldGuideContract.cyperceae.TABLE_NAME, null, cv);

                cyperceaeIndexTest++;
                System.out.println("Cyperceae: Loaded " + cyperceaeIndexTest + " out of " + CYPERCEAE_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + cyperceaeIndexTest);
        }

        // Junaceae Species population
        BufferedReader junaceaebuffer = new BufferedReader(new InputStreamReader(junaceaeStream));
        try {
            while ((line = junaceaebuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.juncaceae.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_FAMILY_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_PLANT_CODE, colums[3].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_COMMON_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_STEM_CROSS_SECTION, colums[6].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_LEAF_BLADE, colums[7].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_HABITAT, colums[8].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_NOTES, colums[9].trim());
                cv.put(localFieldGuideContract.juncaceae.KEY_PHOTO_CREDIT, colums[10].trim());

                fieldGuideDB.insert(localFieldGuideContract.juncaceae.TABLE_NAME, null, cv);

                junaceaeIndexTest++;
                System.out.println("Junaceae: Loaded " + junaceaeIndexTest + " out of " + JUNACEAE_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + junaceaeIndexTest);
        }

        // Poaceae Species Population
        BufferedReader poaceaebuffer = new BufferedReader(new InputStreamReader(poaceaeStream));
        try {
            while ((line = poaceaebuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.poaceae.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_FAMILY_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_PLANT_CODE, colums[3].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_COMMON_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_STEM_CROSS_SECTION, colums[6].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_LEAF_BLADE, colums[7].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_INFLORESCENCE, colums[8].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_FLORETS_PER_SPIKELET, colums[9].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_AWNS, colums[10].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_HABITAT, colums[11].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_NOTES, colums[12].trim());
                cv.put(localFieldGuideContract.poaceae.KEY_PHOTO_CREDIT, colums[13].trim());

                fieldGuideDB.insert(localFieldGuideContract.poaceae.TABLE_NAME, null, cv);

                poaceaeIndexTest++;
                System.out.println("Poaceae: Loaded " + poaceaeIndexTest + " out of " + POACEAE_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + poaceaeIndexTest);
        }

        // Then we do the woody table
        BufferedReader woodybuffer = new BufferedReader(new InputStreamReader(woodyStream));
        try {
            while ((line = woodybuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.woody.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.woody.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.woody.KEY_LEAF_TYPE, colums[2].trim());

                fieldGuideDB.insert(localFieldGuideContract.woody.TABLE_NAME, null, cv);

                woodyIndexTest++;
                System.out.println("Woody: Loaded " + woodyIndexTest + " out of " + WOODY_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + woodyIndexTest);
        }

        // Deciduous Species population
        BufferedReader deciduousbuffer = new BufferedReader(new InputStreamReader(deciduousStream));
        try {
            while ((line = deciduousbuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.deciduous.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_FAMILY_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_PLANT_CODE, colums[3].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_COMMON_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_LEAF_TYPE, colums[6].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_CONE, colums[7].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_LEAF_MARGIN, colums[8].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_LEAF_SHAPE, colums[9].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_LEAF_ARRANGEMENT, colums[10].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_NOTES, colums[11].trim());
                cv.put(localFieldGuideContract.deciduous.KEY_PHOTO_CREDIT, colums[12].trim());

                fieldGuideDB.insert(localFieldGuideContract.deciduous.TABLE_NAME, null, cv);

                deciduousIndexTest++;
                System.out.println("Deciduous: Loaded " + deciduousIndexTest + " out of " + DECIDUOUS_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + deciduousIndexTest);
        }

        // Needle Species population
        BufferedReader needlebuffer = new BufferedReader(new InputStreamReader(needleStream));
        try {
            while ((line = needlebuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.needle.KEY_SPECIES_NAME, colums[0].trim());
                cv.put(localFieldGuideContract.needle.KEY_GROWTH_FORM, colums[1].trim());
                cv.put(localFieldGuideContract.needle.KEY_FAMILY_NAME, colums[2].trim());
                cv.put(localFieldGuideContract.needle.KEY_PLANT_CODE, colums[3].trim());
                cv.put(localFieldGuideContract.needle.KEY_COMMON_NAME, colums[4].trim());
                cv.put(localFieldGuideContract.needle.KEY_SYNONYMS, colums[5].trim());
                cv.put(localFieldGuideContract.needle.KEY_LEAF_TYPE, colums[6].trim());
                cv.put(localFieldGuideContract.needle.KEY_NEEDLE_ARRANGEMENT, colums[7].trim());
                cv.put(localFieldGuideContract.needle.KEY_NEEDLES_PER_FASCICLE, colums[8].trim());
                cv.put(localFieldGuideContract.needle.KEY_NEEDLE_APEX, colums[9].trim());
                cv.put(localFieldGuideContract.needle.KEY_CONE, colums[10].trim());
                cv.put(localFieldGuideContract.needle.KEY_NOTES, colums[11].trim());
                cv.put(localFieldGuideContract.needle.KEY_PHOTO_CREDIT, colums[12].trim());

                fieldGuideDB.insert(localFieldGuideContract.needle.TABLE_NAME, null, cv);

                needleIndexTest++;
                System.out.println("Needle: Loaded " + needleIndexTest + " out of " + NEEDLE_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + needleIndexTest);
        }

        // Photo table population
        BufferedReader photobuffer = new BufferedReader(new InputStreamReader(photoStream));
        try {
            while ((line = photobuffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length < 1) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues();

                cv.put(localFieldGuideContract.FG_Photos.KEY_PLANT_CODE, colums[0].trim());
                cv.put(localFieldGuideContract.FG_Photos.KEY_PLANT_PHOTO, colums[1].trim());

                fieldGuideDB.insert(localFieldGuideContract.FG_Photos.TABLE_NAME, null, cv);

                photoIndexTest++;
                System.out.println("Photos: Loaded " + photoIndexTest + " out of " + PHOTO_TOTAL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problem with index: " + photoIndexTest);
        }

        // Finally done
        fieldGuideDB.setTransactionSuccessful();
        fieldGuideDB.endTransaction();

        System.out.println("Field Guide Complete");

        */

        // Query database to test
        queryCursor = fieldGuideDB.rawQuery("Select * from Cyperceae", null);
        queryCursor.moveToFirst();

        // We're using text view on screen (xml layout file) to show some basic values of our tables.
        int cyperceaeColumnCount = queryCursor.getColumnCount();
        int cyperceaeRowCount = queryCursor.getCount();

        TextView cyperceaecolumncounttext = (TextView) findViewById(R.id.cyperceaecolumncounttext);
        cyperceaecolumncounttext.setText("The Cyperceae Column count is: " + cyperceaeColumnCount);

        TextView cyperceaerowcounttext = (TextView) findViewById(R.id.cyperceaerowcounttext);
        cyperceaerowcounttext.setText("The Cyperceae Row count is: " + cyperceaeRowCount + " which should be " + CYPERCEAE_TOTAL);




    }

}



