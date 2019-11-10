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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.R;

public class Mmse8Activity extends AppCompatActivity {
    private TextView pageTitle, bigTitleTv, titleTv;
    private CheckBox option1, option2, option3;
    private ImageView forwardImg;
    private int score;
    private boolean exitConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_8);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        score = getIntent().getIntExtra(Page5Activity.MMSE_SCORE_KEY, 0);

        pageTitle.setText(getString(R.string.page5_title));
        bigTitleTv.setText(getString(R.string.mmse_8_title));
        titleTv.setText("\t\"ฟังดีๆ นะเดี๋ยวผม(ดิฉัน) จะส่งกระดาษให้ แล้วให้คุณ(ตา,ยาย,...)รับด้วยมือขวา พับครึ่งด้วยมือทั้งสองข้าง แล้ววางไว้ที่...........\" (พื้น, โต๊ะ, เตียง) \n\n\"ผู้ทดสอบแสดงกระดาษเปล่าขนาดประมาณ เอ-4ไม่มีรอยพับ ให้ผู้ถูกทดสอบ\"");

        option1.setText("รับด้วยมือขวา");
        option2.setText("พับครึ่ง");
        option3.setText("วางไว้ที่........... (พื้น, โต๊ะ, เตียง)");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));

        forwardImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(option1.isChecked()) score+=1;
                if(option2.isChecked()) score+=1;
                if(option3.isChecked()) score+=1;

                SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
                int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

                Intent intent = null;

                //Check education level
                if(educationLevel == 1) intent = new Intent(getApplicationContext(), Mmse11Activity.class);
                else intent = new Intent(getApplicationContext(), Mmse9Activity.class);

                intent.putExtra(Page5Activity.MMSE_SCORE_KEY, score);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        bigTitleTv = findViewById(R.id.bigTitleTv);
        titleTv = findViewById(R.id.titleTv);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
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
