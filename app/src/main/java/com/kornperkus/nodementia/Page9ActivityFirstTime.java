package com.kornperkus.nodementia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page9ActivityFirstTime extends AppCompatActivity {

    private ImageView backImg, forwardImg;
    private ImageView displayimg;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);

        //Setting actionbar

        bindView();
        Intent intent = getIntent();
        displayimg.setImageResource(R.drawable.page9_0);

        if (intent != null) {
            if (intent.getBooleanExtra("fromNav", false)) {
                forwardImg.setVisibility(View.GONE);
            } else {
                forwardImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Page10Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        player = MediaPlayer.create(getApplicationContext(), R.raw.welcome_sound);
        player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(player != null) player.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null) player.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) {
            if(player.isPlaying()) player.stop();
            player.release();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void bindView() {
        forwardImg = findViewById(R.id.forwardImg);
        backImg = findViewById(R.id.backImg);
        displayimg = findViewById(R.id.display_img);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
