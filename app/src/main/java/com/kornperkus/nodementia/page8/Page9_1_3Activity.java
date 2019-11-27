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

public class Page9_1_3Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, link1, link2, link3, link4, link5, link6, link7, link8, link9, link10, link11, link12, link13, link14, link15;
    private ImageView backImg, forwardImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_9_3_thai);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_8);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_9_title));

        forwardImg.setVisibility(View.GONE);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_9PrimaryDark));

        setupNav();

        link1.setOnClickListener(this);
        link2.setOnClickListener(this);
        link3.setOnClickListener(this);
        link4.setOnClickListener(this);
        link5.setOnClickListener(this);
        link6.setOnClickListener(this);
        link7.setOnClickListener(this);
        link8.setOnClickListener(this);
        link9.setOnClickListener(this);
        link10.setOnClickListener(this);
        link11.setOnClickListener(this);
        link12.setOnClickListener(this);
        link13.setOnClickListener(this);
        link14.setOnClickListener(this);
        link15.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_1:
                openYoutube("cnpi56lScU4");
                break;
            case R.id.link_2:
                openYoutube("wOJuq5oWKqs");
                break;
            case R.id.link_3:
                openYoutube("ljvmx2t5Qfs");
                break;
            case R.id.link_4:
                openYoutube("ZFCgkpLeGts");
                break;
            case R.id.link_5:
                openYoutube("Pg42HYNdKwE");
                break;
            case R.id.link_6:
                openYoutube("QjtqpsYDPbc");
                break;
            case R.id.link_7:
                openYoutube("yz6TSShCCBI");
                break;
            case R.id.link_8:
                openYoutube("pUPtLPT7vu8");
                break;
            case R.id.link_9:
                openYoutube("Yq_BYeOoQ20");
                break;
            case R.id.link_10:
                openYoutube("i8M4FBPdc8o");
                break;
            case R.id.link_11:
                openYoutube("6iSLWbJXQFs");
                break;
            case R.id.link_12:
                openYoutube("oJKkCVfEdzA");
                break;
            case R.id.link_13:
                openYoutube("hXwvi0ecwTQ");
                break;
            case R.id.link_14:
                openYoutube("xS7DQb3pt6E");
                break;
            case R.id.link_15:
                openYoutube("nD3TApXn71I");
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
        link9 = findViewById(R.id.link_9);
        link10 = findViewById(R.id.link_10);
        link11 = findViewById(R.id.link_11);
        link12 = findViewById(R.id.link_12);
        link13 = findViewById(R.id.link_13);
        link14 = findViewById(R.id.link_14);
        link15 = findViewById(R.id.link_15);
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