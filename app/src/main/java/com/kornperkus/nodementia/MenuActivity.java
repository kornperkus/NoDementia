package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ImageView page1Btn, page2Btn, page3Btn, page4Btn, page5Btn, page6Btn, page7Btn, page8Btn, page9Btn, page10Btn;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_menu);
        getSupportActionBar().setElevation(0);

        bindView();
        setOnClick();
        setupNav();
    }

    private void bindView() {
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
        page1Btn = findViewById(R.id.page1_btn);
        page2Btn = findViewById(R.id.page2_btn);
        page3Btn = findViewById(R.id.page3_btn);
        page4Btn = findViewById(R.id.page4_btn);
        page5Btn = findViewById(R.id.page5_btn);
        page6Btn = findViewById(R.id.page6_btn);
        page7Btn = findViewById(R.id.page7_btn);
        page8Btn = findViewById(R.id.page8_btn);
        page9Btn = findViewById(R.id.page9_btn);
        page10Btn = findViewById(R.id.page10_btn);
    }

    public void setOnClick() {
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
        page4Btn.setOnClickListener(this);
        page5Btn.setOnClickListener(this);
        page6Btn.setOnClickListener(this);
        page7Btn.setOnClickListener(this);
        page8Btn.setOnClickListener(this);
        page9Btn.setOnClickListener(this);
        page10Btn.setOnClickListener(this);
        page1Btn.setOnLongClickListener(this);
        page2Btn.setOnLongClickListener(this);
        page3Btn.setOnLongClickListener(this);
        page4Btn.setOnLongClickListener(this);
        page5Btn.setOnLongClickListener(this);
        page6Btn.setOnLongClickListener(this);
        page7Btn.setOnLongClickListener(this);
        page8Btn.setOnLongClickListener(this);
        page9Btn.setOnLongClickListener(this);
        page10Btn.setOnLongClickListener(this);
    }

    public void setupNav() {
        menuImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!isOpen) {
                    drawer.openDrawer(GravityCompat.START);
                    isOpen = true;
                }else {
                    drawer.closeDrawer(GravityCompat.START);
                    isOpen = false;
                }
            }
        });
        alarmImg.setOnClickListener(new View.OnClickListener(){
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
    public void onClick(View v) {
        int id = v.getId();
        Class clss = null;
        switch (id) {
            case R.id.page1_btn:
                clss = Page1Activity.class;
                break;
            case R.id.page2_btn:
                clss = Page2Activity.class;
                break;
            case R.id.page3_btn:
                clss = Page3Activity.class;
                break;
            case R.id.page4_btn:
                clss = Page4Activity.class;
                break;
            case R.id.page5_btn:
                clss = Page5Activity.class;
                break;
            case R.id.page6_btn:
                clss = Page6Activity.class;
                break;
            case R.id.page7_btn:
                clss = Page7Activity.class;
                break;
            case R.id.page8_btn:
                clss = Page8Activity.class;
                break;
            case R.id.page9_btn:
                Toast.makeText(getApplicationContext(), "Btn 9 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.page10_btn:
                Toast.makeText(getApplicationContext(), "Btn 10 ", Toast.LENGTH_SHORT).show();
                break;
        }
        if(clss != null) startActivity(new Intent(getApplicationContext(), clss));
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.page1_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page1_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page2_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page2_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page3_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page3_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page4_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page4_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page5_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page5_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page6_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page6_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page7_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page8_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page8_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page9_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page9_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page10_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page10_title), Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_accout:
                Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        isOpen = false;
        return true;
    }
}
