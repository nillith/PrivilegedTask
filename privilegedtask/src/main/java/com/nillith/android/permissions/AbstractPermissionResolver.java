package com.nillith.android.permissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

abstract class AbstractPermissionResolver implements PermissionResolver {

    public boolean hasPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(getContext(), permission);
    }
    abstract protected Context getContext();
}
