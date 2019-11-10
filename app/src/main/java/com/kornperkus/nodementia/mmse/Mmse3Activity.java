package com.kornperkus.nodementia.mmse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.R;

public class Mmse3Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView pageTitle;
    private ImageView forwardImg;
    private CheckBox option1_1, option1_2, option1_3, option2_1, option2_2, option2_3;
    private int score;
    private boolean exitConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_3);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText(getString(R.string.page5_title));

        option1_1.setOnClickListener(this);
        option1_2.setOnClickListener(this);
        option1_3.setOnClickListener(this);
        option2_1.setOnClickListener(this);
        option2_2.setOnClickListener(this);
        option2_3.setOnClickListener(this);

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1_1.isChecked()) score+=1;
                if(option1_2.isChecked()) score+=1;
                if(option1_3.isChecked()) score+=1;
                if(option2_1.isChecked()) score+=1;
                if(option2_2.isChecked()) score+=1;
                if(option2_3.isChecked()) score+=1;

                SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
                int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

                Intent intent = null;

                //Check education level
                if(educationLevel == 1) intent = new Intent(getApplicationContext(), Mmse5Activity.class);
                else intent = new Intent(getApplicationContext(), Mmse4Activity.class);

                intent.putExtra(Page5Activity.MMSE_SCORE_KEY, score);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                Log.i("SCORE", "Score = "+score);
            }
        });
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        forwardImg = findViewById(R.id.forwardBtn);
        option1_1 = findViewById(R.id.option1_1);
        option1_2 = findViewById(R.id.option1_2);
        option1_3 = findViewById(R.id.option1_3);
        option2_1 = findViewById(R.id.option2_1);
        option2_2 = findViewById(R.id.option2_2);
        option2_3 = findViewById(R.id.option2_3);
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
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.option1_1 || id == R.id.option1_2 || id == R.id.option1_3) {
            option2_1.setChecked(false);
            option2_2.setChecked(false);
            option2_3.setChecked(false);
        }
        if(id == R.id.option2_1 || id == R.id.option2_2 || id == R.id.option2_3){
            option1_1.setChecked(false);
            option1_2.setChecked(false);
            option1_3.setChecked(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
