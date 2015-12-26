package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public abstract class PrivilegedTask implements IPrivilegedTask {


    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {

    }

    @Override
    public void onShowRationale(String permission) {

    }
}
