package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.parceler.Parcels;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class plantDetailActivity extends AppCompatActivity {

    // Get Snippet Font for page. Check FontHelper class for more info.
    @Override
    protected void attachBaseContext(Context newbase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // See what plant list you're coming from.
        // This will allow the activity to display the correct layout with the correct info.
        Intent previousIntent = getIntent();
        String plantType = previousIntent.getStringExtra("plantType");
        //System.out.println(plantType);

        // Firebase Storage references for the pictures.
        FirebaseStorage mStorage = FirebaseStorage.getInstance();
        StorageReference mStorageRef = mStorage.getReferenceFromUrl("gs://speciesid-ca814.appspot.com/");

        if(plantType.equalsIgnoreCase("forbs")){
            setContentView(R.layout.activity_forbs_detail);

            // Plant info unwrapped using Parcel library.
            forbsDetails forbPlant = Parcels.unwrap(previousIntent.getParcelableExtra("plantInfo"));
            String plant_code = forbPlant.getPlant_code();
            String species_name = forbPlant.getSpecies_name();
            String common_name = forbPlant.getCommon_name();
            String family_name = forbPlant.getFamily_name();
            String flower_color = forbPlant.getFlower_color();
            String flower_shape = forbPlant.getFlower_shape();
            String petal_number = forbPlant.getPetal_number();
            String growth_form = forbPlant.getGrowth_form();
            String habitat = forbPlant.getHabitat();
            String leaf_arrangement = forbPlant.getLeaf_arrangement();
            String leaf_shape_filter = forbPlant.getLeaf_shape_filter();
            String notes = forbPlant.getNotes();
            String photo_credit = forbPlant.getPhoto_credit();



            String tempCode = "flower.jpg";

            // Get plant's picture from Storage.
            StorageReference forbsPicRef = mStorageRef.child("field_guide/" + tempCode);

            // Find all text views and the image view.
            ImageView plantPicture = (ImageView) findViewById(R.id.forbsPlantImage);

            TextView forbsSpeciesName = (TextView) findViewById(R.id.forbsSpeciesName);
            TextView forbsPlantCode = (TextView) findViewById(R.id.forbsPlantCode);
            TextView forbsCommonName = (TextView) findViewById(R.id.forbsCommonName);
            TextView forbsSynonyms = (TextView) findViewById(R.id.forbsSynonyms);
            TextView forbsFamilyName = (TextView) findViewById(R.id.forbsFamilyName);
            TextView forbsFlowerColor = (TextView) findViewById(R.id.forbsFlowerColor);
            TextView forbsFlowerShape = (TextView) findViewById(R.id.forbsFlowerShape);
            TextView forbsPetalNumber = (TextView) findViewById(R.id.forbsPetalNumber);
            TextView forbsGrowthForm = (TextView) findViewById(R.id.forbsGrowthForm);
            TextView forbsHabitat = (TextView) findViewById(R.id.forbsHabitat);
            TextView forbsLeafArrangement = (TextView) findViewById(R.id.forbsLeafArrangement);
            TextView forbsLeafShape = (TextView) findViewById(R.id.forbsLeafShape);
            TextView forbsNotes = (TextView) findViewById(R.id.forbsNotes);
            TextView forbsPhotoCredit = (TextView) findViewById(R.id.forbsPhotoCredit);

            // Set information and picture.
            // Using Glide library for image loading.
            String pictureFile = forbPlant.getPlant_code() + "_1.jpg";
            Glide.with(this).load(Uri.parse("file:///android_asset/plantphotos/forbs/" + pictureFile)).into(plantPicture);

            forbsSpeciesName.setText("Species Name: " + forbPlant.getSpecies_name());
            forbsGrowthForm.setText("Growth Form: " + forbPlant.getGrowth_form());
            forbsPlantCode.setText("Plant Code: " + forbPlant.getPlant_code());
            forbsCommonName.setText("Common Name: " + forbPlant.getCommon_name());
            forbsSynonyms.setText("Synonyms: " + forbPlant.getSynonyms());
            forbsFamilyName.setText("Family Name: " + forbPlant.getFamily_name());
            forbsFlowerColor.setText("Flower Color: " + forbPlant.getFlower_color());
            forbsFlowerShape.setText("Flower Shape: " + forbPlant.getFlower_shape());
            forbsPetalNumber.setText("Petal Number: " + forbPlant.getPetal_number());
            forbsHabitat.setText("Habitat: " + forbPlant.getHabitat());
            forbsLeafArrangement.setText("Leaf Arrangement: " + forbPlant.getLeaf_arrangement());
            forbsLeafShape.setText("Leaf Shape: " + forbPlant.getLeaf_shape_filter());
            forbsNotes.setText("Notes: " + forbPlant.getNotes());
            forbsPhotoCredit.setText("Photo Credit: " + forbPlant.getPhoto_credit());


        }
        else if(plantType.equalsIgnoreCase("cyperceae")){
            setContentView(R.layout.activity_cyperceae_detail);
        }
        else if(plantType.equalsIgnoreCase("deciduous")){
            setContentView(R.layout.activity_deciduous_detail);
        }
        else if(plantType.equalsIgnoreCase("needle")){
            setContentView(R.layout.activity_needle_detail);
        }
        else{
            setContentView(R.layout.activity_error_detail);
        }
    }
}
