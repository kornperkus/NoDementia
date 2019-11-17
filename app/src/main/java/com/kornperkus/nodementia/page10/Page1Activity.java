package com.kornperkus.nodementia.page10;

import android.app.AlertDialog;
import android.content.ContentValues;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.kornperkus.nodementia.Page10Activity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.Page6ResultActivity;
import com.kornperkus.nodementia.R;
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

public class Page1Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView title;
    private ImageView forwardImg;
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title1, title2;
    private LinearLayout topic3, topic4, topic5;
    private RadioGroup group1, group2;
    private boolean exitConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page10);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page10_1_title));

        title1.setText(getString(R.string.page10_1_1));
        title2.setText(getString(R.string.page10_1_2));

        topic3.setVisibility(View.GONE);
        topic4.setVisibility(View.GONE);
        topic5.setVisibility(View.GONE);

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page1PrimaryDark));




        setupNav();
        forwardImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score1;
                int id1 = group1.getCheckedRadioButtonId();
                switch (id1) {
                    case R.id.option1_2:
                        score1 = 2;
                        break;
                    case R.id.option1_3:
                        score1 = 3;
                        break;
                    case R.id.option1_4:
                        score1 = 4;
                        break;
                    case R.id.option1_1:
                        score1 = 1;
                        break;
                    default:
                        score1 = 5;
                }
                int score2;
                int id2 = group2.getCheckedRadioButtonId();
                switch (id2) {
                    case R.id.option2_2:
                        score2 = 2;
                        break;
                    case R.id.option2_3:
                        score2 = 3;
                        break;
                    case R.id.option2_4:
                        score2 = 4;
                        break;
                    case R.id.option2_1:
                        score2 = 1;
                        break;
                    default:
                        score2 = 5;
                }

                Bundle bundle = new Bundle();
                bundle.putInt("1_1", score1);
                bundle.putInt("1_2", score2);
                Intent intent = new Intent(getApplicationContext(), Page2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(exitConfirm){
            super.onBackPressed();
            Intent intent = new Intent(getApplicationContext(), Page10Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.page10_exit_confirm), Toast.LENGTH_SHORT).show();
            exitConfirm = true;
        }
    }

    private void bindView() {
        title = findViewById(R.id.page_title);
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        forwardImg = findViewById(R.id.forwardImg);
        title1 = findViewById(R.id.title_1);
        title2 = findViewById(R.id.title_2);
        topic3 = findViewById(R.id.topic_3);
        topic4 = findViewById(R.id.topic_4);
        topic5 = findViewById(R.id.topic_5);
        group1 = findViewById(R.id.group_1);
        group2 = findViewById(R.id.group_2);
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
