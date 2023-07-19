package com.example.fe_prm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView tv_wcback, tv_noreser;
    ImageView img_home, img_add, userAvatar;

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

        ConfigToolbar();
    }

    private void ConfigToolbar() {
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedItem = item.getItemId();
        if (selectedItem == R.id.menu_location) {
            // Go to location activity
            Intent intent = new Intent(HomePage.this, LocationsActivity.class);
            startActivity(intent);
        }
        if (selectedItem == R.id.menu_reservations) {
            // Go to reservations activity
        }
        if (selectedItem == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}