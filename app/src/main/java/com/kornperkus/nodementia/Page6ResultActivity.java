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
import android.widget.EditText;
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
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

public class Page6ResultActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg, alarmImg, backImg, bmiImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title, resultTv, resultTextTv, resultTextBodyTv;
    private double bmiValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page6);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText("ค่าดัชนีมวลกาย (BMI)");

        long bmi = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).getLong(MainActivity.PREF_KEY_BMI_VALUE, 0);
        bmiValue = Double.longBitsToDouble(bmi);
        resultTv.setText(String.format("%.2f", bmiValue));
        String resultText;
        if(bmiValue <= 0) {
            resultTextTv.setText("ไม่มีข้อมูล");
            bmiImg.setVisibility(View.INVISIBLE);
            resultTextBodyTv.setVisibility(View.INVISIBLE);
        }else if(bmiValue <= 18.5) {
            resultTextTv.setText(getText(R.string.bmi_1_title));
            resultTextBodyTv.setText(getText(R.string.bmi_1_body));
            bmiImg.setImageResource(R.drawable.bmi_1);
        }else if(bmiValue <= 24.9) {
            resultTextTv.setText(getText(R.string.bmi_2_title));
            resultTextBodyTv.setText(getText(R.string.bmi_2_body));
            bmiImg.setImageResource(R.drawable.bmi_2);
        }else if(bmiValue <= 29.9) {
            resultTextTv.setText(getText(R.string.bmi_3_title));
            resultTextBodyTv.setText(getText(R.string.bmi_3_body));
            bmiImg.setImageResource(R.drawable.bmi_3);
        }else {
            resultTextTv.setText(getText(R.string.bmi_4_title));
            resultTextBodyTv.setText(getText(R.string.bmi_4_body));
            bmiImg.setImageResource(R.drawable.bmi_4);
        }

        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page6PrimaryDark));
        setupNav();
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        resultTv = findViewById(R.id.result_tv);
        resultTextTv = findViewById(R.id.result_text_tv);
        resultTextBodyTv = findViewById(R.id.result_text_body_tv);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
        backImg = findViewById(R.id.backImg);
        bmiImg = findViewById(R.id.bmi_img);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
