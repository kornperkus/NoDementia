package com.kornperkus.nodementia.mmse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.R;

public class Mmse4_1Activity extends AppCompatActivity {
    private TextView pageTitle, bigTitleTv, text1Tv, text2Tv;
    private CheckBox option1, option2, option3, option4, option5;
    private ImageView forwardImg;
    private int score;
    private boolean exitConfirm;

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
        text1Tv.setText(getString(R.string.mmse_4_text1_1));
        text2Tv.setText(getString(R.string.mmse_4_text1_2));
        option1.setText("ลบได้ 1");
        option2.setText("ลบได้ 2");
        option3.setText("ลบได้ 3");
        option4.setText("ลบได้ 4");
        option5.setText("ลบได้ 5");

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