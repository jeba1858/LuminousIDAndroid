package com.luminousid.luminousid;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class open_screen extends AppCompatActivity {

    // Objects for Firebase login authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth user = null;

    private static final String TAG = "open_screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__screen);

        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();

        // Firebase authentication code pulled from Firebase tutorial
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        if (user != null){
            System.out.println("Already logged in, skipped open screen");
            Intent intent = new Intent(open_screen.this, Home_screen.class);
            startActivity(intent);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        if (user != null){
            System.out.println("Already logged in, skipped open screen.");
            Intent intent = new Intent(open_screen.this, Home_screen.class);
            startActivity(intent);
        }
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

    public void gotoDBTest(View view) {
        Intent intent = new Intent(open_screen.this, DBTest.class);
        startActivity(intent);
    }

    public void gotoLogin(View view) {
        Intent intent = new Intent(open_screen.this, LoginActivity.class);
        startActivity(intent);
    }

    public void gotoSignup(View view) {
        Intent intent = new Intent(open_screen.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void gotoHomeScreen(View view) {
        Intent intent = new Intent(open_screen.this, Home_screen.class);
        startActivity(intent);
    }

    public void toAboutScreenActivity(View view) {
        Intent intent = new Intent(open_screen.this, com.luminousid.luminousid.About.class);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
