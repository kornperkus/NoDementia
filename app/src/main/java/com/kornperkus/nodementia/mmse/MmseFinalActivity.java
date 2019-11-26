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
import com.kornperkus.nodementia.MenuActivity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.Page6ResultActivity;
import com.kornperkus.nodementia.R;

public class MmseFinalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg, backImg, resultImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView pageTitle, scoreTv, body;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_final);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
        score = pref.getInt(MainActivity.PREF_KEY_MMSE_VALUE, 0);
        int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

        //get max score
        int maxScore = 30;
        if (educationLevel == 1) maxScore = 23;

        pageTitle.setText(getString(R.string.page5_title));
        if(score <=0) {
            scoreTv.setText("ไม่มีข้อมูล");
            body.setVisibility(View.INVISIBLE);
            resultImg.setVisibility(View.INVISIBLE);
        } else scoreTv.setText("คะแนนที่ได้รับ " + score + "/" + maxScore);

        String resultText = "ไม่มีความเสี่ยงของภาวะสมองเสื่อมจากเครื่องมือนี้";
        resultImg.setImageResource(R.drawable.mmse_1);
        if (educationLevel == 1) {
            if (score <= 14) {
                resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ให้คําแนะนํา และรักษา";
                resultImg.setImageResource(R.drawable.mmse_2);
            }
        } else if (educationLevel == 2) {
            if (score <= 17) {
                resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ให้คําแนะนํา และรักษา";
                resultImg.setImageResource(R.drawable.mmse_2);
            }
        } else if (educationLevel == 2) {
            if (score <= 22) {
                resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ให้คําแนะนํา และรักษา";
                resultImg.setImageResource(R.drawable.mmse_2);
            }
        }
        body.setText(resultText);

        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        setupNav();
        Log.i("SCORE", "Score = " + score);
    }

    private void bindView() {
        pageTitle = findViewById(R.id.page_title);
        scoreTv = findViewById(R.id.page_score);
        body = findViewById(R.id.page_body);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        backImg = findViewById(R.id.backImg);
        resultImg = findViewById(R.id.result_img);
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
