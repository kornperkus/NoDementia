package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.page10.Page1Activity;

public class Page10Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    public static final String RATE_SCORE_KEY = "rateScoreKey";
    private Button beginBtn;
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page10);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page10);
        getSupportActionBar().setElevation(0);
        bindView();

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Page1Activity.class));
            }
        });

        setupNav();
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void bindView() {
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        beginBtn = findViewById(R.id.beginBtn);
        backImg = findViewById(R.id.backImg);
    }

    public void setupNav() {
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    drawer.openDrawer(GravityCompat.START);
                    isOpen = true;
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                    isOpen = false;
                }
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        MainActivity.onNavbarSelect(this, menuItem.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        isOpen = false;
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
