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

public class Page9_1_2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title;
    private ImageView backImg, prayThaiBtn1, prayThaiBtn2, prayThaiBtn3, prayThaiBtn4, prayThaiBtn5;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_9_1);

        //TODO: ใส่เสียงบทสวด
        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_9);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_9_title));

        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_9PrimaryDark));
        setupNav();

        prayThaiBtn1.setOnClickListener(this);
        prayThaiBtn2.setOnClickListener(this);
        prayThaiBtn3.setOnClickListener(this);
        prayThaiBtn4.setOnClickListener(this);
        prayThaiBtn5.setOnClickListener(this);
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        backImg = findViewById(R.id.backImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        prayThaiBtn1 = findViewById(R.id.pray_thai_1_btn);
        prayThaiBtn2 = findViewById(R.id.pray_thai_2_btn);
        prayThaiBtn3 = findViewById(R.id.pray_thai_3_btn);
        prayThaiBtn4 = findViewById(R.id.pray_thai_4_btn);
        prayThaiBtn5 = findViewById(R.id.pray_thai_5_btn);
    }

    @Override
    public void onClick(View v) {
        if(player != null) player.release();
        switch (v.getId()) {
            case R.id.pray_thai_1_btn:
                player = MediaPlayer.create(getApplicationContext(), R.raw.pray_thai_1);
                break;
            case R.id.pray_thai_2_btn:
                player = MediaPlayer.create(getApplicationContext(), R.raw.pray_thai_2);
                break;
            case R.id.pray_thai_3_btn:
                player = MediaPlayer.create(getApplicationContext(), R.raw.pray_thai_3);
                break;
            case R.id.pray_thai_4_btn:
                player = MediaPlayer.create(getApplicationContext(), R.raw.pray_thai_4);
                break;
            case R.id.pray_thai_5_btn:
                player = MediaPlayer.create(getApplicationContext(), R.raw.pray_thai_5);
                break;
        }
        player.start();
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
