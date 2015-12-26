package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public interface IPermissionResolver {
    IPermissionSession createSession(IPrivilegedTask privilegedTask);
    void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults);
}
