package com.kornperkus.nodementia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.AlarmClock;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_KEY_MAIN = "mainPrefKey";
    public static final String PREF_KEY_LOGIN_STATUS = "loginStatusKey";
    public static final String PREF_KEY_EDIT_ACCOUNT = "editAccountKey";
    public static final String PREF_KEY_BMI_VALUE = "bmiValueKey";
    public static final String PREF_KEY_MMSE_VALUE = "mmseValueKey";

    public static final String PREF_KEY_NAME = "nameKey";
    public static final String PREF_KEY_GENDER = "genderKey";
    public static final String PREF_KEY_AGE = "ageKey";
    public static final String PREF_KEY_RELIGION = "religionKey";
    public static final String PREF_KEY_JOB= "jobKey";
    public static final String PREF_KEY_EDUCATION = "educationKey";
    public static final String PREF_KEY_DISEASE = "diseaseKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: เพิ่มเนื้อหาหน้า 9 10
        //TODO: แก้ไขลูกศรไปกลับให้ถูกต้อง
        bindView();

        final SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_KEY_MAIN, 0);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pref.getBoolean(PREF_KEY_LOGIN_STATUS, false)) startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                else startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 1500);
    }

    private void bindView(){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
