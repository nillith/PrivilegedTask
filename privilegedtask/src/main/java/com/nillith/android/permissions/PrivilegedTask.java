package com.nillith.android.permissions;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public abstract class PrivilegedTask<TParam> {
    protected static final String[] CALENDAR = {
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
    };

    protected static final String[] CAMERA = {
            "android.permission.CAMERA",
    };

    protected static final String[] CONTACTS = {
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.GET_ACCOUNTS",
    };

    protected static final String[] LOCATION = {
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
    };

    protected static final String[] MICROPHONE = {
            "android.permission.RECORD_AUDIO",
    };

    protected static final String[] PHONE = {
            "android.permission.READ_PHONE_STATE",
            "android.permission.CALL_PHONE",
            "android.permission.READ_CALL_LOG",
            "android.permission.WRITE_CALL_LOG",
            "com.android.voicemail.permission.ADD_VOICEMAIL",
            "android.permission.USE_SIP",
            "android.permission.PROCESS_OUTGOING_CALLS",
    };

    protected static final String[] SENSORS = {
            "android.permission.BODY_SENSORS",
    };

    protected static final String[] SMS = {
            "android.permission.SEND_SMS",
            "android.permission.RECEIVE_SMS",
            "android.permission.READ_SMS",
            "android.permission.RECEIVE_WAP_PUSH",
            "android.permission.RECEIVE_MMS",
    };

    protected static final String[] EXTERNAL_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
    };

    private TParam[] params;
    private int requestCode;

    abstract protected String[] getRequiredPermissions();

    @SuppressWarnings("unchecked")
    abstract protected void onPermissionsAllowed(TParam... params);

    protected void onPermissionsDenied(String[] deniedPermissions) {

    }

    protected void onShowRationale(String permission) {

    }

    protected void onBeforeStart() {

    }

    @SafeVarargs
    final public void start(final Activity host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void start(final Fragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void start(final android.support.v4.app.Fragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void start(final DialogFragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void start(final android.support.v4.app.DialogFragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    @SuppressWarnings("unchecked")
    private void start(PermissionResolver resolver, TParam... params) {
        if(null != this.params){
            throw new IllegalStateException();
        }
        onBeforeStart();
        this.params = params;
        List<String> pendingPermissions = new ArrayList<>();
        for (String permission : getRequiredPermissions()) {
            if (!resolver.hasPermission(permission)) {
                pendingPermissions.add(permission);
            }
        }
        if (pendingPermissions.isEmpty()) {
            try {
                onPermissionsAllowed(params);
            } finally {
                clear();
            }
        } else {
            for (String permission : pendingPermissions) {
                if (resolver.shouldShowRequestPermissionRationale(permission)) {
                    onShowRationale(permission);
                }
            }
            requestCode = Sequencer.next();
            resolver.requestPermissions(toStringArray(pendingPermissions), requestCode);
        }

    }

    private String[] toStringArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    private void clear() {
        this.params = null;
    }

    @SuppressWarnings("unchecked")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.requestCode == requestCode) {
            try {
                List<String> deniedPermissions = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (PackageManager.PERMISSION_GRANTED != grantResults[i]) {
                        deniedPermissions.add(permissions[i]);
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    onPermissionsAllowed(params);
                } else {
                    onPermissionsDenied(toStringArray(deniedPermissions));
                }
            } finally {
                clear();
            }
        }
    }
}
