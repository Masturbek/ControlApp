package com.example.control.ui.home;

import android.util.Log;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.control.R;

import org.w3c.dom.Text;

public class BootViewModel extends ViewModel {

    private MutableLiveData<String> mTextState;
    private MutableLiveData<Integer> PcState;
    public BootViewModel() {
        mTextState = new MutableLiveData<>();
        PcState = new MutableLiveData<>();
        mTextState.setValue("Current PC state:");
        PcState.setValue(0);
    }

    public LiveData<String> getText() {
        return mTextState;
    }
    public LiveData<Integer> getPcState() {
        return PcState;
    }
    public void setPcState(Integer val) {  PcState.setValue(val);}
}