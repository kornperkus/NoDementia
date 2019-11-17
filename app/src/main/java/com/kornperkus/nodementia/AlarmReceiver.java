package com.kornperkus.nodementia;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.kornperkus.nodementia.App.CHANNEL_1_ID;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("เตือนความจำ")
                .setContentText(intent.getStringExtra("title"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();

        notificationManagerCompat.notify(intent.getIntExtra("id", 1), notification);
    }
}
