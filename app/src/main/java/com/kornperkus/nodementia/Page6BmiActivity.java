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

public class Page6BmiActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String PREF_KEY_BMI = "bmiKey";

    private ImageView menuImg, alarmImg, backImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private EditText editHeight, editWeight;
    private Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page6);
        getSupportActionBar().setElevation(0);

        bindView();
        btnCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String h = editHeight.getText().toString();
                String w = editWeight .getText().toString();
                if(h.isEmpty() || w.isEmpty()){
                    Toast.makeText(getApplicationContext(), "กรุณาป้อนข้อมูลให้ครบถ้วนค่ะ", Toast.LENGTH_SHORT).show();
                }else {
                    double height = Float.parseFloat(h);
                    double weight = Float.parseFloat(w);
                    double result = weight/(Math.pow(height, 2));

                    //save bmi value
                    SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).edit();
                    edit.putLong(MainActivity.PREF_KEY_BMI_VALUE, Double.doubleToRawLongBits(result*10000));
                    edit.apply();

                    startActivity(new Intent(getApplicationContext(), Page6ResultActivity.class));
                    finish();
                }
            }
        });

        setupNav();

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page6PrimaryDark));

        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bindView(){
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        btnCal = findViewById(R.id.bnt_cal);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
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
