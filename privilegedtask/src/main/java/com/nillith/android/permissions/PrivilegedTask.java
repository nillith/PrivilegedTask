package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public abstract class PrivilegedTask<TParam> implements IPrivilegedTask<TParam> {


    @Override
    public void onPermissionsDenied(String[] deniedPermissions) {

    }

    @Override
    public void onShowRationale(String permission) {

    }
}
