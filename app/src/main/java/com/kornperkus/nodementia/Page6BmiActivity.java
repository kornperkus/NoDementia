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

public class Page6BmiActivity extends AppCompatActivity {

    public static final String PREF_KEY_BMI = "bmiKey";
    private EditText editHeight, editWeight;
    private Button btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page6);
        getSupportActionBar().setElevation(0);

        bindView();
        btnCal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String h = editHeight.getText().toString();
                String w = editWeight .getText().toString();
                if(h.isEmpty() || w.isEmpty()){
                    Toast.makeText(getApplicationContext(), "กรุณาป้อนข้อมูลให้ครบถ้วนค่ะ", Toast.LENGTH_SHORT).show();
                }else {
                    double height = Float.parseFloat(h);
                    double weight = Float.parseFloat(w);
                    double result = weight/(Math.pow(height, 2));

                    //save bmi value
                    SharedPreferences.Editor edit = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).edit();
                    edit.putLong(MainActivity.PREF_KEY_BMI_VALUE, Double.doubleToRawLongBits(result*10000));
                    edit.apply();

                    startActivity(new Intent(getApplicationContext(), Page6ResultActivity.class));
                    finish();
                }
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page6PrimaryDark));
    }

    private void bindView(){
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        btnCal = findViewById(R.id.bnt_cal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
