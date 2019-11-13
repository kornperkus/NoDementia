package com.kornperkus.nodementia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Page6ResultActivity extends AppCompatActivity {
    private TextView title, resultTv, resultTextTv, resultTextBodyTv;
    private double bmiValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page6);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText("ค่าดัชนีมวลกาย (BMI)");

        long bmi = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).getLong(MainActivity.PREF_KEY_BMI_VALUE, 0);
        bmiValue = Double.longBitsToDouble(bmi);
        resultTv.setText(String.format("%.2f", bmiValue));
        String resultText;
        if(bmiValue <= 18.5) {
            resultTextTv.setText(getText(R.string.bmi_1_title));
            resultTextBodyTv.setText(getText(R.string.bmi_1_body));
        }else if(bmiValue <= 24.9) {
            resultTextTv.setText(getText(R.string.bmi_2_title));
            resultTextBodyTv.setText(getText(R.string.bmi_2_body));
        }else if(bmiValue <= 29.9) {
            resultTextTv.setText(getText(R.string.bmi_3_title));
            resultTextBodyTv.setText(getText(R.string.bmi_3_body));
        }else {
            resultTextTv.setText(getText(R.string.bmi_4_title));
            resultTextBodyTv.setText(getText(R.string.bmi_4_body));
        }

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page6PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        resultTv = findViewById(R.id.result_tv);
        resultTextTv = findViewById(R.id.result_text_tv);
        resultTextBodyTv = findViewById(R.id.result_text_body_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
