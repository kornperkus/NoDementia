package com.kornperkus.nodementia;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.kornperkus.nodementia.App.CHANNEL_1_ID;

public class Test extends AppCompatActivity {
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    public void showNotification(View v) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_account)
                .setContentTitle("Test Title")
                .setContentText("Test Body")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();

        notificationManagerCompat.notify(1, notification);
    }
    public void showNotificationA(View v) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_account)
                .setContentTitle("Test Title2")
                .setContentText("Test Body2")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();

        notificationManagerCompat.notify(2, notification);
    }
}
