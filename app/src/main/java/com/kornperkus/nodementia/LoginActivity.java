package com.kornperkus.nodementia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText nameEdit, ageEdit, jobEdit, deseaseEdit;
    private Spinner educationSpinner;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();

        final ArrayAdapter<CharSequence> educationList = ArrayAdapter.createFromResource(this, R.array.education_array, android.R.layout.simple_spinner_item);
        educationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationSpinner.setAdapter(educationList);

        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int educationLevel = educationSpinner.getSelectedItemPosition();
                if(educationLevel == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกระดับการศึกษา", Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("educationPref", 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putInt("educationLevel", educationLevel);
                    editor.apply();
                    Toast.makeText(getApplicationContext(), "Level: "+pref.getInt("educationLevel", 0), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void bindView(){
        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        jobEdit = findViewById(R.id.jobEdit);
        deseaseEdit = findViewById(R.id.diseaseEdit);
        educationSpinner = findViewById(R.id.educationSpinner);
        confirmBtn = findViewById(R.id.confirmBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
