package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by chase on 3/29/2017.
 * Works with Firebase UI to create the list view of plants.
 * Need to implement plant thumbnails when we have them.
 */

public class forbsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public forbsHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPlant(forbsDetails species){
        ImageView plantThumbnail = (ImageView) mView.findViewById(R.id.plantThumbnail);
        TextView speciesnameText = (TextView) mView.findViewById(R.id.species_nameText);
        TextView commonnameText = (TextView) mView.findViewById(R.id.common_nameText);

        speciesnameText.setText(species.getSpecies_name());
        commonnameText.setText(species.getCommon_name());

        // Using Glide library for image loading.
        String pictureFile = species.getPlant_code() + "_1.jpg";
        Glide.with(mContext).load(Uri.parse("file:///android_asset/plantphotos/forbs/" + pictureFile)).into(plantThumbnail);

    }

    @Override
    public void onClick(View view){
        final ArrayList<forbsDetails> species = new ArrayList<>();
        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        forbsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    species.add(snapshot.getValue(forbsDetails.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, plantDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("plantType", "forbs");

                // Get plant clicked on and send to plantDetailActivity.
                forbsDetails forbsPlant = species.get(itemPosition);

                // Use Parcels class to wrap a class and send it to the detail activity.
                intent.putExtra("plantInfo", Parcels.wrap(forbsPlant));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
