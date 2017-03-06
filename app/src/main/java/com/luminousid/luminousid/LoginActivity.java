package com.luminousid.luminousid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luminousid.luminousid.R;

import org.w3c.dom.Text;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that for Firebase server
 */
public class LoginActivity extends AppCompatActivity
        implements View.OnClickListener{

    // Objects for Firebase login authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static boolean loginSuccess = false;

    private static final String TAG = "LoginActivity";

    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Text fields on screen
        mEmailField = (EditText) findViewById(R.id.email);
        mPasswordField = (EditText) findViewById(R.id.password);

        // Buttons on screen
        findViewById(R.id.email_sign_in_button).setOnClickListener(this);

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

                // Go to home screen after
                //updateUI(user);

            }
        };
    }

    // Firebase tutorial sign in code
    private void signIn(String email, String password){
        Log.d(TAG, "signIn:" + email);

        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                // If Sign-in fails, display a message to the user.
                // If sign in succeeds, the auth state listener will be notified
                // and logic to handle the signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    setLoginStatus(false);
                    System.out.println("Login fail at signIn step 1: " + getLoginStatus());
                    mEmailField.setError("Email or password incorrect!");
                    mPasswordField.setError("Email or password incorrect!");
                }
                else {
                    setLoginStatus(true);
                    System.out.println("Login success at signIn step 2: " + getLoginStatus());
                    System.out.println("Logged in as: " + mAuth.getCurrentUser().getEmail());
                    gotoOpen();
                }

                if (!task.isSuccessful()) {
                    //mStatusTextView.setText("Authentication failed.");
                    setLoginStatus(false);
                    System.out.println("Login fail at signIn step 3: " + getLoginStatus());
                }
            }
        });
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

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        }
        else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_sign_in_button) {

            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());

        }
    }

    private boolean getLoginStatus(){
        return loginSuccess;
    }

    private void setLoginStatus(boolean status){
        loginSuccess = status;
    }

    private void gotoOpen() {
        Intent intent = new Intent(LoginActivity.this, Home_screen.class);
        startActivity(intent);
    }

}

