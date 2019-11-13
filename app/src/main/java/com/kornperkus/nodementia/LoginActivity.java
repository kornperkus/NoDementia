package com.kornperkus.nodementia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private EditText nameEdit, ageEdit, jobEdit, deseaseEdit;
    private RadioGroup religionRadio, genderRadio;
    private RadioButton sexMale, sexFemale, religionThai, religionIslam;
    private Spinner educationSpinner;
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

        pref = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0);

        //ถ้าเข้าสู่ระบบแล้ว ให้เปลี่ยนเป็นโหมดแก้ไขข้อมูล
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getBooleanExtra(MainActivity.PREF_KEY_EDIT_ACCOUNT, false)) {
                logoImg.setVisibility(View.GONE);
                headerTv.setText("แก้ไขข้อมูลส่วนตัว");

                nameEdit.setText(pref.getString(MainActivity.PREF_KEY_NAME, "ไม่ระบุ"));
                ageEdit.setText(pref.getString(MainActivity.PREF_KEY_AGE, "ไม่ระบุ"));
                jobEdit.setText(pref.getString(MainActivity.PREF_KEY_JOB, "ไม่ระบุ"));
                deseaseEdit.setText(pref.getString(MainActivity.PREF_KEY_DISEASE, "ไม่ระบุ"));

                int gender = pref.getInt(MainActivity.PREF_KEY_GENDER, 0);
                if(gender == R.id.sex_male) sexMale.setChecked(true);
                else sexFemale.setChecked(true);

                int education = pref.getInt(MainActivity.PREF_KEY_EDUCATION, 0);
                if (education == 1) educationSpinner.setSelection(1);
                else if (education == 2) educationSpinner.setSelection(2);
                else if (education == 3) educationSpinner.setSelection(3);

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
                String job = jobEdit.getText().toString();
                String disease = deseaseEdit.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(job)) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!sexMale.isChecked() && !sexFemale.isChecked() || !religionThai.isChecked()&& !religionIslam.isChecked()) {
                    Toast.makeText(getApplicationContext(), "กรุณาเลือกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(disease)) {
                    disease = "ไม่มี";
                }
                int gender = genderRadio.getCheckedRadioButtonId();
                int religion = religionRadio.getCheckedRadioButtonId();
                int education = educationSpinner.getSelectedItemPosition();

                if(education == 0) Toast.makeText(getApplicationContext(), "โปรดเลือกระดับการศึกษา", Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, true);
                    editor.putString(MainActivity.PREF_KEY_NAME, name);
                    editor.putString(MainActivity.PREF_KEY_AGE, age);
                    editor.putString(MainActivity.PREF_KEY_JOB, job);
                    editor.putString(MainActivity.PREF_KEY_DISEASE, disease);
                    editor.putInt(MainActivity.PREF_KEY_GENDER, gender);
                    editor.putInt(MainActivity.PREF_KEY_RELIGION, religion);
                    editor.putInt(MainActivity.PREF_KEY_EDUCATION, education);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
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
        jobEdit = findViewById(R.id.jobEdit);
        deseaseEdit = findViewById(R.id.diseaseEdit);
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
