package com.kornperkus.nodementia.page8;

import android.content.Intent;
import android.media.MediaPlayer;
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

public class Page9_1_1Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, headline, body;
    private ImageView backImg, forwardImg, pagePic;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_9);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_9_title));
        headline.setText(getString(R.string.page8_9_1_headline));
        body.setText(getText(R.string.page8_9_1_body));
        pagePic.setVisibility(View.VISIBLE);
        pagePic.setImageResource(R.drawable.page8_9_pic);

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Page9_1_2Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
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
        player = MediaPlayer.create(getApplicationContext(), R.raw.page8_9_1);
        //TODO: ขาดเสียงพุทธ
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

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        body = findViewById(R.id.page_body);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        pagePic = findViewById(R.id.page_pic);
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
