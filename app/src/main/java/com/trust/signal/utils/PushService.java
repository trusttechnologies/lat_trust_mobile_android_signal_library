package com.trust.signal.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.trust.signal.R;

import java.util.Map;

public class PushService extends FirebaseMessagingService {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // If True, means that session is active, otherwise, app must not receive notifications
        Log.d(TAG, "onMessageReceived: ");

        Map<String, String> data = remoteMessage.getData();


        String datito = data.get("key");
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        String title;
        String body;
        if (notification != null) {
            title = notification.getTitle();
            body = datito; //notification.getBody()
        } else {
            title = "titulo";
            body = "body por defecto";
        }
        showNotification(title, body, remoteMessage);

        new GPS(this);
    }

    private void showNotification(String title, String content, RemoteMessage remoteMessage) {
        Log.d(TAG, "showNotification: ");

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(getPackageName(), "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setDescription("Testing");
            mChannel.enableLights(true);
            mChannel.enableVibration(true);
            mChannel.setShowBadge(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            nManager.createNotificationChannel(mChannel);
        }

        Log.d(TAG, "showNotification: HERE");

        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this, getPackageName());
        ncb
                .setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(true)
                .setOngoing(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText(content)
                .setVibrate(new long[]{300, 500, 300, 500});

        nManager.notify(0, ncb.build());
    }

}
