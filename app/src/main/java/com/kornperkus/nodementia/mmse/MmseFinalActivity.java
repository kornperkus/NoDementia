package com.kornperkus.nodementia.mmse;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.R;

public class MmseFinalActivity extends AppCompatActivity {

    private TextView title, score, body;

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
        title.setText("แบบประเมินสภาพสมองเสื่อม");
        score.setText("คะแนนที่ได้รับ 15/20");
        body.setText("ไม่มีความเสี่ยงของภาวะสมองเสื่อมจากเครื่องมือนี้");

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page1PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        score = findViewById(R.id.page_score);
        body = findViewById(R.id.page_body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
