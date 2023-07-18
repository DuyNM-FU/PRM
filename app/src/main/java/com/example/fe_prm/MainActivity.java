package com.example.fe_prm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.fe_prm.ConfirmReservation.ConfirmReservation;
import com.example.fe_prm.FoodOrder.FoodOrder;
import com.example.fe_prm.Payment.Payment;
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
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY3NmRhOWQzMTJjMzlhNDI5OTMyZjU0M2U2YzFiNmU2NTEyZTQ5ODMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODkwNjU0NzQsImF1ZCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMjI3MTUxOTU4NzA3MDU0NzgwMCIsImVtYWlsIjoibWluaHF1YW5nMjYxMTZAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJRdWFuZyBEYW5nIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FBY0hUdGQwRUNmel93d1VmZXhFMzFoYng1YzFzRGc2RFJqRlpjSF9YdE4ycEZxZ2s3WT1zOTYtYyIsImdpdmVuX25hbWUiOiJRdWFuZyIsImZhbWlseV9uYW1lIjoiRGFuZyIsImlhdCI6MTY4OTA2NTc3NCwiZXhwIjoxNjg5MDY5Mzc0LCJqdGkiOiIxODIxY2Q4YjU5MTdkYTRkNDAxNGRkOGRjNmExNGQ0YTU0M2E5YWE5In0.Xsy38f_zYEJ1ncZ5vo2mevCLDFRZVvruFMH5VbXxQl_urTl8zFb4xxqRwnew_ZfLDKlFzWug_SyvacmFLYAdXp0ORW1Xe9x5EHwKJw0j3LxYAhx0T3DakvmnFCAUNAkdBFuhXlYM7siTCe_24uXLAaBBaKDIowCLfVlXP4mH3QtO7BRFNxrXpEu8_zn__hXcTrjOfy5kyxI6h5BMAnd7DReu60xPqnGm6fVVrw1PP98d_zg2r13ZoNAPFA0MhDtrzfiFQm5s4Mv3x62v7DWC6qD-1D3DANQEvpYVK4qCi53zyvVN7pt34VoDz4iwUhcwfobpEO8SW5hrWLvAqmIiSQ"
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
        Intent intent = new Intent(MainActivity.this,  FoodOrder.class);
        startActivity(intent);
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}