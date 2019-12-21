package com.nibalaws.ebrahim.law.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.nibalaws.ebrahim.law.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getData().toString());

        if (remoteMessage == null)
            return;

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            /* Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());*/
            Log.e(TAG, "Data Payload: " + remoteMessage);

            try {

                Map<String, String> params =  remoteMessage.getData();
                JSONObject json = new JSONObject( params);
                Log.e("JSON_OBJECT", json.toString());


                Log.e(TAG, "onMessageReceived: " + json.toString());

                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }


    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }else{
            // If the app is in background, firebase itself handles the notification
        }
    }
    private void sendNotification( String title, String message) {
        int notifyID = 0;
        try {
            notifyID = Integer.parseInt("0");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String CHANNEL_ID = "my_channel_01";            // The id of the channel.
        Intent intent = new Intent(this, Show_massege.class);
        Show_massege.txtmssage = message ;
        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "01")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher)

                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(pendingIntent);




        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {       // For Oreo and greater than it, we required Notification Channel.
            CharSequence name = "My New Channel";                   // The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name, importance); //Create Notification Channel
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(notifyID /* ID of notification */, notificationBuilder.build());
    }



    private void handleDataMessage(JSONObject json) {
    //    Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject jsonObject = new JSONObject(json.toString());

            String title = jsonObject.getString("title");
            String message = jsonObject.getString("message");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);


            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                sendNotification(title,message);
                // app is in foreground, broadcast the push message
              //  sendNotification(title,message);
            } else {
                // app is in background, show the notification in notification tray
                sendNotification(title,message);

            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}