package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Page9Activity extends AppCompatActivity {

    private ImageView backImg, forwardImg;
    private boolean isOpen;
    private ImageView displayimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);

        //Setting actionbar

        bindView();

        Intent intent = getIntent();
        final int pageNumber = intent.getIntExtra("page", 1);
        int id = getResources().getIdentifier("page9_" + pageNumber, "drawable", getPackageName());
        displayimg.setImageResource(id);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (pageNumber >= 6) {
            forwardImg.setVisibility(View.GONE);
        }else {
            forwardImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Page9Activity.class);
                    intent.putExtra("page", pageNumber + 1);
                    startActivity(intent);
                    finish();
                }
            });
        }
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
