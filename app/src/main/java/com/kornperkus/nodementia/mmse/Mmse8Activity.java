package com.kornperkus.nodementia.mmse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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

public class Mmse8Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView pageTitle, bigTitleTv, titleTv;
    private CheckBox option1, option2, option3;
    private ImageView forwardImg;
    private int score;
    private boolean exitConfirm;
    private MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_8);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText(getString(R.string.page5_title));
        bigTitleTv.setText(getString(R.string.mmse_8_title));
        titleTv.setText("\t\"ฟังดีๆ นะเดี๋ยวผม(ดิฉัน) จะส่งกระดาษให้ แล้วให้คุณ(ตา,ยาย,...)รับด้วยมือขวา พับครึ่งด้วยมือทั้งสองข้าง แล้ววางไว้ที่...........\" (พื้น, โต๊ะ, เตียง) \n\n\"ผู้ทดสอบแสดงกระดาษเปล่าขนาดประมาณ เอ-4ไม่มีรอยพับ ให้ผู้ถูกทดสอบ\"");

        option1.setText("รับด้วยมือขวา");
        option2.setText("พับครึ่ง");
        option3.setText("วางไว้ที่........... (พื้น, โต๊ะ, เตียง)");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1.isChecked()) score+=1;
                if(option2.isChecked()) score+=1;
                if(option3.isChecked()) score+=1;

                SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
                int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

                Intent intent = null;

                //Check education level
                if(educationLevel == 1) intent = new Intent(getApplicationContext(), Mmse11Activity.class);
                else intent = new Intent(getApplicationContext(), Mmse9Activity.class);

                intent.putExtra(Page5Activity.MMSE_SCORE_KEY, score);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        player = MediaPlayer.create(getApplicationContext(), R.raw.mmse_8_des);
        player.start();

        setupNav();
        alarmImg.setVisibility(View.GONE);
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        bigTitleTv = findViewById(R.id.bigTitleTv);
        titleTv = findViewById(R.id.titleTv);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        forwardImg = findViewById(R.id.forwardBtn);
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
    @Override
    protected void onStop() {
        super.onStop();
        if(player != null) player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null) player.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) {
            if(player.isPlaying()) player.stop();
            player.release();
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
