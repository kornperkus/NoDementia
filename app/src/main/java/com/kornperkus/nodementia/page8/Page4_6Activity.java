package com.kornperkus.nodementia.page8;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.kornperkus.nodementia.R;

public class Page4_6Activity extends AppCompatActivity {

    public static final String PREF_KEY_LEVEL = "levelPrefKey";
    private TextView title, headline, body;
    private Spinner stageSpin;
    private Button playBtn;
    private ImageView backImg, forwardImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8_4_6);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page8_4);
        getSupportActionBar().setElevation(0);

        bindView();
        title.setText(getString(R.string.page8_4_title));
        headline.setText(getString(R.string.page8_4_6_headline));
        body.setText(getText(R.string.page8_4_6_body));

        final ArrayAdapter<CharSequence> stageList = ArrayAdapter.createFromResource(this, R.array.stage_array, android.R.layout.simple_spinner_item);
        stageList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stageSpin.setAdapter(stageList);

        playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(stageSpin.getSelectedItemPosition() > 0) {
                    Intent intent = new Intent(getApplicationContext(), Page4_6_PlayActivity.class);
                    intent.putExtra(PREF_KEY_LEVEL, stageSpin.getSelectedItemPosition());
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "โปรดเลือกด่านก่อนเล่น", Toast.LENGTH_SHORT).show();
                }
            }
        });

        forwardImg.setVisibility(View.GONE);
        backImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page7_4PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        body = findViewById(R.id.page_body);
        stageSpin = findViewById(R.id.stage_spin);
        playBtn = findViewById(R.id.play_btn);
        backImg = findViewById(R.id.backImg);
        forwardImg = findViewById(R.id.forwardImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}