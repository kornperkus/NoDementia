package com.kornperkus.nodementia.page8;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.R;

public class Page5Activity extends AppCompatActivity {

    private TextView title, headline, headline2, headline3, body, body2, body3;
    private ImageView backImg, forwardImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_5);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_5);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_5_title));
        headline.setText(getString(R.string.page8_5_headline));
        headline2.setText(getString(R.string.page8_5_headline2));
        headline3.setText(getString(R.string.page8_5_headline3));
        body.setText(getString(R.string.page8_5_body));
        body2.setText(getString(R.string.page8_5_body2));
        body3.setText(getString(R.string.page8_5_body3));

        forwardImg.setVisibility(View.GONE);
        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_5PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        headline2 = findViewById(R.id.page_headline2);
        headline3 = findViewById(R.id.page_headline3);
        body = findViewById(R.id.page_body);
        body2 = findViewById(R.id.page_body2);
        body3 = findViewById(R.id.page_body3);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
