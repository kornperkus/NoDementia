package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Page9Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView backImg, forwardImg;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private ImageView displayimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page1);
        getSupportActionBar().setElevation(0);

        bindView();

        Intent intent = getIntent();
        final int pageNumber = intent.getIntExtra("page", 1);
        int id = getResources().getIdentifier("page9_" + pageNumber, "drawable", getPackageName());
        displayimg.setImageResource(id);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (pageNumber >= 6) {
            forwardImg.setVisibility(View.GONE);
        }else {
            forwardImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Page9Activity.class);
                    intent.putExtra("page", pageNumber + 1);
                    startActivity(intent);
                    finish();
                }
            });
        }

        setupNav();
        alarmImg.setVisibility(View.GONE);
    }

    private void bindView() {
        forwardImg = findViewById(R.id.forwardImg);
        backImg = findViewById(R.id.backImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
        displayimg = findViewById(R.id.display_img);
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
        alarmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
