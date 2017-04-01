package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by chase on 3/29/2017.
 * Works with Firebase UI to create the list view of plants.
 * Need to implement plant thumbnails when we have them.
 */

public class plantHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public plantHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPlant(speciesName species){
        ImageView plantThumbnail = (ImageView) mView.findViewById(R.id.plantThumbnail);
        TextView speciesnameText = (TextView) mView.findViewById(R.id.species_nameText);
        TextView commonnameText = (TextView) mView.findViewById(R.id.common_nameText);

        speciesnameText.setText(species.getSpecies_name());
        commonnameText.setText(species.getCommon_name());
        plantThumbnail.setImageResource(R.drawable.lumi_app_logo);
    }

    @Override
    public void onClick(View view){
        final ArrayList<speciesName> species = new ArrayList<>();
        DatabaseReference forbsRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://speciesid-ca814.firebaseio.com/speciesid/field_guide/forbs");
        forbsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    species.add(snapshot.getValue(speciesName.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, plantDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                //intent.putExtra("plants", Parcels.wrap(species));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
