package com.kornperkus.nodementia.mmse;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.R;

public class Mmse4Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView pageTitle, bigTitleTv, titleTv;
    private RadioButton correct, inCorrect;
    private ImageView forwardImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();

        pageTitle.setText("แบบประเมินสภาพสมองเสื่อม");
        bigTitleTv.setText("Attention / Calculation ทดสอบสมาธิโดยให้คิดเลขในใจ");
        titleTv.setText("\tข้อนี้เป็นการคิดเลขในใจเพื่อทดสอบสมาธิ คุณ (ตา,ยาย,...) คิดเลขในใจเป็นไหม ?");
        forwardImg.setVisibility(View.INVISIBLE);
        correct.setText("ตอบคิดเป็น");
        inCorrect.setText("ตอบคิดไม่เป็นหรือไม่ตอบ");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        correct.setOnCheckedChangeListener(this);
        inCorrect.setOnCheckedChangeListener(this);
        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(correct.isChecked()) startActivity(new Intent(getApplicationContext(), Mmse4_1Activity.class));
                else if(inCorrect.isChecked()) startActivity(new Intent(getApplicationContext(), Mmse4_2Activity.class));
            }
        });
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        bigTitleTv = findViewById(R.id.bigTitleTv);
        titleTv = findViewById(R.id.titleTv);
        correct = findViewById(R.id.correct);
        inCorrect = findViewById(R.id.incorrect);
        forwardImg = findViewById(R.id.forwardBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        forwardImg.setVisibility(View.VISIBLE);
    }
}
