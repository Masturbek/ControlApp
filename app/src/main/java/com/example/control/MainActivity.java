package com.example.control;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.control.databinding.ActivityMainBinding;
import com.example.control.NET.Net;
import com.example.control.ui.home.BootViewModel;
import com.example.control.ui.settings.SettingsViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
private ActivityMainBinding binding;
    private BootViewModel bootViewModel;
    private MainViewModel mainViewModel;
    private SettingsViewModel settingsViewModel;
    public Net NET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        bootViewModel = new ViewModelProvider(this).get(BootViewModel.class);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        new StartNET().execute();
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_boot, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View layout = findViewById(R.id.drawer_layout);
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                SwipeLeft();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                SwipeRigth();
            }
        });
        //bootViewModel.setPcState(0);

        //ContextCompat.startForegroundService(new Intent(this, BootWidgetService.class));

        //startForegroundService(new Intent(this, BootWidgetService.class));

        //Intent myIntent = new Intent(this, BootWidgetService.class);

        // Call startService with Intent parameter.
        //startService(myIntent);

       // new Connect().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.settings_menu:
                Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.action_nav_boot_to_nav_settings2);
                return true;
            default:
                break;
        }

        return false;
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }

    public void  SwipeLeft(){

    }
    public void  SwipeRigth(){
        //onSupportNavigateUp();
    }

    public void clickOn(View view) {
        //DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
        //navDrawer.openDrawer(Gravity.START);
        //mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        //mySwipeRefreshLayout.setRefreshing(false);
        PcState(1);
    }
    public void clickOff(View view) {
        PcState(0);
    }

    public void PcState(int state) {
        TextView tv = findViewById(R.id.text1);
        switch (state) {
            case 0:{
                tv.setText("Off");
                tv.setTextColor(getResources().getColor(R.color.off));
            }
            break;
            case 1:{
                tv.setText("On");
                tv.setTextColor(getResources().getColor(R.color.on));
            }
            break;
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    public void clickSave(View view) {
        /*EditText mac_edit = findViewById(R.id.macadresstextedit);
        EditText ip_edit = findViewById(R.id.ipadresstextedit);
        EditText port_edit = findViewById(R.id.porttextedit);*/
        SharedPreferences sPref = getSharedPreferences("netconfig",0);
        SharedPreferences.Editor ed = sPref.edit();

        ed.putString("mac_address", settingsViewModel.mac_address.get());
        ed.putString("ip_address", settingsViewModel.ip_address.get());
        ed.putString("port",settingsViewModel.port.get());
        ed.commit();
        Toast.makeText(this, "Saved ", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(this,R.id.nav_host_fragment_content_main).popBackStack();
        new StartNET().execute();
    }


    public class StartNET extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            SharedPreferences sPref = getSharedPreferences("netconfig",MODE_PRIVATE);
            NET = new Net(sPref.getString("mac_address", ""),sPref.getString("ip_address", ""),sPref.getString("port", ""));
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
    /*public void StartNET(){
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        NET = new Net(sPref.getString("mac_address", ""),sPref.getString("ip_address", ""),sPref.getString("port", ""));
    }*/
}