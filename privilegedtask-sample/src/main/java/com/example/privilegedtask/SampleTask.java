package com.example.privilegedtask;

import android.Manifest;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.nillith.android.permissions.PrivilegedTask;

/**
 * Created by Nil on 2015/12/26.
 */


public class SampleTask extends PrivilegedTask {
    private static final String TAG = "SampleTask";
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, };

    @Override
    public String[] getRequiredPermissions() {
        return REQUIRED_PERMISSIONS;
    }

    @Override
    @RequiresPermission(allOf = {Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, })
    public void onPermissionsAllowed() {
        Log.d(TAG, "onPermissionsAllowed ");
    }

    @Override
    public void onShowRationale(String permission) {
        Log.d(TAG, "onPermissionsDenied " + permission);

    }

    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {
        StringBuilder sb = new StringBuilder();
        for (String permission : deniedPermissions){
            sb.append(permission);
        }
        Log.d(TAG, "onPermissionsDenied " + sb.toString());
    }
}
