package com.kornperkus.nodementia.mmse;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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

import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.R;

public class Mmse5Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView pageTitle;
    private ImageView forwardImg;
    private RadioGroup option1, option2;
    private RadioButton option1_1, option1_2, option1_3, option2_1, option2_2, option2_3;
    private int score;
    private boolean exitConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_5);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText("แบบประเมินสภาพสมองเสื่อม");
        forwardImg.setVisibility(View.INVISIBLE);

        option1_1.setOnCheckedChangeListener(this);
        option1_2.setOnCheckedChangeListener(this);
        option1_3.setOnCheckedChangeListener(this);
        option2_1.setOnCheckedChangeListener(this);
        option2_2.setOnCheckedChangeListener(this);
        option2_3.setOnCheckedChangeListener(this);
        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1_1.isChecked()) Toast.makeText(getApplicationContext(), "1_1", Toast.LENGTH_SHORT).show();
                else if(option1_2.isChecked()) Toast.makeText(getApplicationContext(), "1_2", Toast.LENGTH_SHORT).show();
                else if(option1_3.isChecked()) Toast.makeText(getApplicationContext(), "1_3", Toast.LENGTH_SHORT).show();
                else if(option2_1.isChecked()) Toast.makeText(getApplicationContext(), "2_1", Toast.LENGTH_SHORT).show();
                else if(option2_2.isChecked()) Toast.makeText(getApplicationContext(), "2_2", Toast.LENGTH_SHORT).show();
                else if(option2_3.isChecked()) Toast.makeText(getApplicationContext(), "2_3", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Mmse6_1Activity.class));
            }
        });
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        forwardImg = findViewById(R.id.forwardBtn);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        forwardImg.setVisibility(View.VISIBLE);
        if(option1_1.isChecked() || option1_2.isChecked() || option1_3.isChecked()) {
            option2.clearCheck();
        }
        if(option2_1.isChecked() || option2_2.isChecked() || option2_3.isChecked()) {
            option1.clearCheck();
        }
    }
}
