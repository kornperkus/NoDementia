package com.kornperkus.nodementia;

import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Page5Activity extends AppCompatActivity {

    private TextView title, headline, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Setting actionbar
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.action_bar_page5);
        getSupportActionBar().setElevation(0);

        //bindView();

        /*Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String dayString;
        switch(day) {
            case 1:
                dayString = "อาทิตย์";
                break;
            case 2:
                dayString = "จันทร์";
                break;
            case 3:
                dayString = "อังคาร";
                break;
            case 4:
                dayString = "พุธ";
                break;
            case 5:
                dayString = "พฤหัส";
                break;
            default:
                dayString = "none";
        }
        title.setText(dayString);
        headline.setText("วัน"+ dayString +
                "วันที่" +calendar.get(Calendar.DAY_OF_MONTH)+
                "เดือน" +(calendar.get(Calendar.MONTH)+1)+
                "ปี"+(calendar.get(Calendar.YEAR)+543));
        body.setText(getString(R.string.page5_body));

        //set color
        FrameLayout frame = findViewById(R.id.frame);
        frame.setBackgroundColor(getResources().getColor(R.color.page5PrimaryDark));
    }

    private void bindView(){
        title = findViewById(R.id.page_title);
        headline = findViewById(R.id.page_headline);
        body = findViewById(R.id.page_body);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
