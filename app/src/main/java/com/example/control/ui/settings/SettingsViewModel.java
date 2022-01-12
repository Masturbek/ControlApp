package com.example.control.ui.settings;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kotlin.properties.ObservableProperty;

public class SettingsViewModel extends ViewModel {
    public ObservableField<String> mac_address;
    public ObservableField<String> ip_address;
    public ObservableField<String> port;

    public SettingsViewModel(){
        mac_address = new ObservableField<>();
        ip_address = new ObservableField<>();
        port = new ObservableField<>();
    }

}
