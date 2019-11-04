package com.kornperkus.nodementia;

import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Page5Activity extends AppCompatActivity {

    private TextView title, headline, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page5_title));
        headline.setText(getString(R.string.page5_headline));
        body.setText(getString(R.string.page5_body));

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        body = findViewById(R.id.page_body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
