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

public class Page4_4Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, headline, body;
    private ImageView backImg, forwardImg;
    private MediaPlayer player;
    private ImageView pos1Img, pos2Img, pos3Img, pos4Img, pos5Img, pos6Img, pos7Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_4_4);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_4);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_4_title));
        headline.setText(getString(R.string.page8_4_4_headline));
        body.setText(getText(R.string.page8_4_4_body));
        int relegion_id = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).getInt(MainActivity.PREF_KEY_RELIGION, R.id.religion_thai);
        if(relegion_id == R.id.religion_islam) {
            body.append(" คลิ๊กที่รูปเพื่อเล่นเสียง");
        }

        forwardImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Page4_5Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_4PrimaryDark));
        setupNav();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) player.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            if (player.isPlaying()) player.stop();
            player.release();
        }
    }

    private void bindView() {
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        body = findViewById(R.id.page_body);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        pos1Img = findViewById(R.id.pos_1_img);
        pos2Img = findViewById(R.id.pos_2_img);
        pos3Img = findViewById(R.id.pos_3_img);
        pos4Img = findViewById(R.id.pos_4_img);
        pos5Img = findViewById(R.id.pos_5_img);
        pos6Img = findViewById(R.id.pos_6_img);
        pos7Img = findViewById(R.id.pos_7_img);

        int relegion_id = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).getInt(MainActivity.PREF_KEY_RELIGION, R.id.religion_thai);
        if(relegion_id == R.id.religion_islam) {
            pos1Img.setOnClickListener(this);
            pos2Img.setOnClickListener(this);
            pos3Img.setOnClickListener(this);
            pos4Img.setOnClickListener(this);
            pos5Img.setOnClickListener(this);
            pos6Img.setOnClickListener(this);
            pos7Img.setOnClickListener(this);
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

    public void playSound(int id) {
        if(player != null) {
            player.stop();
        }
        player = MediaPlayer.create(getApplicationContext(), id);
        player.start();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pos_1_img:
                playSound(R.raw.meditation_1);
                break;
            case R.id.pos_2_img:
                playSound(R.raw.meditation_2);
                break;
            case R.id.pos_3_img:
                playSound(R.raw.meditation_3);
                break;
            case R.id.pos_4_img:
                playSound(R.raw.meditation_4);
                break;
            case R.id.pos_5_img:
                playSound(R.raw.meditation_5);
                break;
            case R.id.pos_6_img:
                playSound(R.raw.meditation_6);
                break;
            case R.id.pos_7_img:
                playSound(R.raw.meditation_7);
                break;
        }
    }
}
