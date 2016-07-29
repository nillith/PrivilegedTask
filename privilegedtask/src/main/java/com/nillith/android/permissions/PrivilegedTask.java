package com.nillith.android.permissions;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public abstract class PrivilegedTask<TParam> {

    private static final SparseArray<PrivilegedTask> TASKS = new SparseArray<>();

    public static final String[] CALENDAR = {
            "android.permission.READ_CALENDAR",
            "android.permission.WRITE_CALENDAR",
    };

    public static final String[] CAMERA = {
            "android.permission.CAMERA",
    };

    public static final String[] CONTACTS = {
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.GET_ACCOUNTS",
    };

    public static final String[] LOCATION = {
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION",
    };

    public static final String[] MICROPHONE = {
            "android.permission.RECORD_AUDIO",
    };

    public static final String[] PHONE = {
            "android.permission.READ_PHONE_STATE",
            "android.permission.CALL_PHONE",
            "android.permission.READ_CALL_LOG",
            "android.permission.WRITE_CALL_LOG",
            "com.android.voicemail.permission.ADD_VOICEMAIL",
            "android.permission.USE_SIP",
            "android.permission.PROCESS_OUTGOING_CALLS",
    };

    public static final String[] SENSORS = {
            "android.permission.BODY_SENSORS",
    };

    public static final String[] SMS = {
            "android.permission.SEND_SMS",
            "android.permission.RECEIVE_SMS",
            "android.permission.READ_SMS",
            "android.permission.RECEIVE_WAP_PUSH",
            "android.permission.RECEIVE_MMS",
    };

    public static final String[] EXTERNAL_STORAGE = {
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

    @SuppressWarnings("unchecked")
    private void execute() {
        onPermissionsAllowed(params);
    }

    protected void onShowRationale(String permission) {

    }

    protected void onBeforeInitiation() {

    }

    public static void initiate(final Activity host, final String[] requiredPermissions, final Runnable onGranted, final Runnable onDenied) {
        initiate(PermissionResolvers.create(host), requiredPermissions, onGranted, onDenied);
    }

    public static void initiate(final Fragment host, final String[] requiredPermissions, final Runnable onGranted, final Runnable onDenied) {
        initiate(PermissionResolvers.create(host), requiredPermissions, onGranted, onDenied);
    }

    public static void initiate(final android.support.v4.app.Fragment host, final String[] requiredPermissions, final Runnable onGranted, final Runnable onDenied) {
        initiate(PermissionResolvers.create(host), requiredPermissions, onGranted, onDenied);
    }

    public static void initiate(final DialogFragment host, String[] requiredPermissions, final Runnable onGranted, final Runnable onDenied) {
        initiate(PermissionResolvers.create(host), requiredPermissions, onGranted, onDenied);
    }

    public static void initiate(final android.support.v4.app.DialogFragment host, final String[] requiredPermissions, final Runnable onGranted, final Runnable onDenied) {
        initiate(PermissionResolvers.create(host), requiredPermissions, onGranted, onDenied);
    }

    private static void initiate(PermissionResolver resolver, final String[] requiredPermissions,
                                 final Runnable onGranted, final Runnable onDenied) {
        PrivilegedTask<Void> task = new PrivilegedTask<Void>() {

            @Override
            protected String[] getRequiredPermissions() {
                return requiredPermissions;
            }

            @Override
            protected void onPermissionsAllowed(Void... voids) {
                onGranted.run();
            }

            @Override
            protected void onPermissionsDenied(String[] deniedPermissions) {
                if(null != onDenied){
                    onDenied.run();
                }
            }
        };
        task.initiate(resolver);
    }

    @SafeVarargs
    final public void initiate(final Activity host, TParam... params) {
        initiate(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void initiate(final Fragment host, TParam... params) {
        initiate(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void initiate(final android.support.v4.app.Fragment host, TParam... params) {
        initiate(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void initiate(final DialogFragment host, TParam... params) {
        initiate(PermissionResolvers.create(host), params);
    }

    @SafeVarargs
    final public void initiate(final android.support.v4.app.DialogFragment host, TParam... params) {
        initiate(PermissionResolvers.create(host), params);
    }

    @SuppressWarnings("unchecked")
    private void initiate(PermissionResolver resolver, TParam... params) {
        if (null != this.params) {
            throw new IllegalStateException();
        }
        onBeforeInitiation();
        this.params = params;
        List<String> pendingPermissions = new ArrayList<>();
        for (String permission : getRequiredPermissions()) {
            if (!resolver.hasPermission(permission)) {
                pendingPermissions.add(permission);
            }
        }
        if (pendingPermissions.isEmpty()) {
            try {
                execute();
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
            TASKS.put(requestCode, this);
            resolver.requestPermissions(toStringArray(pendingPermissions), requestCode);
        }
    }

    private static String[] toStringArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    private void clear() {
        params = null;
        TASKS.put(requestCode, null);
    }

    @SuppressWarnings("unchecked")
    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PrivilegedTask privilegedTask = TASKS.get(requestCode);
        if (null != privilegedTask) {
            try {
                List<String> deniedPermissions = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    if (PackageManager.PERMISSION_GRANTED != grantResults[i]) {
                        deniedPermissions.add(permissions[i]);
                    }
                }
                if (deniedPermissions.isEmpty()) {
                    privilegedTask.execute();
                } else {
                    privilegedTask.onPermissionsDenied(toStringArray(deniedPermissions));
                }
            } finally {
                privilegedTask.clear();
            }
        }
    }
}
