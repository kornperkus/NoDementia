package com.kornperkus.nodementia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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
        int id = 0;

        if (pageNumber == 11) {
            int relegion_id = getApplicationContext().getSharedPreferences(MainActivity.PREF_KEY_MAIN, 0).getInt(MainActivity.PREF_KEY_RELIGION, R.id.religion_thai);
            if (relegion_id == R.id.religion_islam) id = R.drawable.page9_11_islam;
            else id = R.drawable.page9_11_thai;
        } else {
            id = getResources().getIdentifier("page9_" + pageNumber, "drawable", getPackageName());
        }

        displayimg.setImageResource(id);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (pageNumber >= 12) {
            forwardImg.setVisibility(View.GONE);
        } else {
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
