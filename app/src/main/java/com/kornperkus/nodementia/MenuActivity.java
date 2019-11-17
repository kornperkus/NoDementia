package com.kornperkus.nodementia;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.Data.AlarmAdapter;
import com.kornperkus.nodementia.Data.Alarm;
import com.kornperkus.nodementia.Data.AlarmViewModel;
import com.kornperkus.nodementia.Data.Contract;

import java.util.Calendar;
import java.util.List;

import static com.kornperkus.nodementia.App.CHANNEL_1_ID;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, NavigationView.OnNavigationItemSelectedListener {

    private ImageView page1Btn, page2Btn, page3Btn, page4Btn, page5Btn, page6Btn, page7Btn, page8Btn, page9Btn, page10Btn;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView, navAlarm;
    private boolean isOpen, isAlramOpen;

    private ImageView addAlarmImg;
    private AlarmViewModel viewModel;
    private RecyclerView alarmRecyclerView;
    private EditText addAlarmEdit;
    private Button addSaveBtn;
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_menu);
        getSupportActionBar().setElevation(0);

        bindView();
        setOnClick();
        setupNav();

        notificationManagerCompat = NotificationManagerCompat.from(this);
        setUpAlarm();

    }

    public void setUpAlarm() {
        alarmRecyclerView = findViewById(R.id.alarm_list);
        alarmRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        alarmRecyclerView.setHasFixedSize(true);

        final AlarmAdapter adapter = new AlarmAdapter(this);
        alarmRecyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
        viewModel.getAllAlarms().observe(this, new Observer<List<Alarm>>() {
            @Override
            public void onChanged(List<Alarm> alarms) {
                adapter.setAlarms(alarms);
            }
        });

        addAlarmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmEdit.setText("");
                addAlarmEdit.setVisibility(View.VISIBLE);
                addSaveBtn.setVisibility(View.VISIBLE);
                addAlarmEdit.requestFocus();
            }
        });

        addSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarmEdit.setVisibility(View.GONE);
                addSaveBtn.setVisibility(View.GONE);
                String title = addAlarmEdit.getText().toString();
                if(!TextUtils.isEmpty(title)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    long time = calendar.getTimeInMillis();
                    viewModel.insert(new Alarm(title, time, 0));
                }
            }
        });

        ItemTouchHelper.SimpleCallback touchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getAlarmAt(viewHolder.getAdapterPosition()));
            }
        };
        ItemTouchHelper itemTouch = new ItemTouchHelper(touchHelper);
        itemTouch.attachToRecyclerView(alarmRecyclerView);

        adapter.setOnItemClickListener(new AlarmAdapter.OnItemClickListener() {
            @Override
            public void onStatusClick(Calendar c, Alarm alarm) {
                if(alarm.getStatus() == Contract.AlarmEntry.STATUS_ON) {
                    alarm.setStatus(Contract.AlarmEntry.STATUS_OFF);
                    cancelAlarm(alarm.getId());
                }else {
                    alarm.setStatus(Contract.AlarmEntry.STATUS_ON);
                    startAlarm(c, alarm);
                }
                viewModel.update(alarm);
            }

            @Override
            public void onTimeClick(Calendar c,Alarm alarm) {
                alarm.setStatus(1);
                viewModel.update(alarm);
                cancelAlarm(alarm.getId());
                startAlarm(c, alarm);
            }
        });
    }

    private void startAlarm(Calendar c, Alarm alarm) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("id", alarm.getId());
        intent.putExtra("title", alarm.getTitle());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, alarm.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,  c.getTimeInMillis(), pendingIntent);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
        else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        }
        Log.i("ALARM", "Alarm set for title: "+alarm.getTitle());
    }

    private void cancelAlarm(int id) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent, 0);
        alarmManager.cancel(pendingIntent);
    }

    private void bindView() {
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        navAlarm = findViewById(R.id.nav_alarm);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
        page1Btn = findViewById(R.id.page1_btn);
        page2Btn = findViewById(R.id.page2_btn);
        page3Btn = findViewById(R.id.page3_btn);
        page4Btn = findViewById(R.id.page4_btn);
        page5Btn = findViewById(R.id.page5_btn);
        page6Btn = findViewById(R.id.page6_btn);
        page7Btn = findViewById(R.id.page7_btn);
        page8Btn = findViewById(R.id.page8_btn);
        page9Btn = findViewById(R.id.page9_btn);
        page10Btn = findViewById(R.id.page10_btn);

        addAlarmImg = findViewById(R.id.alarm_add);
        addAlarmEdit = findViewById(R.id.alarm_add_edittext);
        addSaveBtn = findViewById(R.id.alarm_add_save);
    }

    public void setOnClick() {
        page1Btn.setOnClickListener(this);
        page2Btn.setOnClickListener(this);
        page3Btn.setOnClickListener(this);
        page4Btn.setOnClickListener(this);
        page5Btn.setOnClickListener(this);
        page6Btn.setOnClickListener(this);
        page7Btn.setOnClickListener(this);
        page8Btn.setOnClickListener(this);
        page9Btn.setOnClickListener(this);
        page10Btn.setOnClickListener(this);
        page1Btn.setOnLongClickListener(this);
        page2Btn.setOnLongClickListener(this);
        page3Btn.setOnLongClickListener(this);
        page4Btn.setOnLongClickListener(this);
        page5Btn.setOnLongClickListener(this);
        page6Btn.setOnLongClickListener(this);
        page7Btn.setOnLongClickListener(this);
        page8Btn.setOnLongClickListener(this);
        page9Btn.setOnLongClickListener(this);
        page10Btn.setOnLongClickListener(this);
    }

    public void setupNav() {
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    drawer.openDrawer(navView);
                    drawer.closeDrawer(navAlarm);
                    isOpen = true;
                } else {
                    drawer.closeDrawer(navView);
                    isOpen = false;
                }
            }
        });
        alarmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAlramOpen) {
                    drawer.openDrawer(navAlarm);
                    drawer.closeDrawer(navView);
                    isAlramOpen = true;
                } else {
                    drawer.closeDrawer(navAlarm);
                    isAlramOpen = false;
                }
               /* Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        MainActivity.onNavbarSelect(this, menuItem.getItemId());
        drawer.closeDrawer(GravityCompat.START);
        isOpen = false;
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class clss = null;
        switch (id) {
            case R.id.page1_btn:
                clss = Page1Activity.class;
                break;
            case R.id.page2_btn:
                clss = Page2Activity.class;
                break;
            case R.id.page3_btn:
                clss = Page3Activity.class;
                break;
            case R.id.page4_btn:
                clss = Page4Activity.class;
                break;
            case R.id.page5_btn:
                clss = Page5Activity.class;
                break;
            case R.id.page6_btn:
                clss = Page6Activity.class;
                break;
            case R.id.page7_btn:
                clss = Page8Activity.class;
                break;
            case R.id.page8_btn:
                clss = Page7Activity.class;
                break;
            case R.id.page9_btn:
                clss = Page9Activity.class;
                break;
            case R.id.page10_btn:
                clss = Page10Activity.class;
                break;
        }
        if (clss != null) startActivity(new Intent(getApplicationContext(), clss));
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.page1_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page1_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page2_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page2_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page3_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page3_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page4_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page4_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page5_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page5_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page6_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page6_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page7_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page8_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page8_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page7_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page9_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page9_title), Toast.LENGTH_SHORT).show();
                break;
            case R.id.page10_btn:
                Toast.makeText(getApplicationContext(), getString(R.string.page10_title), Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
