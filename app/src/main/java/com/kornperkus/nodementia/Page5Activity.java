package com.kornperkus.nodementia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.mmse.Mmse1_1Activity;
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

public class Page5Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    public static final String MMSE_SCORE_KEY = "mmseScoreKey";
    private Button beginBtn;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);
        bindView();
        setupNav();

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mmse1_1Activity.class));
            }
        });

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
        alarmImg = findViewById(R.id.ic_clock);
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

    public void showConfirm() {
        new AlertDialog.Builder(Page5Activity.this)
                .setTitle("ออกจากระบบ")
                .setMessage("หากออกจากระบบข้อมูลทั้งหมดของท่านจะศูนย์หาย")
                .setCancelable(false)
                .setPositiveButton("ออกจากระบบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logout();
                    }
                }).setNegativeButton("ยกเลิก",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void logout() {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).edit();
        editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, false);
        editor.apply();
        Toast.makeText(getApplicationContext(), "ออกจากระบบแล้ว", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_accout:
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                break;
            case R.id.nav_edit:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra(MainActivity.PREF_KEY_EDIT_ACCOUNT, true);
                startActivity(intent);
                break;
            case R.id.nav_bmi:
                startActivity(new Intent(getApplicationContext(), Page6ResultActivity.class));
                break;
            case R.id.nav_mmse:
                startActivity(new Intent(getApplicationContext(), MmseFinalActivity.class));
                break;
            case R.id.nav_logout:
                showConfirm();
                break;
        }
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
