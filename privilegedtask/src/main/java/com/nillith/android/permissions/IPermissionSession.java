package com.nillith.android.permissions;

/**
 * Created by Nil on 2015/12/26.
 */
public interface IPermissionSession<TParam> {
    void start(TParam... params);
}
