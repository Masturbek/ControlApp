package com.example.control.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.control.MainActivity;
import com.example.control.MainViewModel;
import com.example.control.R;
import com.example.control.databinding.FragmentSettingsBinding;
import com.example.control.ui.home.BootViewModel;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    private SettingsViewModel settingsViewModel;
    SharedPreferences sPref;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);
        binding.setSettingsViewModel(settingsViewModel);
        sPref = requireActivity().getSharedPreferences("netconfig",Context.MODE_PRIVATE);
        settingsViewModel.mac_address.set(sPref.getString("mac_address",""));
        settingsViewModel.ip_address.set(sPref.getString("ip_address",""));
        settingsViewModel.port.set(sPref.getString("port",""));
        /*binding.savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSave();
            }
        });*/

        View root = binding.getRoot();
        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
}
