package com.example.control.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.control.BootWidgetService;
import com.example.control.MainViewModel;
import com.example.control.NET.Boot;
import com.example.control.R;
import com.example.control.databinding.BootWidgetBinding;
import com.example.control.databinding.FragmentBootBinding;
import com.example.control.NET.Net;

public class BootFragment extends Fragment {

    private BootViewModel bootViewModel;
    private MainViewModel mainViewModel;
    private FragmentBootBinding binding;
    private BootWidgetBinding bootWidgetBinding;
    private TextView tv1;
    public static final String REFRESH_MAIN = "REFRESH_MAIN";
    MyBroadcastReceiver receiver;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        bootViewModel = new ViewModelProvider(requireActivity()).get(BootViewModel.class);
        //mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding = FragmentBootBinding.inflate(inflater, container, false);
        //bootWidgetBinding = BootWidgetBinding.inflate(inflater, container, false);

         View view = inflater.inflate(R.layout.fragment_boot, container, false);
        binding.bootbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.BOOT)); }
        });
        binding.offbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.SHUTDOWN));  }
        });
        binding.sleepbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.SLEEP)); }
        });

        binding.restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.RESTART));  }
        });
        binding.refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {/*clickRefresh();*/getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.REFRESH)); }
        });
        tv1 = binding.text1;
        bootViewModel.getPcState().observe(getViewLifecycleOwner(), new Observer<Integer>()  {
            @Override
            public void onChanged(Integer integer) {
                PcState(integer);
            }
        });
        View root = binding.getRoot();
        /*final TextView textView = binding.text;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }

   /* private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Intent", "onReceive: "+intent.getIntExtra("pcstate",0));
        }
    };*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onResume() {
        super.onResume();
       //new ConnectionCheck().execute();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("REFRESH_MAIN");
        receiver = new MyBroadcastReceiver();
        getActivity().registerReceiver(receiver, intentFilter);
        getContext().startForegroundService(new Intent(getContext(), BootWidgetService.class).setAction(BootWidgetService.REFRESH_onRESUME));
    }
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            /*Bundle extras = intent.getExtras();
            String state = extras.getString("pcstate");*/
            bootViewModel.setPcState(intent.getIntExtra("pcstate",0));
            Log.d("int", "onReceive: "+  intent.getIntExtra("pcstate",0));// update your textView in the main layout
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    public void clickRefresh(){
        new ConnectionCheck().execute();
    }
    public void PcState(int state) {

        switch (state) {
            case 0:{
                tv1.setText("Off");
                tv1.setTextColor(getResources().getColor(R.color.off));
            }
            break;
            case 1:{
                tv1.setText("On");
                tv1.setTextColor(getResources().getColor(R.color.on));
            }
            break;
        }
    }
    class ConnectionCheck extends AsyncTask<Void, Void, Integer> {
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
            bootViewModel.setPcState(result);
        }
    }
    class BootPC extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
             Boot.PCBoot();
             return null;
        }
        @Override
        protected void onPostExecute(Void result) {
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