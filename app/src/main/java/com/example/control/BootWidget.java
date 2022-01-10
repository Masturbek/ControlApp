package com.example.control;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.SharedPreferences.Editor;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.control.NET.Boot;
import com.example.control.NET.Net;

/**
 * Implementation of App Widget functionality.
 */
public class BootWidget extends AppWidgetProvider {
   // public static String APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
    public final static String BUTTON_BOOT = "BUTTON_BOOT";
    public final static String BUTTON_OFF = "BUTTON_OFF";
    public final static String BUTTON_SLEEP = "BUTTON_SLEEP";
    public final static String BUTTON_RESTART = "BUTTON_RESTART";
    public final static String BUTTON_REFRESH = "BUTTON_REFRESH";
    static Net NET;
    static SharedPreferences sPref;
    static  RemoteViews views;

    static AppWidgetManager apm;
    static Integer wid;
    static Resources res;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        apm = appWidgetManager;
        wid = appWidgetId;

        views = new RemoteViews(context.getPackageName(), R.layout.boot_widget);
        sPref = context.getSharedPreferences("netconfig",Context.MODE_PRIVATE);
        new StartNET().execute();
        //views.setTextViewText(R.id.widgetpcstate,"GG");

        Intent Intent = new Intent(context, BootWidget.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, appWidgetId, Intent, 0);
        res = context.getResources();

        Intent.setAction(BUTTON_BOOT);
        pIntent = PendingIntent.getBroadcast(context, appWidgetId, Intent, 0);
        views.setOnClickPendingIntent(R.id.widgetbootbutton, pIntent);

        Intent.setAction(BUTTON_OFF);
        pIntent = PendingIntent.getBroadcast(context, appWidgetId, Intent, 0);
        views.setOnClickPendingIntent(R.id.widgetoffbutton, pIntent);

        Intent.setAction(BUTTON_SLEEP);
        pIntent = PendingIntent.getBroadcast(context, appWidgetId, Intent, 0);
        views.setOnClickPendingIntent(R.id.widgetsleepbutton, pIntent);

        Intent.setAction(BUTTON_RESTART);
        pIntent = PendingIntent.getBroadcast(context, appWidgetId, Intent, 0);
        views.setOnClickPendingIntent(R.id.widgetrestartbutton, pIntent);

        Intent.setAction(BUTTON_REFRESH);
        pIntent = PendingIntent.getBroadcast(context, appWidgetId, Intent, 0);
        views.setOnClickPendingIntent(R.id.widgetrefreshbutton, pIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
       /* if (BUTTON_BOOT.equals(intent.getAction())) {

        }*/

        switch (intent.getAction()){
            case BUTTON_BOOT:{new BootPC().execute();}
            break;
            case BUTTON_OFF:{new ControlCommand().execute(Boot.Shutdown);}
            break;
            case BUTTON_SLEEP:{new ControlCommand().execute(Boot.Sleep);}
            break;
            case BUTTON_RESTART:{new ControlCommand().execute(Boot.Restart);}
            break;
            case BUTTON_REFRESH:{new ConnectCheck().execute();}
            break;
        }
        //Net.boot.dothis();
        Log.d("widget", "onReceive: "+intent.getAction());
        Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show();

    }
    public static class StartNET extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {

            //NET = new Net(sPref.getString("mac_address", ""),sPref.getString("ip_address", ""),sPref.getString("port", ""));
            NET = new Net(sPref.getString("mac_address",""),sPref.getString("ip_address",""),sPref.getString("port",""));
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        // TODO IntentService
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public void PcState(int state) {
       // RemoteViews views = new RemoteViews(Context.getPackageName(), R.layout.boot_widget);
        switch (state) {
            case 0:{
                views.setTextViewText(R.id.widgetpcstate,"off");
                views.setTextColor(R.id.widgetpcstate,res.getColor(R.color.off));
            }
            break;
            case 1:{
                views.setTextViewText(R.id.widgetpcstate,"on");
                views.setTextColor(R.id.widgetpcstate,res.getColor(R.color.on));
            }
            break;
            /*Context context = BootWidget.class;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.boot_widget);
            ComponentName thisWidget = new ComponentName(context, BootWidget.class);*/

        }
        apm.updateAppWidget(wid, views);
    }
    class ConnectCheck extends AsyncTask<Void, Void, Integer> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(Void... params) {
            return Boot.CheckConnection();
        }
        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            PcState(result);
        }
    }
    class BootPC extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            return Boot.PCBoot();
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
    class ControlCommand extends AsyncTask<Integer, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Integer... params) {
            Boot.Command(params[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}