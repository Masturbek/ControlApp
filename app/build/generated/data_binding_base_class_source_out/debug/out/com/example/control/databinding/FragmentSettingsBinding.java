// Generated by data binding compiler. Do not edit!
package com.example.control.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.control.R;
import com.example.control.ui.settings.SettingsViewModel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentSettingsBinding extends ViewDataBinding {
  @NonNull
  public final EditText ipadresstextedit;

  @NonNull
  public final EditText macadresstextedit;

  @NonNull
  public final EditText porttextedit;

  @NonNull
  public final Button savebutton;

  @NonNull
  public final CoordinatorLayout settingsLay;

  @Bindable
  protected SettingsViewModel mSettingsViewModel;

  protected FragmentSettingsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      EditText ipadresstextedit, EditText macadresstextedit, EditText porttextedit,
      Button savebutton, CoordinatorLayout settingsLay) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ipadresstextedit = ipadresstextedit;
    this.macadresstextedit = macadresstextedit;
    this.porttextedit = porttextedit;
    this.savebutton = savebutton;
    this.settingsLay = settingsLay;
  }

  public abstract void setSettingsViewModel(@Nullable SettingsViewModel settingsViewModel);

  @Nullable
  public SettingsViewModel getSettingsViewModel() {
    return mSettingsViewModel;
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_settings, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSettingsBinding>inflateInternal(inflater, R.layout.fragment_settings, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_settings, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSettingsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSettingsBinding>inflateInternal(inflater, R.layout.fragment_settings, null, false, component);
  }

  public static FragmentSettingsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static FragmentSettingsBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentSettingsBinding)bind(component, view, R.layout.fragment_settings);
  }
}
