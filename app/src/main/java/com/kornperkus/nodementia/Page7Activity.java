package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Page7Activity extends AppCompatActivity  {
    private ImageView page1Btn, page2Btn, page3Btn, page4Btn, page5Btn, page6Btn, page7Btn, page8Btn, page9Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7);



        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page7);
        getSupportActionBar().setElevation(0);

        bindView();
    }

    private void bindView() {
        page1Btn = findViewById(R.id.page1_btn);
        page2Btn = findViewById(R.id.page2_btn);
        page3Btn = findViewById(R.id.page3_btn);
        page4Btn = findViewById(R.id.page4_btn);
        page5Btn = findViewById(R.id.page5_btn);
        page6Btn = findViewById(R.id.page6_btn);
        page7Btn = findViewById(R.id.page7_btn);
        page8Btn = findViewById(R.id.page8_btn);
        page9Btn = findViewById(R.id.page9_btn);
    }



}
