package com.kornperkus.nodementia.page8;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.R;

public class Page8_1Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, link1, link2, link3, link4, link5, link6, link7, link8;
    private ImageView backImg, forwardImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_8_thai);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_8);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_8_title));

        forwardImg.setVisibility(View.GONE);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_8PrimaryDark));

        setupNav();

        link1.setOnClickListener(this);
        link2.setOnClickListener(this);
        link3.setOnClickListener(this);
        link4.setOnClickListener(this);
        link5.setOnClickListener(this);
        link6.setOnClickListener(this);
        link7.setOnClickListener(this);
        link8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_1:
                openYoutube("drEK-q-uUVA");
                break;
            case R.id.link_2:
                openYoutube("bLNnAA_2_rs");
                break;
            case R.id.link_3:
                openYoutube("dgUt_rPD_ss");
                break;
            case R.id.link_4:
                openYoutube("Zu4pwnNcZkQ");
                break;
            case R.id.link_5:
                openYoutube("bkst3xdeWrU");
                break;
            case R.id.link_6:
                openYoutube("kgy33OslcAg");
                break;
            case R.id.link_7:
                openYoutube("Q05waGR5F_w");
                break;
            case R.id.link_8:
                openYoutube("Ns40wsrM2ow");
                break;
        }
    }

    private void openYoutube(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    private void bindView() {
        title = findViewById(R.id.page_title);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        link1 = findViewById(R.id.link_1);
        link2 = findViewById(R.id.link_2);
        link3 = findViewById(R.id.link_3);
        link4 = findViewById(R.id.link_4);
        link5 = findViewById(R.id.link_5);
        link6 = findViewById(R.id.link_6);
        link7 = findViewById(R.id.link_7);
        link8 = findViewById(R.id.link_8);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
