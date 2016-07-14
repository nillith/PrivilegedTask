package com.nillith.android.permissions;

interface PermissionResolver {
    boolean hasPermission(String permission);

    boolean shouldShowRequestPermissionRationale(String permission);

    void requestPermissions(String[] permissions, int requestCode);
}
