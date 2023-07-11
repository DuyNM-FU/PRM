package com.example.fe_prm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView tv_wcback, tv_noreser;
    ImageView img_home, img_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tv_noreser = (TextView) findViewById(R.id.tv_noreser);
        img_add = (ImageView) findViewById(R.id.img_add);

        img_add.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                intent = new Intent(HomePage.this, TableReservationActivity.class);
                startActivity(intent);
            }
        });
    }
}