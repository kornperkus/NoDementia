package com.kornperkus.nodementia.mmse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.AccountActivity;
import com.kornperkus.nodementia.LoginActivity;
import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.Page6ResultActivity;
import com.kornperkus.nodementia.R;

public class Mmse3Activity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private TextView pageTitle;
    private ImageView forwardImg;
    private CheckBox option1_1, option1_2, option1_3, option2_1, option2_2, option2_3;
    private int score;
    private boolean exitConfirm;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_3);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText(getString(R.string.page5_title));

        option1_1.setOnClickListener(this);
        option1_2.setOnClickListener(this);
        option1_3.setOnClickListener(this);
        option2_1.setOnClickListener(this);
        option2_2.setOnClickListener(this);
        option2_3.setOnClickListener(this);

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1_1.isChecked()) score+=1;
                if(option1_2.isChecked()) score+=1;
                if(option1_3.isChecked()) score+=1;
                if(option2_1.isChecked()) score+=1;
                if(option2_2.isChecked()) score+=1;
                if(option2_3.isChecked()) score+=1;

                SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
                int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

                Intent intent = null;

                //Check education level
                if(educationLevel == 1) intent = new Intent(getApplicationContext(), Mmse5Activity.class);
                else intent = new Intent(getApplicationContext(), Mmse4Activity.class);

                intent.putExtra(Page5Activity.MMSE_SCORE_KEY, score);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Log.i("SCORE", "Score = "+score);
            }
        });
        setupNav();
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        forwardImg = findViewById(R.id.forwardBtn);
        option1_1 = findViewById(R.id.option1_1);
        option1_2 = findViewById(R.id.option1_2);
        option1_3 = findViewById(R.id.option1_3);
        option2_1 = findViewById(R.id.option2_1);
        option2_2 = findViewById(R.id.option2_2);
        option2_3 = findViewById(R.id.option2_3);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
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
        new AlertDialog.Builder(Mmse3Activity.this)
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
        if(exitConfirm){
            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), Page5Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.exit_confirm), Toast.LENGTH_SHORT).show();
            exitConfirm = true;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.option1_1 || id == R.id.option1_2 || id == R.id.option1_3) {
            option2_1.setChecked(false);
            option2_2.setChecked(false);
            option2_3.setChecked(false);
        }
        if(id == R.id.option2_1 || id == R.id.option2_2 || id == R.id.option2_3){
            option1_1.setChecked(false);
            option1_2.setChecked(false);
            option1_3.setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
