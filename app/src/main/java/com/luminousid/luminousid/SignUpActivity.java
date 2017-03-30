package com.luminousid.luminousid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

// Code inspired by Firebase startup tutorial for account creation & authentication

public class SignUpActivity extends AppCompatActivity
        implements View.OnClickListener {

    // Objects for Firebase login authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    // Firebase database reference
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userReference = FirebaseDatabase.getInstance().getReference();

    private static final String TAG = "sign_up_activity";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mPassword2Field;
    private EditText mUsernameField;

    @Override
    protected void attachBaseContext(Context newbase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newbase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        // Views
        //mStatusTextView = (TextView) findViewById(R.id.status);
        //mDetailTextView = (TextView) findViewById(R.id.detail);
        mEmailField = (EditText) findViewById(R.id.useremailText);
        mUsernameField = (EditText) findViewById(R.id.usernameText);
        mPasswordField = (EditText) findViewById(R.id.userpassword1);
        mPassword2Field = (EditText) findViewById(R.id.userpassword2);

        //Buttons
        //findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.createAccountButton).setOnClickListener(this);
        //findViewById(R.id.sign_out_button).setOnClickListener(this);
        //findViewById(R.id.verify_email_button).setOnClickListener(this);

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
                }
                else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

                // Go to home screen afterwards
                //updateUI(user);
            }
        };
    }

    private void writeNewAccount(String userID, String username, Boolean researcherStatus){
        userReference.child("speciesid").child("accounts").child(userID).child("researcher").setValue(researcherStatus);
        userReference.child("speciesid").child("accounts").child(userID).child("username").setValue(username);
    }

        // Method to create new Account with input email and password.
        // Validates and creates the new user.

        private void createAccount(String email, String password){
            Log.d(TAG, "createAccount:" + email);

            if (!validateForm()) {
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                  // If sign in fails, display a message to the user.
                    // If sign in succeeds, the auth state listener will be notified and logic
                    // to handle the signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.i("Reponse", "Failed to create user: " + task.getException().getMessage());
                        mEmailField.setError(task.getException().getMessage());
                    }
                    else {
                        System.out.println("Account created successfully.");
                        writeNewAccount(mAuth.getCurrentUser().getUid(), mUsernameField.getText().toString(), false);
                        toHomeScreenActivity();
                    }

                }
            });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        }
        else {
            mEmailField.setError(null);
        }

        String username = mUsernameField.getText().toString();

        if (TextUtils.isEmpty(username)) {
            mUsernameField.setError("Required.");
            valid = false;
        }
        else {
            mUsernameField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        String password2 = mPassword2Field.getText().toString();

        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        }
        else if (TextUtils.isEmpty(password2)) {
            mPassword2Field.setError("Required.");
            valid = false;
        }
        else if (!TextUtils.equals(password, password2)){
            mPassword2Field.setError("Passwords aren't the same.");
            valid = false;
        }
        else {
            mPasswordField.setError(null);
            mPassword2Field.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.createAccountButton){

            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());

        }
    }

    private void updateUI(FirebaseUser user) {
        if (user != null){
            System.out.println("Already signed in, skipping screen.");
            toHomeScreenActivity();
        }
        else {
            System.out.println("No account signed in.");
        }
    }

    public void toHomeScreenActivity() {
        Intent intent = new Intent(SignUpActivity.this, Home_screen.class);
        startActivity(intent);
        open_screen.openScreenObj.finish();
        super.finish();
    }

}
