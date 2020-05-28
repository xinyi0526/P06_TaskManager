package com.myapplicationdev.android.p06_taskmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver  {

    int reqCode = 12345;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("default","Default Channel",NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is default notofication");
            notificationManager.createNotificationChannel(channel);

        }
        Intent i = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,reqCode,i,PendingIntent.FLAG_CANCEL_CURRENT);
        String text = intent.getStringExtra("name");

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.sentosa)).build();



        //Build Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"default");
        builder.setContentTitle("Task Manager Reminder");
        builder.setContentText(text);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setStyle(bigPictureStyle);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Uri uri= RingtoneManager.getDefaultUri
                (RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        builder.setPriority(Notification.PRIORITY_HIGH);


        Notification n = builder.build();

        notificationManager.notify(123,n);

    }
}
