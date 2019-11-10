package com.kornperkus.nodementia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText nameEdit;//, ageEdit, jobEdit, deseaseEdit;
    private RadioGroup religionRadio;//sexRadio, ;
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
                String name = nameEdit.getText().toString();

                //int sexId = sexRadio.getCheckedRadioButtonId();
               // RadioButton sex = findViewById(sexId);
                int religionId = religionRadio.getCheckedRadioButtonId();
                RadioButton religion = findViewById(religionId);

                int educationLevel = educationSpinner.getSelectedItemPosition();
                if(educationLevel == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกระดับการศึกษา", Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, true);
                    editor.putInt(MainActivity.PREF_KEY_EDUCATION, educationLevel);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    finish();
                }
            }
        });
    }

    private void bindView(){
        nameEdit = findViewById(R.id.nameEdit);
        /*ageEdit = findViewById(R.id.ageEdit);
        jobEdit = findViewById(R.id.jobEdit);
        deseaseEdit = findViewById(R.id.diseaseEdit);
        sexRadio = findViewById(R.id.sex_radio);*/
        religionRadio = findViewById(R.id.religion_radio);
        educationSpinner = findViewById(R.id.educationSpinner);
        confirmBtn = findViewById(R.id.confirmBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
