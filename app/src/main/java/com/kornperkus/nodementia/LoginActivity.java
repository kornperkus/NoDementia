package com.kornperkus.nodementia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private ImageView logoImg;
    private TextView headerTv;
    private EditText nameEdit, ageEdit;
    private RadioGroup religionRadio, genderRadio;
    private RadioButton sexMale, sexFemale, religionThai, religionIslam;
    private Spinner educationSpinner, jobSpinner; //diseaseSpinner;
    private CheckBox disease1, disease2, disease3, disease4, disease5;
    private Button confirmBtn;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindView();

        final ArrayAdapter<CharSequence> educationList = ArrayAdapter.createFromResource(this, R.array.education_array, android.R.layout.simple_spinner_item);
        educationList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        educationSpinner.setAdapter(educationList);

        final ArrayAdapter<CharSequence> jobList = ArrayAdapter.createFromResource(this, R.array.job_array, android.R.layout.simple_spinner_item);
        jobList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(jobList);

       // final ArrayAdapter<CharSequence> diseaseList = ArrayAdapter.createFromResource(this, R.array.disease_array, android.R.layout.simple_spinner_item);
       // diseaseList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //diseaseSpinner.setAdapter(diseaseList);

        pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);

        //ถ้าเข้าสู่ระบบแล้ว ให้เปลี่ยนเป็นโหมดแก้ไขข้อมูล
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getBooleanExtra(MainActivity.PREF_KEY_EDIT_ACCOUNT, false)) {
                logoImg.setVisibility(View.GONE);
                headerTv.setText("แก้ไขข้อมูลส่วนตัว");

                nameEdit.setText(pref.getString(MainActivity.PREF_KEY_NAME, "ไม่ระบุ"));
                ageEdit.setText(pref.getString(MainActivity.PREF_KEY_AGE, "ไม่ระบุ"));

                int gender = pref.getInt(MainActivity.PREF_KEY_GENDER, 0);
                if(gender == R.id.sex_male) sexMale.setChecked(true);
                else sexFemale.setChecked(true);

                int education = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);
                if (education == 1) educationSpinner.setSelection(1);
                else if (education == 2) educationSpinner.setSelection(2);
                else if (education == 3) educationSpinner.setSelection(3);

                int job = pref.getInt(MainActivity.PREF_KEY_JOB, 0);
                if (job == 1) educationSpinner.setSelection(1);
                else if (job == 2) jobSpinner.setSelection(2);
                else if (job == 3) jobSpinner.setSelection(3);
                else if (job == 4) jobSpinner.setSelection(4);
                else if (job == 5) jobSpinner.setSelection(5);
                else if (job == 6) jobSpinner.setSelection(6);
                else if (job == 7) jobSpinner.setSelection(7);

                boolean disease1Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE1, false);
                boolean disease2Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE2, false);
                boolean disease3Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE3, false);
                boolean disease4Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE4, false);
                boolean disease5Value = pref.getBoolean(MainActivity.PREF_KEY_DISEASE5, false);
                disease1.setChecked(disease1Value);
                disease2.setChecked(disease2Value);
                disease3.setChecked(disease3Value);
                disease4.setChecked(disease4Value);
                disease5.setChecked(disease5Value);
                /*if (disease == 1) diseaseSpinner.setSelection(1);
                else if (disease == 2) diseaseSpinner.setSelection(2);
                else if (disease == 3) diseaseSpinner.setSelection(3);
                else if (disease == 4) diseaseSpinner.setSelection(4);
                else if (disease == 5) diseaseSpinner.setSelection(5);
                else if (disease == 6) diseaseSpinner.setSelection(6);*/

                int religion = pref.getInt(MainActivity.PREF_KEY_RELIGION, 0);
                if(religion == R.id.religion_thai) religionThai.setChecked(true);
                else religionIslam.setChecked(true);
            }
        }


        onSubmit();
    }

    private void onSubmit() {
        confirmBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String age = ageEdit.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(age)) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!sexMale.isChecked() && !sexFemale.isChecked() || !religionThai.isChecked()&& !religionIslam.isChecked()) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    return;
                }
                int gender = genderRadio.getCheckedRadioButtonId();
                int religion = religionRadio.getCheckedRadioButtonId();
                int education = educationSpinner.getSelectedItemPosition();
                int job = jobSpinner.getSelectedItemPosition();
               // int disease = diseaseSpinner.getSelectedItemPosition();

                if (job == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกอาชีพ", Toast.LENGTH_SHORT).show();
                else if (education == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกระดับการศึกษา", Toast.LENGTH_SHORT).show();
               // else if(disease == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกโรคประจำตัว", Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, true);
                    editor.putString(MainActivity.PREF_KEY_NAME, name);
                    editor.putString(MainActivity.PREF_KEY_AGE, age);
                    editor.putInt(MainActivity.PREF_KEY_JOB, job);
                   // editor.putInt(MainActivity.PREF_KEY_DISEASE, disease);
                    editor.putInt(MainActivity.PREF_KEY_GENDER, gender);
                    editor.putInt(MainActivity.PREF_KEY_RELIGION, religion);
                    editor.putInt(MainActivity.PREF_KEY_EDUCATION, education);
                    editor.putBoolean(MainActivity.PREF_KEY_DISEASE1, disease1.isChecked());
                    editor.putBoolean(MainActivity.PREF_KEY_DISEASE2, disease2.isChecked());
                    editor.putBoolean(MainActivity.PREF_KEY_DISEASE3, disease3.isChecked());
                    editor.putBoolean(MainActivity.PREF_KEY_DISEASE4, disease4.isChecked());
                    editor.putBoolean(MainActivity.PREF_KEY_DISEASE5, disease5.isChecked());

                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), Page9ActivityFirstTime.class));
                    Toast.makeText(getApplicationContext(), "บันทึกข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void bindView(){
        logoImg = findViewById(R.id.logo);
        headerTv = findViewById(R.id.header);
        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        jobSpinner = findViewById(R.id.jobSpinner);
     //   diseaseSpinner = findViewById(R.id.diseaseSpinner);
        disease1 = findViewById(R.id.disease_1);
        disease2 = findViewById(R.id.disease_2);
        disease3 = findViewById(R.id.disease_3);
        disease4 = findViewById(R.id.disease_4);
        disease5 = findViewById(R.id.disease_5);
        genderRadio = findViewById(R.id.sex_radio);
        religionRadio = findViewById(R.id.religion_radio);
        sexMale = findViewById(R.id.sex_male);
        sexFemale = findViewById(R.id.sex_female);
        religionThai = findViewById(R.id.religion_thai);
        religionIslam = findViewById(R.id.religion_islam);
        educationSpinner = findViewById(R.id.educationSpinner);
        confirmBtn = findViewById(R.id.confirmBtn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
