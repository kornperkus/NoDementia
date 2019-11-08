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

public class Mmse7Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
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
        bigTitleTv.setText("Repetition ทดสอบการพูดซ้ำคําที่ได้ยิน (พูดตามได้ถูกต้อง 1 คะแนน)");
        titleTv.setText("\"ตั้งใจฟังผม(ดิฉัน) นะ เมื่อผม(ดิฉัน) พูดข้อความนี้แล้วให้คุณ(ตา,ยาย,...) พูดตามผม(ดิฉัน) จะบอกเพียงเที่ยวเดียว\"\n\n\"ใครใคร่ขายไก่ไข่\"");
        forwardImg.setVisibility(View.INVISIBLE);

        correct.setText("พูดตามได้");
        inCorrect.setText("พูดตามไม่ได้");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        correct.setOnCheckedChangeListener(this);
        inCorrect.setOnCheckedChangeListener(this);
        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(correct.isChecked()) Toast.makeText(Mmse7Activity.this, "ถูก", Toast.LENGTH_SHORT).show();
                else if(inCorrect.isChecked()) Toast.makeText(Mmse7Activity.this, "ผิด", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Mmse1_2Activity.class));
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
