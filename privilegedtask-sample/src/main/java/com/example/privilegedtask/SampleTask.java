package com.example.privilegedtask;

import android.Manifest;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.nillith.android.permissions.PrivilegedTask;

/**
 * Created by Nil on 2015/12/26.
 */


public class SampleTask extends PrivilegedTask<String> {
    private static final String TAG = "SampleTask";
    private static final String[] REQUIRED_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, };

    @Override
    public String[] getRequiredPermissions() {
        return REQUIRED_PERMISSIONS;
    }



    @Override
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, })
    public void onPermissionsAllowed(String...params) {
        Log.d(TAG, "onPermissionsAllowed " + arrayToString(params));
    }

    @Override
    public void onShowRationale(String permission) {
        Log.d(TAG, "onPermissionsDenied " + permission);

    }

    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {

        Log.d(TAG, "onPermissionsDenied " + arrayToString(deniedPermissions));
    }

    private static<T> String arrayToString(T[] arr){
        StringBuilder sb = new StringBuilder();
        for (T t : arr){
            sb.append(t.toString());
        }
        return sb.toString();
    }
}
