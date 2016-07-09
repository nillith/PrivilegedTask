package com.nillith.android.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by Nil on 2015/12/26.
 */
abstract class AbstractPermissionResolver implements IPermissionResolver {

    public boolean hasPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getContext(), permission);
    }
    abstract protected Context getContext();
}
