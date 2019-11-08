package com.kornperkus.nodementia.mmse;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.R;

public class Mmse4_2Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private TextView pageTitle, bigTitleTv, text1Tv, text2Tv;
    private RadioButton option1, option2, option3, option4, option5;
    private ImageView forwardImg;

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

        pageTitle.setText("แบบประเมินสภาพสมองเสื่อม");
        bigTitleTv.setText("Attention / Calculation ทดสอบสมาธิโดยให้คิดเลขในใจ");
        text1Tv.setText(getString(R.string.mmse_4_text2_1));
        text2Tv.setText(getString(R.string.mmse_4_text2_2));
        option1.setText("สะกดคำว่า วอแหวน ได้");
        option2.setText("สะกดคำว่า สระอา ได้");
        option3.setText("สะกดคำว่า นอหนู ได้");
        option4.setText("สะกดคำว่า สระอะ ได้");
        option5.setText("สะกดคำว่า มอม้า ได้");
        forwardImg.setVisibility(View.INVISIBLE);


        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));
/*
        correct.setOnCheckedChangeListener(this);
        inCorrect.setOnCheckedChangeListener(this);
        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(correct.isChecked()) Toast.makeText(Mmse4_1Activity.this, "ถูก", Toast.LENGTH_SHORT).show();
                else if(inCorrect.isChecked()) Toast.makeText(Mmse4_1Activity.this, "ผิด", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Mmse2_2_1Activity.class));
            }
        });*/
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        forwardImg.setVisibility(View.VISIBLE);
    }
}
