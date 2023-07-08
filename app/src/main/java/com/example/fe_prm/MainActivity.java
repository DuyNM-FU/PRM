package com.example.fe_prm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class MainActivity extends AppCompatActivity {

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
    }
}