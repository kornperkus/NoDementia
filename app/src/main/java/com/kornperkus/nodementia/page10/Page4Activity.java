package com.kornperkus.nodementia.page10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

public class Page4Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView title;
    private ImageView forwardImg;
    private ImageView menuImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private TextView title1, title2, title3;
    private LinearLayout topic4, topic5;
    private RadioGroup group1, group2, group3;
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
        title.setText(getString(R.string.page10_4_title));

        title1.setText(getString(R.string.page10_4_1));
        title2.setText(getString(R.string.page10_4_2));
        title3.setText(getString(R.string.page10_4_3));

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

                Bundle bundle = getIntent().getExtras();
                bundle.putInt("4_1", score1);
                bundle.putInt("4_2", score2);
                bundle.putInt("4_3", score3);

                JSONObject report = new JSONObject();
                try {
                    report.put("1-1", bundle.getInt("1_1"));
                    report.put("1-2", bundle.getInt("1_2"));
                    report.put("2-1", bundle.getInt("2_1"));
                    report.put("2-2", bundle.getInt("2_2"));
                    report.put("2-3", bundle.getInt("2_3"));
                    report.put("2-4", bundle.getInt("2_4"));
                    report.put("2-5", bundle.getInt("2_5"));
                    report.put("3-1", bundle.getInt("3_1"));
                    report.put("3-2", bundle.getInt("3_2"));
                    report.put("3-3", bundle.getInt("3_3"));
                    report.put("3-4", bundle.getInt("3_4"));
                    report.put("3-5", bundle.getInt("3_5"));
                    report.put("4-1", bundle.getInt("4_1"));
                    report.put("4-2", bundle.getInt("4_2"));
                    report.put("4-3", bundle.getInt("4_3"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String reportText = report.toString();

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mailto:nodementia.nurse3pnu@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "รายงานแบบประเมินความพึงพอใจ No Damentia");
                emailIntent.putExtra(Intent.EXTRA_TEXT, reportText);

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
                finish();
            }

        });
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
        topic4 = findViewById(R.id.topic_4);
        topic5 = findViewById(R.id.topic_5);
        group1 = findViewById(R.id.group_1);
        group2 = findViewById(R.id.group_2);
        group3 = findViewById(R.id.group_3);
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
