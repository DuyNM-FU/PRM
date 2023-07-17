package com.example.fe_prm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    TextView tv_tittle, tv_booknow;
    SignInButton google_button;
    private static final int RC_SIGN_IN = 123;
    private GoogleSignInClient googleSignInClient; //google_button
    public static String AUTORIZATION_KEY = "Bearer " +
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY3NmRhOWQzMTJjMzlhNDI5OTMyZjU0M2U2YzFiNmU2NTEyZTQ5ODMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODk2MDk5NTAsImF1ZCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMjI3MTUxOTU4NzA3MDU0NzgwMCIsImVtYWlsIjoibWluaHF1YW5nMjYxMTZAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJRdWFuZyBEYW5nIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FBY0hUdGZYYmZ0SUZESEE5X3JOWU5XUG9Jc2xZUDJUZlFmZ0hld250TjZxU1hVQzRaVT1zOTYtYyIsImdpdmVuX25hbWUiOiJRdWFuZyIsImZhbWlseV9uYW1lIjoiRGFuZyIsImlhdCI6MTY4OTYxMDI1MCwiZXhwIjoxNjg5NjEzODUwLCJqdGkiOiI4ODIwMTJkNTRkZDI2ZDc2NDE1NzBjNDUyODY5N2VmZDViYjNiNDMwIn0.abtyl8UUqUQ-DgHSZXyTIh_xkGUf4XyliCHoHtXnbORGEXoSkUgvlZpxG7LLBNLXvNCcFHJG0oeJ_Gs0mJrYfwVQv4f9ZNCoR9WRIrrsf4ZnrRu_l5poSEM00GR7e5_4JzAzZWezHOwGFQ5ApfIql_soKa_M1qbGJQe28-9Cq36_oTbfOuyVOMiCAjLm8wiKbuFYow7Vb1FtKKPDmFlILucDk9o5S1nVnwHm1AcgN-3-4-DgRkJvRK7ZWZpUaRaSoykq75Fv8geJ34bEYUWWjwlTv-GGP1Zcc4VWflOOnrQK5kE_yapsfcSnEz_kevhdsRoFbKn8HTWiXr8CDyCCxg"
            ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tittle = (TextView) findViewById(R.id.tv_tittle);
        tv_booknow = (TextView) findViewById(R.id.tv_booknow);
        tv_booknow.setText("Tired of having to wait?\nMake a reservation right away.");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.google_button);
        signInButton.setOnClickListener(view -> signIn());
        Log.i("TEST","Init");
        Log.i("TEST", getApplicationContext().getPackageName());



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("TEST","Init intent requestCode: " + requestCode);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, you can use the account details
            String idToken = account.getIdToken();
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        } catch (ApiException e) {
            // Sign-in failed, handle error
            Log.e("LOGIN_ERROR", "signInResult:failed code=" + e.getStatusCode());
        }
    }
    private void signIn() {
        //Remember to remove below comment to login with Google
        Intent intent = new Intent(MainActivity.this, HomePage.class);
        startActivity(intent);
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}