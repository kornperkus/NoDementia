package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Page7Activity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
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
        setOnCLick();
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

    public void setOnCLick() {
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
        page4Btn.setOnClickListener(this);
        page5Btn.setOnClickListener(this);
        page6Btn.setOnClickListener(this);
        page7Btn.setOnClickListener(this);
        page8Btn.setOnClickListener(this);
        page9Btn.setOnClickListener(this);
        page1Btn.setOnLongClickListener(this);
        page2Btn.setOnLongClickListener(this);
        page3Btn.setOnLongClickListener(this);
        page4Btn.setOnLongClickListener(this);
        page5Btn.setOnLongClickListener(this);
        page6Btn.setOnLongClickListener(this);
        page7Btn.setOnLongClickListener(this);
        page8Btn.setOnLongClickListener(this);
        page9Btn.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class clss = null;
        switch (id) {
            case R.id.page1_btn:
                clss = com.kornperkus.nodementia.page7.Page1Activity.class;
                break;
            case R.id.page2_btn:
                clss = com.kornperkus.nodementia.page7.Page2Activity.class;
                break;
            case R.id.page3_btn:
                clss = com.kornperkus.nodementia.page7.Page3Activity.class;
                break;
            case R.id.page4_btn:
                clss = com.kornperkus.nodementia.page7.Page4Activity.class;
                break;
            case R.id.page5_btn:
                clss = com.kornperkus.nodementia.page7.Page5Activity.class;
                break;
            case R.id.page6_btn:
                clss = com.kornperkus.nodementia.page7.Page6Activity.class;
                break;
            case R.id.page7_btn:
                clss = com.kornperkus.nodementia.page7.Page7Activity.class;
                break;
            case R.id.page8_btn:
                clss = com.kornperkus.nodementia.page7.Page8Activity.class;
                break;
            case R.id.page9_btn:
                clss = com.kornperkus.nodementia.page7.Page9Activity.class;
                break;
        }
        if(clss != null) startActivity(new Intent(getApplicationContext(), clss));
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.page1_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_1_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page2_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_2_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page3_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_3_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page4_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_4_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page5_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_5_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page6_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_6_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page7_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_7_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page8_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_8_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page9_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_9_title), Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }
}
