package com.kornperkus.nodementia.mmse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.MenuActivity;
import com.kornperkus.nodementia.Page5Activity;
import com.kornperkus.nodementia.R;

public class MmseFinalActivity extends AppCompatActivity {

    private TextView pageTitle, scoreTv, body;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmse_final);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page1);
        getSupportActionBar().setElevation(0);

        bindView();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
        score = pref.getInt(MainActivity.PREF_KEY_MMSE_VALUE, 0);
        int educationLevel = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);

        //get max score
        int maxScore = 30;
        if(educationLevel == 1) maxScore = 23;

        pageTitle.setText(getString(R.string.page5_title));
        scoreTv.setText("คะแนนที่ได้รับ "+score+"/"+maxScore);

        String resultText = "ไม่มีความเสี่ยงของภาวะสมองเสื่อมจากเครื่องมือนี้";
        if(educationLevel == 1) {
            if(score <= 14) resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ส่งไปรักษาต่อ";
        }else if(educationLevel == 2) {
            if(score <= 17) resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ส่งไปรักษาต่อ";
        }else if(educationLevel == 2) {
            if(score <= 22) resultText = "สงสัยว่ามีสภาวะสมองเสื่อม ส่งไปรักษาต่อ";
        }
        body.setText(resultText);

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page1PrimaryDark));

        Log.i("SCORE", "Score = "+score);
    }

    private void bindView(){
        pageTitle = findViewById(R.id.page_title);
        scoreTv = findViewById(R.id.page_score);
        body = findViewById(R.id.page_body);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
