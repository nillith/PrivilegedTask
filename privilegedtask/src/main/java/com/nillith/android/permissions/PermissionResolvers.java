package com.nillith.android.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

class PermissionResolvers{

    private PermissionResolvers(){}

    public static PermissionResolver create(final Activity activity) {
        return new AbstractPermissionResolver() {

            @Override
            protected Context getContext() {
                return activity;
            }

            @Override
            public boolean shouldShowRequestPermissionRationale(String permission) {
                return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            }

            @Override
            public void requestPermissions(String[] permissions, int requestCode) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static PermissionResolver create(final Fragment fragment) {
        return new AbstractPermissionResolver() {


            @Override
            protected Context getContext() {
                return fragment.getContext();
            }

            @Override
            public boolean shouldShowRequestPermissionRationale(String permission) {
                return fragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            public void requestPermissions(String[] permissions, int requestCode) {
                fragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    public static PermissionResolver create(final android.support.v4.app.Fragment fragment) {
        return new AbstractPermissionResolver() {

            @Override
            protected Context getContext() {
                return fragment.getContext();
            }

            @Override
            public boolean shouldShowRequestPermissionRationale(String permission) {
                return fragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            public void requestPermissions(String[] permissions, int requestCode) {
                fragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static PermissionResolver create(final DialogFragment dialogFragment) {
        return new AbstractPermissionResolver() {


            @Override
            protected Context getContext() {
                return dialogFragment.getContext();
            }

            @Override
            public boolean shouldShowRequestPermissionRationale(String permission) {
                return dialogFragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            public void requestPermissions(String[] permissions, int requestCode) {
                dialogFragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    public static PermissionResolver create(final android.support.v4.app.DialogFragment dialogFragment) {
        return new AbstractPermissionResolver() {


            @Override
            protected Context getContext() {
                return dialogFragment.getContext();
            }

            @Override
            public boolean shouldShowRequestPermissionRationale(String permission) {
                return dialogFragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            public void requestPermissions(String[] permissions, int requestCode) {
                dialogFragment.requestPermissions(permissions, requestCode);
            }
        };
    }
}
