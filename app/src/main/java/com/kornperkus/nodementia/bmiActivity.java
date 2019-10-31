package com.kornperkus.nodementia;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class bmiActivity extends AppCompatActivity {

    private EditText editHeight, editWeight;
    private Button btnCal;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setElevation(0);

        bindView();
        btnCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int height = Integer.parseInt(editHeight.getText().toString());
                int weight = Integer.parseInt(editWeight .getText().toString());
                double result = weight/(Math.pow(height, 2));
                resultTv.setText(String.format("BMI: %.2f", result*1000));
            }
        });
    }

    private void bindView(){
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        btnCal = findViewById(R.id.bnt_cal);
        resultTv = findViewById(R.id.result_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
