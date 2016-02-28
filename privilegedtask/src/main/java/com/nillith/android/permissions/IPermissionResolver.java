package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public interface IPermissionResolver {
    <TParam> IPermissionSession<TParam> createSession(IPrivilegedTask<TParam> privilegedTask);
    <TParam> void execute(IPrivilegedTask<TParam> privilegedTask, TParam ... params);
    void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults);
}
