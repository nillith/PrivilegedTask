package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public interface IPrivilegedTask {
    String[] getRequiredPermissions();
    void onPermissionsAllowed();
    void onPermissionsDenied(String[] deniedPermissions);
    void onShowRationale(String permission);
}
