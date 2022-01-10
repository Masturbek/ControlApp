package com.example.control;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.control.NET.Net;

import java.util.MissingFormatArgumentException;

public class BootWidgetService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        // Reaches the view on widget and displays the number
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.boot_widget);



        if(intent.getAction()!=null)
        if(intent.getAction().equalsIgnoreCase("android.appwidget.action.APPWIDGET_UPDATE")){
            // do your stuff here
            //Net.boot.dothis();
        }
        Log.d("Start", "onStartCommand: ");
        Toast.makeText(getApplicationContext(), "Clicked!", Toast.LENGTH_SHORT).show();


        return super.onStartCommand(intent, flags, startId);
        //return START_FLAG_REDELIVERY;
    }

}
