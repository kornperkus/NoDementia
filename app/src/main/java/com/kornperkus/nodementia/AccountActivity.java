package com.kornperkus.nodementia;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

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
        jobTv.setText(pref.getString(MainActivity.PREF_KEY_JOB, "ไม่ระบุ"));
        diseaseTv.setText(pref.getString(MainActivity.PREF_KEY_DISEASE, "ไม่ระบุ"));

        int gender = pref.getInt(MainActivity.PREF_KEY_GENDER, 0);
        if(gender == R.id.sex_male) genderTv.setText(getString(R.string.gender_male));
        else genderTv.setText(getString(R.string.gender_female));

        int education = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);
        if(education == 1) educationTv.setText(getString(R.string.education_1));
        else if(education == 2) educationTv.setText(getString(R.string.education_2));
        else if(education == 3) educationTv.setText(getString(R.string.education_3));

        int religion = pref.getInt(MainActivity.PREF_KEY_RELIGION, 0);
        if(religion == R.id.religion_thai) religionTv.setText(getString(R.string.religion_thai));
        else religionTv.setText(getString(R.string.religion_islam));

    }

    private void bindView(){
        nameTv = findViewById(R.id.name_tv);
        ageTv = findViewById(R.id.age_tv);
        genderTv = findViewById(R.id.gender_tv);
        jobTv = findViewById(R.id.job_tv);
        educationTv = findViewById(R.id.education_tv);
        diseaseTv = findViewById(R.id.dissease_tv);
        religionTv = findViewById(R.id.religion_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
