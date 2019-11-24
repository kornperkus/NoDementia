package com.kornperkus.nodementia.page8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.AccountActivity;
import com.kornperkus.nodementia.LoginActivity;
import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page6ResultActivity;
import com.kornperkus.nodementia.R;
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

public class Page5Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, headline, headline2, headline3, body, body2, body3;
    private ImageView backImg, forwardImg, pagePic;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_5);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_5);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_5_title));
        headline.setText(getString(R.string.page8_5_headline));
        headline2.setText(getString(R.string.page8_5_headline2));
        headline3.setText(getString(R.string.page8_5_headline3));
        body.setText(getString(R.string.page8_5_body));
        body2.setText(getString(R.string.page8_5_body2));
        body3.setText(getString(R.string.page8_5_body3));
        pagePic.setVisibility(View.VISIBLE);
        pagePic.setImageResource(R.drawable.page8_5_pic);

        forwardImg.setVisibility(View.GONE);
        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_5PrimaryDark));
        setupNav();
        player = MediaPlayer.create(getApplicationContext(), R.raw.page8_5);
        player.start();
}

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        headline2 = findViewById(R.id.page_headline2);
        headline3 = findViewById(R.id.page_headline3);
        body = findViewById(R.id.page_body);
        body2 = findViewById(R.id.page_body2);
        body3 = findViewById(R.id.page_body3);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        pagePic = findViewById(R.id.page_pic);
    }

    public void onCallClick(View v) {
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(player != null) player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null) player.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) {
            if(player.isPlaying()) player.stop();
            player.release();
        }
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
