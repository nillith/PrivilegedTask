package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
interface IPermissionResolver {
    boolean hasPermission(String permission);

    boolean shouldShowRequestPermissionRationale(String permission);

    void requestPermissions(String[] permissions, int requestCode);
}
