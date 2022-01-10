package com.example.control.databinding;
import com.example.control.R;
import com.example.control.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentSettingsBindingImpl extends FragmentSettingsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.savebutton, 4);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener ipadresstexteditandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of settingsViewModel.ip_address.get()
            //         is settingsViewModel.ip_address.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(ipadresstextedit);
            // localize variables for thread safety
            // settingsViewModel.ip_address.get()
            java.lang.String settingsViewModelIpAddressGet = null;
            // settingsViewModel.ip_address
            androidx.databinding.ObservableField<java.lang.String> settingsViewModelIpAddress = null;
            // settingsViewModel != null
            boolean settingsViewModelJavaLangObjectNull = false;
            // settingsViewModel.ip_address != null
            boolean settingsViewModelIpAddressJavaLangObjectNull = false;
            // settingsViewModel
            com.example.control.ui.settings.SettingsViewModel settingsViewModel = mSettingsViewModel;



            settingsViewModelJavaLangObjectNull = (settingsViewModel) != (null);
            if (settingsViewModelJavaLangObjectNull) {


                settingsViewModelIpAddress = settingsViewModel.ip_address;

                settingsViewModelIpAddressJavaLangObjectNull = (settingsViewModelIpAddress) != (null);
                if (settingsViewModelIpAddressJavaLangObjectNull) {




                    settingsViewModelIpAddress.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener macadresstexteditandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of settingsViewModel.mac_address.get()
            //         is settingsViewModel.mac_address.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(macadresstextedit);
            // localize variables for thread safety
            // settingsViewModel != null
            boolean settingsViewModelJavaLangObjectNull = false;
            // settingsViewModel.mac_address != null
            boolean settingsViewModelMacAddressJavaLangObjectNull = false;
            // settingsViewModel
            com.example.control.ui.settings.SettingsViewModel settingsViewModel = mSettingsViewModel;
            // settingsViewModel.mac_address.get()
            java.lang.String settingsViewModelMacAddressGet = null;
            // settingsViewModel.mac_address
            androidx.databinding.ObservableField<java.lang.String> settingsViewModelMacAddress = null;



            settingsViewModelJavaLangObjectNull = (settingsViewModel) != (null);
            if (settingsViewModelJavaLangObjectNull) {


                settingsViewModelMacAddress = settingsViewModel.mac_address;

                settingsViewModelMacAddressJavaLangObjectNull = (settingsViewModelMacAddress) != (null);
                if (settingsViewModelMacAddressJavaLangObjectNull) {




                    settingsViewModelMacAddress.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };
    private androidx.databinding.InverseBindingListener porttexteditandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of settingsViewModel.port.get()
            //         is settingsViewModel.port.set((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(porttextedit);
            // localize variables for thread safety
            // settingsViewModel != null
            boolean settingsViewModelJavaLangObjectNull = false;
            // settingsViewModel.port != null
            boolean settingsViewModelPortJavaLangObjectNull = false;
            // settingsViewModel.port
            androidx.databinding.ObservableField<java.lang.String> settingsViewModelPort = null;
            // settingsViewModel
            com.example.control.ui.settings.SettingsViewModel settingsViewModel = mSettingsViewModel;
            // settingsViewModel.port.get()
            java.lang.String settingsViewModelPortGet = null;



            settingsViewModelJavaLangObjectNull = (settingsViewModel) != (null);
            if (settingsViewModelJavaLangObjectNull) {


                settingsViewModelPort = settingsViewModel.port;

                settingsViewModelPortJavaLangObjectNull = (settingsViewModelPort) != (null);
                if (settingsViewModelPortJavaLangObjectNull) {




                    settingsViewModelPort.set(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public FragmentSettingsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private FragmentSettingsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 3
            , (android.widget.EditText) bindings[2]
            , (android.widget.EditText) bindings[1]
            , (android.widget.EditText) bindings[3]
            , (android.widget.Button) bindings[4]
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            );
        this.ipadresstextedit.setTag(null);
        this.macadresstextedit.setTag(null);
        this.porttextedit.setTag(null);
        this.settingsLay.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x10L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.settingsViewModel == variableId) {
            setSettingsViewModel((com.example.control.ui.settings.SettingsViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSettingsViewModel(@Nullable com.example.control.ui.settings.SettingsViewModel SettingsViewModel) {
        this.mSettingsViewModel = SettingsViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.settingsViewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeSettingsViewModelIpAddress((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeSettingsViewModelPort((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
            case 2 :
                return onChangeSettingsViewModelMacAddress((androidx.databinding.ObservableField<java.lang.String>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeSettingsViewModelIpAddress(androidx.databinding.ObservableField<java.lang.String> SettingsViewModelIpAddress, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSettingsViewModelPort(androidx.databinding.ObservableField<java.lang.String> SettingsViewModelPort, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeSettingsViewModelMacAddress(androidx.databinding.ObservableField<java.lang.String> SettingsViewModelMacAddress, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.lang.String settingsViewModelIpAddressGet = null;
        com.example.control.ui.settings.SettingsViewModel settingsViewModel = mSettingsViewModel;
        androidx.databinding.ObservableField<java.lang.String> settingsViewModelIpAddress = null;
        androidx.databinding.ObservableField<java.lang.String> settingsViewModelPort = null;
        java.lang.String settingsViewModelPortGet = null;
        java.lang.String settingsViewModelMacAddressGet = null;
        androidx.databinding.ObservableField<java.lang.String> settingsViewModelMacAddress = null;

        if ((dirtyFlags & 0x1fL) != 0) {


            if ((dirtyFlags & 0x19L) != 0) {

                    if (settingsViewModel != null) {
                        // read settingsViewModel.ip_address
                        settingsViewModelIpAddress = settingsViewModel.ip_address;
                    }
                    updateRegistration(0, settingsViewModelIpAddress);


                    if (settingsViewModelIpAddress != null) {
                        // read settingsViewModel.ip_address.get()
                        settingsViewModelIpAddressGet = settingsViewModelIpAddress.get();
                    }
            }
            if ((dirtyFlags & 0x1aL) != 0) {

                    if (settingsViewModel != null) {
                        // read settingsViewModel.port
                        settingsViewModelPort = settingsViewModel.port;
                    }
                    updateRegistration(1, settingsViewModelPort);


                    if (settingsViewModelPort != null) {
                        // read settingsViewModel.port.get()
                        settingsViewModelPortGet = settingsViewModelPort.get();
                    }
            }
            if ((dirtyFlags & 0x1cL) != 0) {

                    if (settingsViewModel != null) {
                        // read settingsViewModel.mac_address
                        settingsViewModelMacAddress = settingsViewModel.mac_address;
                    }
                    updateRegistration(2, settingsViewModelMacAddress);


                    if (settingsViewModelMacAddress != null) {
                        // read settingsViewModel.mac_address.get()
                        settingsViewModelMacAddressGet = settingsViewModelMacAddress.get();
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x19L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.ipadresstextedit, settingsViewModelIpAddressGet);
        }
        if ((dirtyFlags & 0x10L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.ipadresstextedit, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, ipadresstexteditandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.macadresstextedit, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, macadresstexteditandroidTextAttrChanged);
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.porttextedit, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, porttexteditandroidTextAttrChanged);
        }
        if ((dirtyFlags & 0x1cL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.macadresstextedit, settingsViewModelMacAddressGet);
        }
        if ((dirtyFlags & 0x1aL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.porttextedit, settingsViewModelPortGet);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): settingsViewModel.ip_address
        flag 1 (0x2L): settingsViewModel.port
        flag 2 (0x3L): settingsViewModel.mac_address
        flag 3 (0x4L): settingsViewModel
        flag 4 (0x5L): null
    flag mapping end*/
    //end
}