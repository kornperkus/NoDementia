package com.kornperkus.nodementia;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Page10Activity extends AppCompatActivity {
    private ImageView backImg, forwardImg;
    private ImageView displayimg;
    private Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page10);

        //Setting actionbar
        bindView();
        Intent intent = getIntent();
        int status = intent.getIntExtra("test_status", 0);

        if(status == 1) {
            displayimg.setImageResource(R.drawable.post_test);
            goBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String urlString = "https://docs.google.com/forms/d/1d7Q21cKRfDksMilddG8YQFNVD7bnf7QnFvoy9vgoIsU/viewform?edit_requested=true";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            });

            forwardImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Page10Activity.class);
                    intent.putExtra("test_status", 2);
                    startActivity(intent);
                    finish();
                }
            });
        }else if(status == 2) {
            forwardImg.setVisibility(View.GONE);
            displayimg.setImageResource(R.drawable.rating);
            goBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String urlString = "https://docs.google.com/forms/d/17YkecA0ARqJl33xsvICtcQPHYuy_RTFYh3ZpXVeuUxc/edit";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            });
        }else {
            displayimg.setImageResource(R.drawable.pre_test);
            goBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String urlString = "https://docs.google.com/forms/d/18ALOTW-N0l7RTkoxRl6mXsuo7DvmIvDHb_B1zsJjAlY/viewform?edit_requested=true";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            });
            forwardImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Page9Activity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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
        goBtn = findViewById(R.id.goBtn);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
