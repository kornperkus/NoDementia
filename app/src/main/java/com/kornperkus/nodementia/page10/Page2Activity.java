package com.kornperkus.nodementia.page10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page10Activity;
import com.kornperkus.nodementia.R;

public class Page2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView title;
    private ImageView forwardImg;
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title1, title2, title3, title4, title5;
    private RadioGroup group1, group2, group3, group4, group5;
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
        title.setText(getString(R.string.page10_2_title));

        title1.setText(getString(R.string.page10_2_1));
        title2.setText(getString(R.string.page10_2_2));
        title3.setText(getString(R.string.page10_2_3));
        title4.setText(getString(R.string.page10_2_4));
        title5.setText(getString(R.string.page10_2_5));

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
                int score3;
                int id3 = group3.getCheckedRadioButtonId();
                switch (id3) {
                    case R.id.option3_2:
                        score3 = 2;
                        break;
                    case R.id.option3_3:
                        score3 = 3;
                        break;
                    case R.id.option3_4:
                        score3 = 4;
                        break;
                    case R.id.option3_1:
                        score3 = 1;
                        break;
                    default:
                        score3 = 5;
                }
                int score4;
                int id4 = group4.getCheckedRadioButtonId();
                switch (id4) {
                    case R.id.option4_2:
                        score4 = 2;
                        break;
                    case R.id.option4_3:
                        score4 = 3;
                        break;
                    case R.id.option4_4:
                        score4 = 4;
                        break;
                    case R.id.option4_1:
                        score4 = 1;
                        break;
                    default:
                        score4 = 5;
                }
                int score5;
                int id5 = group5.getCheckedRadioButtonId();
                switch (id5) {
                    case R.id.option5_2:
                        score5 = 2;
                        break;
                    case R.id.option5_3:
                        score5 = 3;
                        break;
                    case R.id.option5_4:
                        score5 = 4;
                        break;
                    case R.id.option5_1:
                        score5 = 1;
                        break;
                    default:
                        score5 = 5;
                }

                Bundle bundle = getIntent().getExtras();
                bundle.putInt("2_1", score1);
                bundle.putInt("2_2", score2);
                bundle.putInt("2_3", score2);
                bundle.putInt("2_4", score2);
                bundle.putInt("2_5", score2);

                Intent intent = new Intent(getApplicationContext(), Page3Activity.class);
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
        title3 = findViewById(R.id.title_3);
        title4 = findViewById(R.id.title_4);
        title5 = findViewById(R.id.title_5);
        group1 = findViewById(R.id.group_1);
        group2 = findViewById(R.id.group_2);
        group3 = findViewById(R.id.group_3);
        group4 = findViewById(R.id.group_4);
        group5 = findViewById(R.id.group_5);
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
