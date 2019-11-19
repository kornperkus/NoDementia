package com.kornperkus.nodementia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

import com.kornperkus.nodementia.Data.Alarm;
import com.kornperkus.nodementia.Data.AlarmViewModel;
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PREF_KEY_MAIN = "mainPrefKey";
    public static final String PREF_KEY_LOGIN_STATUS = "loginStatusKey";
    public static final String PREF_KEY_EDIT_ACCOUNT = "editAccountKey";
    public static final String PREF_KEY_BMI_VALUE = "bmiValueKey";
    public static final String PREF_KEY_MMSE_VALUE = "mmseValueKey";

    public static final String PREF_KEY_NAME = "nameKey";
    public static final String PREF_KEY_GENDER = "genderKey";
    public static final String PREF_KEY_AGE = "ageKey";
    public static final String PREF_KEY_RELIGION = "religionKey";
    public static final String PREF_KEY_JOB= "jobKey";
    public static final String PREF_KEY_EDUCATION = "educationKey";
    public static final String PREF_KEY_DISEASE = "diseaseKey";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: หัวข้อหน้าแรกกับหน้าป้องกัน
        //TODO: เปลี่ยนเสียง mmse
        //TODO: เสียงด้านจิตวิญญาน
        //TODO: เพิ่มเสียงทุกหน้า
        //TODO: ใส่รูปหน้าเนื้อหา
        //TODO: สรุปผลประเมินความพึงพอใจ
        //TODO: สรุปคะแนนที่ส่งในเมล
        //TODO: วิดีโอหน้าอาการกับจีบแอล
        //TODO: เพิ่มคู่มือการใช้งาน
        //TODO: เสียงประกอบหน้าแรก
        //TODO: นาฬิกาปลุก
        //TODO: pre-post test พึงพอใจ
        bindView();

        final SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_KEY_MAIN, 0);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(pref.getBoolean(PREF_KEY_LOGIN_STATUS, false)) startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                finish();
            }
        }, 1000);
    }

    public static void showLogoutConfirm(final Activity activity){
        new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.logout_title))
                .setMessage(activity.getString(R.string.logout_confirm))
                .setCancelable(false)
                .setPositiveButton(activity.getString(R.string.logout_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logout(activity);
                    }
                }).setNegativeButton(activity.getString(R.string.logout_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    private static void logout(Activity activity) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).edit();
        editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, false);
        editor.apply();
        Toast.makeText(activity, "ออกจากระบบแล้ว", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void onNavbarSelect(Activity activity, int id) {
        switch (id) {
            case R.id.nav_home:
                activity.startActivity(new Intent(activity.getApplicationContext(), MenuActivity.class));
                break;
            case R.id.nav_accout:
                activity.startActivity(new Intent(activity.getApplicationContext(), AccountActivity.class));
                break;
            case R.id.nav_edit:
                Intent intent = new Intent(activity.getApplicationContext(), LoginActivity.class);
                intent.putExtra(MainActivity.PREF_KEY_EDIT_ACCOUNT, true);
                activity.startActivity(intent);
                break;
            case R.id.nav_bmi:
                activity.startActivity(new Intent(activity.getApplicationContext(), Page6ResultActivity.class));
                break;
            case R.id.nav_mmse:
                activity.startActivity(new Intent(activity.getApplicationContext(), MmseFinalActivity.class));
                break;
            case R.id.nav_logout:
               showLogoutConfirm(activity);
                break;
        }
    }

    private void bindView(){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
