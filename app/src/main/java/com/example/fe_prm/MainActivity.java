package com.example.fe_prm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fe_prm.view_your_reservation.ViewYourReservationActivity;
import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {

    public static String AUTORIZATION_KEY = "Bearer " +
            "eyJhbGciOiJSUzI1NiIsImtpZCI6IjY3NmRhOWQzMTJjMzlhNDI5OTMyZjU0M2U2YzFiNmU2NTEyZTQ5ODMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2ODkwNjU0NzQsImF1ZCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExMjI3MTUxOTU4NzA3MDU0NzgwMCIsImVtYWlsIjoibWluaHF1YW5nMjYxMTZAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF6cCI6IjI0NzAyMjkwMDI3NS0zZWx0ZGtuMXZtYTIwbDNwcjYxamZudnBhNGs4Ymdlby5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsIm5hbWUiOiJRdWFuZyBEYW5nIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FBY0hUdGQwRUNmel93d1VmZXhFMzFoYng1YzFzRGc2RFJqRlpjSF9YdE4ycEZxZ2s3WT1zOTYtYyIsImdpdmVuX25hbWUiOiJRdWFuZyIsImZhbWlseV9uYW1lIjoiRGFuZyIsImlhdCI6MTY4OTA2NTc3NCwiZXhwIjoxNjg5MDY5Mzc0LCJqdGkiOiIxODIxY2Q4YjU5MTdkYTRkNDAxNGRkOGRjNmExNGQ0YTU0M2E5YWE5In0.Xsy38f_zYEJ1ncZ5vo2mevCLDFRZVvruFMH5VbXxQl_urTl8zFb4xxqRwnew_ZfLDKlFzWug_SyvacmFLYAdXp0ORW1Xe9x5EHwKJw0j3LxYAhx0T3DakvmnFCAUNAkdBFuhXlYM7siTCe_24uXLAaBBaKDIowCLfVlXP4mH3QtO7BRFNxrXpEu8_zn__hXcTrjOfy5kyxI6h5BMAnd7DReu60xPqnGm6fVVrw1PP98d_zg2r13ZoNAPFA0MhDtrzfiFQm5s4Mv3x62v7DWC6qD-1D3DANQEvpYVK4qCi53zyvVN7pt34VoDz4iwUhcwfobpEO8SW5hrWLvAqmIiSQ"
            ;
    TextView tv_tittle, tv_booknow;
    SignInButton google_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tittle = (TextView) findViewById(R.id.tv_tittle);
        tv_booknow = (TextView) findViewById(R.id.tv_booknow);
        google_button = (SignInButton) findViewById(R.id.google_button);

        tv_booknow.setText("Tired of having to wait?\nMake a reservation right away.");

        google_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        });
    }

}