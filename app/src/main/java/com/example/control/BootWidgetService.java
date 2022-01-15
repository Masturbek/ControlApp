package com.example.control;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.util.ULocale;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.control.NET.Boot;
import com.example.control.NET.Net;

import java.util.Locale;
import java.util.MissingFormatArgumentException;

public class BootWidgetService extends Service {
    public static final String REFRESH = "REFRESH";
    public static final String BOOT = "BOOT";
    public static final String SLEEP = "SLEEP";
    public static final String SHUTDOWN = "SHUTDOWN";
    public static final String RESTART = "RESTART";
    SharedPreferences sPref;
    Net NET;
    Resources res;
    AppWidgetManager apm;
    Integer apId;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String CHANNEL_ID = "my_channel_01";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "Boot Service",
                NotificationManager.IMPORTANCE_DEFAULT);

        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build();

        startForeground(1, notification);

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));

        sPref = getApplicationContext().getSharedPreferences("netconfig",0);;
        //Net NET = new Net(sPref.getString("mac_address",""),sPref.getString("ip_address",""),sPref.getString("port",""));
        res = getResources();
        new StartNET().execute();
        apm = AppWidgetManager.getInstance(this);

        // Reaches the view on widget and displays the number
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.boot_widget);

        apId = views.getLayoutId();
        //onTaskRemoved(intent);

        if(intent.getAction()!=null) {
            switch (intent.getAction()){
                case REFRESH : { new ConnectCheck().execute();}
                break;
                case BOOT : { new BootPC().execute();}
                break;
                case SLEEP : { new ControlCommand().execute(Boot.Sleep);}
                break;
                case SHUTDOWN : { new ControlCommand().execute(Boot.Shutdown);}
                break;
                case RESTART : { new ControlCommand().execute(Boot.Restart);}
                break;
            }
        }
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
        //return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(),this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        startService(restartServiceIntent);
        super.onTaskRemoved(rootIntent);
    }
    public class StartNET extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {

            NET = new Net(sPref.getString("mac_address",""),sPref.getString("ip_address",""),sPref.getString("port",""));
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
    class ConnectCheck extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Refreshing...", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return NET.boot.CheckConnection();
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            PcState(result);
            switch (result){
                case 0 : Toast.makeText(getApplicationContext(), "PC is off", Toast.LENGTH_SHORT).show();
                break;
                case 1 : Toast.makeText(getApplicationContext(), "PC is on", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
    public void PcState(int state) {
        RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(), R.layout.boot_widget);
        Intent intent = new Intent(this, BootWidget.class);
        switch (state) {
            case 0:{
                views.setTextViewText(R.id.widgetpcstate,"off");
                views.setTextColor(R.id.widgetpcstate,res.getColor(R.color.off));
                intent.setAction("off");
            }
            break;
            case 1:{
                views.setTextViewText(R.id.widgetpcstate,"on");
                views.setTextColor(R.id.widgetpcstate,res.getColor(R.color.on));
                intent.setAction("on");
            }
            break;
        }
       int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), BootWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
        UpdateWidget();
    }
    void UpdateWidget(){
        Intent intent = new Intent(this, BootWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), BootWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }
    class BootPC extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            return  NET.boot.PCBoot();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
    class ControlCommand extends AsyncTask<Integer, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Integer... params) {
            return NET.boot.Command(params[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
