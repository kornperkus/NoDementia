package com.kornperkus.nodementia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PREF_KEY_MAIN = "mainPrefKey";
    public static final String PREF_KEY_LOGIN_STATUS = "loginStatusKey";
    public static final String PREF_KEY_EDUCATION = "educationLevelKey";
    private Button page1Btn, page2Btn, page3Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page1);
        getSupportActionBar().setElevation(0);

        bindView();

        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_KEY_MAIN, 0);
        if(pref.getBoolean(PREF_KEY_LOGIN_STATUS, false)) startActivity(new Intent(getApplicationContext(), MenuActivity.class));
        else startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        finish();
    }

    private void bindView(){
        page1Btn = findViewById(R.id.btn_page1);
        page2Btn = findViewById(R.id.btn_page2);
        page3Btn = findViewById(R.id.btn_page3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
