package com.kornperkus.nodementia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

public class AccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg, backImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView nameTv, ageTv, genderTv, religionTv, jobTv, educationTv, diseaseTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_menu);
        getSupportActionBar().setElevation(0);
        bindView();
        //แสดงผลข้อมูลทั้งหมด
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
        nameTv.setText(pref.getString(MainActivity.PREF_KEY_NAME, "ไม่ระบุ"));
        ageTv.setText(pref.getString(MainActivity.PREF_KEY_AGE, "ไม่ระบุ"));

        int gender = pref.getInt(MainActivity.PREF_KEY_GENDER, 0);
        if(gender == R.id.sex_male) genderTv.setText(getString(R.string.gender_male));
        else genderTv.setText(getString(R.string.gender_female));

        int job = pref.getInt(MainActivity.PREF_KEY_JOB, 0);
        if(job == 1) jobTv.setText(getString(R.string.job_1));
        else if(job == 2) jobTv.setText(getString(R.string.job_2));
        else if(job == 3) jobTv.setText(getString(R.string.job_3));
        else if(job == 4) jobTv.setText(getString(R.string.job_4));
        else if(job == 5) jobTv.setText(getString(R.string.job_5));
        else if(job == 6) jobTv.setText(getString(R.string.job_6));
        else if(job == 7) jobTv.setText(getString(R.string.job_7));

        int education = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);
        if(education == 1) educationTv.setText(getString(R.string.education_1));
        else if(education == 2) educationTv.setText(getString(R.string.education_2));
        else if(education == 3) educationTv.setText(getString(R.string.education_3));

       /* int disease = pref.getInt(MainActivity.PREF_KEY_DISEASE, 0);
        if(disease == 1) diseaseTv.setText(getString(R.string.disease_1));
        else if(disease == 2) diseaseTv.setText(getString(R.string.disease_2));
        else if(disease == 3) diseaseTv.setText(getString(R.string.disease_3));
        else if(disease == 4) diseaseTv.setText(getString(R.string.disease_4));
        else if(disease == 5) diseaseTv.setText(getString(R.string.disease_5));
        else if(disease == 6) diseaseTv.setText(getString(R.string.disease_6));*/

        boolean disease1Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE1, false);
        boolean disease2Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE2, false);
        boolean disease3Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE3, false);
        boolean disease4Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE4, false);
        boolean disease5Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE5, false);

        String diseaseText = "";
        if(disease1Value || disease2Value || disease3Value || disease4Value || disease5Value) {
            if(disease1Value) diseaseText += getString(R.string.disease_1)+"  ";
            if(disease2Value) diseaseText += getString(R.string.disease_2)+"  ";
            if(disease3Value) diseaseText += getString(R.string.disease_3)+"  ";
            if(disease4Value) diseaseText += getString(R.string.disease_4)+"  ";
            if(disease5Value) diseaseText += getString(R.string.disease_5)+"  ";
        }else {
            diseaseText = getString(R.string.disease_0);
        }
        diseaseTv.setText(diseaseText);

        int religion = pref.getInt(MainActivity.PREF_KEY_RELIGION, 0);
        if(religion == R.id.religion_thai) religionTv.setText(getString(R.string.religion_thai));
        else religionTv.setText(getString(R.string.religion_islam));

        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setupNav();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void bindView(){
        backImg = findViewById(R.id.backImg);
        nameTv = findViewById(R.id.name_tv);
        ageTv = findViewById(R.id.age_tv);
        genderTv = findViewById(R.id.gender_tv);
        jobTv = findViewById(R.id.job_tv);
        educationTv = findViewById(R.id.education_tv);
        diseaseTv = findViewById(R.id.dissease_tv);
        religionTv = findViewById(R.id.religion_tv);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
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
