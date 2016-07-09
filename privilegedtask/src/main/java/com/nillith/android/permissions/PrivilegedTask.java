package com.nillith.android.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nil on 2015/12/26.
 */
public abstract class PrivilegedTask<TParam> implements IPrivilegedTask<TParam> {

    TParam[] params;
    final int id = Sequencer.next();

    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {

    }

    @Override
    public void onShowRationale(String permission) {

    }


    public void start(final Activity host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    public void start(final Fragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    public void start(final android.support.v4.app.Fragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    public void start(final DialogFragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }

    public void start(final android.support.v4.app.DialogFragment host, TParam... params) {
        start(PermissionResolvers.create(host), params);
    }


    private void start(IPermissionResolver resolver, TParam... params) {
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
            } catch (Exception e) {
                throw e;
            } finally {
                clear();
            }
        } else {
            for (String permission : pendingPermissions) {
                if (resolver.shouldShowRequestPermissionRationale(permission)) {
                    onShowRationale(permission);
                }
            }
            resolver.requestPermissions(toStringArray(pendingPermissions), id);
        }

    }

    private String[] toStringArray(List<String> list){
        return list.toArray(new String[list.size()]);
    }

    private void clear() {
        this.params = null;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (id == requestCode) {
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
            } catch (Exception e) {
                throw e;
            } finally {
                clear();
            }
        }
    }
}
