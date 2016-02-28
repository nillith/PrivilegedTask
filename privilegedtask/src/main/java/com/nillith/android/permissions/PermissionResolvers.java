package com.nillith.android.permissions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Nil on 2015/12/30.
 */
public class PermissionResolvers{

    private PermissionResolvers(){}

    public static IPermissionResolver create(final Activity activity) {
        return new PermissionResolverImpl() {


            @Override
            protected Context getContext() {
                return activity;
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
                return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static IPermissionResolver create(final Fragment fragment) {
        return new PermissionResolverImpl() {


            @Override
            protected Context getContext() {
                return fragment.getContext();
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
                return fragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                fragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    public static IPermissionResolver create(final android.support.v4.app.Fragment fragment) {
        return new PermissionResolverImpl() {


            @Override
            protected Context getContext() {
                return fragment.getContext();
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
                return fragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                fragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static IPermissionResolver create(final DialogFragment dialogFragment) {
        return new PermissionResolverImpl() {


            @Override
            protected Context getContext() {
                return dialogFragment.getContext();
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
                return dialogFragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                dialogFragment.requestPermissions(permissions, requestCode);
            }
        };
    }

    public static IPermissionResolver create(final android.support.v4.app.DialogFragment dialogFragment) {
        return new PermissionResolverImpl() {


            @Override
            protected Context getContext() {
                return dialogFragment.getContext();
            }

            @Override
            protected boolean shouldShowRequestPermissionRationale(String permission) {
                return dialogFragment.shouldShowRequestPermissionRationale(permission);
            }

            @Override
            protected void requestPermissions(String[] permissions, int requestCode) {
                dialogFragment.requestPermissions(permissions, requestCode);
            }
        };
    }
}
