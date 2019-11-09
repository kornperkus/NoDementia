package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.mmse.Mmse1_1Activity;

public class Page5Activity extends AppCompatActivity {

    private TextView title, headline, body;
    Button beginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);
        //TODO: คิดคะแนน และแสดงผลลัพธ์
        bindView();

        beginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Mmse1_1Activity.class));
            }
        });
    }

    public void bindView() {
        beginBtn = findViewById(R.id.beginBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
