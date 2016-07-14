package com.example.privilegedtask;

//import android.Manifest;
import android.Manifest;
import android.support.annotation.RequiresPermission;
import android.util.Log;

import com.nillith.android.permissions.PrivilegedTask;

public class DemoTask extends PrivilegedTask<String> {
    private static final String TAG = "DemoTask";

    @Override
    public String[] getRequiredPermissions() {
        return EXTERNAL_STORAGE;
    }



    @Override
    @RequiresPermission(allOf = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, })
    public void onPermissionsAllowed(String...params) {
        Log.d(TAG, hashCode()+ ":onPermissionsAllowed " + arrayToString(params));
    }

    @Override
    public void onShowRationale(String permission) {
        Log.d(TAG, hashCode()+ ":onShowRationale " + permission);

    }

    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {

        Log.d(TAG, hashCode()+ ":onPermissionsDenied " + arrayToString(deniedPermissions));
    }

    private static<T> String arrayToString(T[] arr){
        StringBuilder sb = new StringBuilder();
        for (T t : arr){
            sb.append(t.toString());
        }
        return sb.toString();
    }
}
