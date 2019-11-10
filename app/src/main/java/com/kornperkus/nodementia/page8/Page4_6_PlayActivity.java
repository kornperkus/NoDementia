package com.kornperkus.nodementia.page8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.Data.Card;
import com.kornperkus.nodementia.Data.CardProvider;
import com.kornperkus.nodementia.Data.Contract;
import com.kornperkus.nodementia.R;

import java.util.ArrayList;
import java.util.List;

public class Page4_6_PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView[] imgCards;
    private CardProvider provider;
    private List<Card> cards;
    private List<Card> openned;
    private int clickCount, correctCount, level;
    private Animation fadeIn, fadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_4_6_play);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_4);
        getSupportActionBar().setElevation(0);

        Intent intent = getIntent();
        if(intent != null) {
            level = intent.getIntExtra(Page4_6Activity.PREF_KEY_LEVEL, 0);
            showMessage("ด่านที่ "+level);
        }

        //Load layout and setup bord size
        imgCards = new ImageView[12];
        initViews();

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        provider = new CardProvider(this);
        cards = provider.getCardLists(level);
        openned = new ArrayList<>();
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
        if(level <5) {
            Intent intent = new Intent(getApplicationContext(), Page4_6_PlayActivity.class);
            intent.putExtra(Page4_6Activity.PREF_KEY_LEVEL, level + 1);
            startActivity(intent);
            finish();
        }else {
            navigateUpTo(new Intent(this, Page4_6Activity.class));
        }

    }

    private void showMessage(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        for(int i=0; i<imgCards.length; i++) {
            int resId = getResources().getIdentifier("card_"+(i+1), "id", getPackageName());
            imgCards[i] = findViewById(resId);
            imgCards[i].setOnClickListener(this);
            imgCards[i].setTag(i);
        }
    }
}
