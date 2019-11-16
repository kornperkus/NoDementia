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

public class Mmse4_2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TextView pageTitle, bigTitleTv, text1Tv, text2Tv;
    private CheckBox option1, option2, option3, option4, option5;
    private ImageView forwardImg;
    private int score;
    private boolean exitConfirm;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private MediaPlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_4);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText(getString(R.string.page5_title));
        bigTitleTv.setText(getString(R.string.mmse_4_title));
        text1Tv.setText(getString(R.string.mmse_4_text2_1));
        text2Tv.setText(getString(R.string.mmse_4_text2_2));
        option1.setText("สะกดคำว่า วอแหวน ได้");
        option2.setText("สะกดคำว่า สระอา ได้");
        option3.setText("สะกดคำว่า นอหนู ได้");
        option4.setText("สะกดคำว่า สระอะ ได้");
        option5.setText("สะกดคำว่า มอม้า ได้");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1.isChecked()) score+=1;
                if(option2.isChecked()) score+=1;
                if(option3.isChecked()) score+=1;
                if(option4.isChecked()) score+=1;
                if(option5.isChecked()) score+=1;

                Intent intent = new Intent(getApplicationContext(), Mmse5Activity.class);
                intent.putExtra(Page5Activity.MMSE_SCORE_KEY, score);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Log.i("SCORE", "Score = "+score);
            }
        });
        setupNav();
        player = MediaPlayer.create(getApplicationContext(), R.raw.mmse_4_2);
        player.start();
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        bigTitleTv = findViewById(R.id.bigTitleTv);
        text1Tv = findViewById(R.id.textTv1);
        text2Tv = findViewById(R.id.textTv2);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        forwardImg = findViewById(R.id.forwardBtn);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
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
                MainActivity.showLogoutConfirm(this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
