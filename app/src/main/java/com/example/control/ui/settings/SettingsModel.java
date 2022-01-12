package com.example.control.ui.settings;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

public class SettingsModel extends BaseObservable {
    @Bindable
    public ObservableField<String > mac_address;
    public SettingsModel(){
        mac_address= new ObservableField<>("123");

    }
}
