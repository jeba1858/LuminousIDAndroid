package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.luminousid.luminousid.R.id.logoutButton;

public class Home_screen extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Buttons on screen
        Button logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(this);

        findViewById(R.id.fieldGuideButton).setOnClickListener(this);
        findViewById(R.id.myObservationsButton).setOnClickListener(this);
        findViewById(R.id.settingsButton).setOnClickListener(this);
        findViewById(R.id.glossaryButton).setOnClickListener(this);
        findViewById(R.id.aboutButton).setOnClickListener(this);

        //Textview for Login Status
        TextView loginStatus = (TextView) findViewById(R.id.loginStatus);
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            loginStatus.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }
        else{
            loginStatus.setText("Guest");
            logoutButton.setText("Log-In/Sign-up");
        }

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            System.out.println("Currently signed in: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        }

    }

    @Override
    public void onClick(View v){
        int i = v.getId();

        // Check what button user presses, go there.

        if(i == logoutButton){

            if(FirebaseAuth.getInstance().getCurrentUser() != null){
                FirebaseAuth.getInstance().signOut();
                System.out.println("Previous user signed out.");
                gotoOpen();
            }
            else{
                gotoOpen();
            }

        }

        else if(i == R.id.aboutButton){
            gotoAbout();
        }

        else if(i == R.id.fieldGuideButton){
            gotoFieldGuide();
        }


    }

    public void gotoOpen(){
        Intent intent = new Intent(Home_screen.this, open_screen.class);
        startActivity(intent);
        super.finish();
    }

    public void gotoAbout(){
        Intent intent = new Intent(Home_screen.this, About.class);
        startActivity(intent);
    }

    public void gotoFieldGuide(){
        Intent intent = new Intent(Home_screen.this, Forbs_FieldGuide.class);
        startActivity(intent);
    }

}
