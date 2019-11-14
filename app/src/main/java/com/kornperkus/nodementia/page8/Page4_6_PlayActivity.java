package com.kornperkus.nodementia.page8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kornperkus.nodementia.AccountActivity;
import com.kornperkus.nodementia.Data.Card;
import com.kornperkus.nodementia.Data.CardProvider;
import com.kornperkus.nodementia.LoginActivity;
import com.kornperkus.nodementia.MainActivity;
import com.kornperkus.nodementia.Page6ResultActivity;
import com.kornperkus.nodementia.R;
import com.kornperkus.nodementia.mmse.MmseFinalActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Page4_6_PlayActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageView[] imgCards;
    private CardProvider provider;
    private List<Card> cards;
    private List<Card> openned;
    private int clickCount, correctCount, level;
    private Animation fadeIn, fadeOut;
    private ImageView menuImg, alarmImg;
    private DrawerLayout drawer;
    private NavigationView navView;
    private boolean isOpen;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_4_6_play);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_4);
        getSupportActionBar().setElevation(0);

        level = new Random().nextInt(10) + 1;

        //Load layout and setup bord size
        imgCards = new ImageView[12];
        bindViews();

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        provider = new CardProvider(this);
        cards = provider.getCardLists(level);
        openned = new ArrayList<>();
        setupNav();

        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished/1000 == 30) Toast.makeText(getApplicationContext(), "เหลือเวลาอีก 30 วินาที", Toast.LENGTH_SHORT).show();
                if(millisUntilFinished/1000 == 10) Toast.makeText(getApplicationContext(), "เหลือเวลาอีก 10 วินาที!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "หมดเวลา! คุณแพ้", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Page4_6_PlayActivity.class);
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null) timer.cancel();
    }

    @Override
    public void onClick(View view) {
        if(clickCount==2) {
            closeCard();
        }
        openCard(Integer.parseInt(view.getTag().toString()));
        clickCount++;

        if(clickCount==2) {
            checkMatch();
        }
    }

    private void openCard(int index) {
        Card card = cards.get(index);
        //if already open, just return
        if(card.isOpen()) return;

        card.setIsOpen(true);
        ImageView img = imgCards[index];
        img.setImageResource(card.getImgeResource());
        img.startAnimation(fadeIn);
    }

    private void closeCard() {
        for(int i=0; i<cards.size(); i++) {
            Card card = cards.get(i);
            if(card.isCorrect()) {
                continue;
            }
            card.setIsOpen(false);
            imgCards[i].startAnimation(fadeOut);
            imgCards[i].setImageResource(0);
        }
        clickCount = 0;
    }

    private void checkMatch() {
        openned.clear();
        for(int i=0; i<cards.size(); i++) {
            Card card = cards.get(i);
            //if card is openning and not correct yet then add
            if(card.isOpen()&&!card.isCorrect()) {
                openned.add(card);
            }
        }
        //if matches set isCorrect to true (both)
        if(openned.get(0).getValue()==openned.get(1).getValue()) {
            cards.get(openned.get(0).getIndex()).setIsCorrect(true);
            cards.get(openned.get(1).getIndex()).setIsCorrect(true);
            correctCount += 2;
        }
        if(correctCount>=cards.size()) {
            gameWin();
        }
    }

    private void gameWin() {
        showMessage("ชนะแล้ว!");
        Intent intent = new Intent(getApplicationContext(), Page4_6_PlayActivity.class);
        startActivity(intent);
        finish();
    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void bindViews() {
        for(int i=0; i<imgCards.length; i++) {
            int resId = getResources().getIdentifier("card_"+(i+1), "id", getPackageName());
            imgCards[i] = findViewById(resId);
            imgCards[i].setOnClickListener(this);
            imgCards[i].setTag(i);
        }
        drawer = findViewById(R.id.drawer);
        navView = findViewById(R.id.nav_view);
        menuImg = findViewById(R.id.ic_menu);
        alarmImg = findViewById(R.id.ic_clock);
    }

    public void setupNav() {
        menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    drawer.openDrawer(GravityCompat.START);
                    isOpen = true;
                } else {
                    drawer.closeDrawer(GravityCompat.START);
                    isOpen = false;
                }
            }
        });
        alarmImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        navView.setNavigationItemSelectedListener(this);
    }

    public void showConfirm() {
        new AlertDialog.Builder(Page4_6_PlayActivity.this)
                .setTitle("ออกจากระบบ")
                .setMessage("หากออกจากระบบข้อมูลทั้งหมดของท่านจะศูนย์หาย")
                .setCancelable(false)
                .setPositiveButton("ออกจากระบบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        logout();
                    }
                }).setNegativeButton("ยกเลิก",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void logout() {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).edit();
        editor.putBoolean(MainActivity.PREF_KEY_LOGIN_STATUS, false);
        editor.apply();
        Toast.makeText(getApplicationContext(), "ออกจากระบบแล้ว", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_accout:
                startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                break;
            case R.id.nav_edit:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra(MainActivity.PREF_KEY_EDIT_ACCOUNT, true);
                startActivity(intent);
                break;
            case R.id.nav_bmi:
                startActivity(new Intent(getApplicationContext(), Page6ResultActivity.class));
                break;
            case R.id.nav_mmse:
                startActivity(new Intent(getApplicationContext(), MmseFinalActivity.class));
                break;
            case R.id.nav_logout:
                showConfirm();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        isOpen = false;
        return true;
    }
}
