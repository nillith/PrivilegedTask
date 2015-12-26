package com.example.privilegedtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nillith.android.permissions.IPermissionResolver;
import com.nillith.android.permissions.IPermissionSession;
import com.nillith.android.permissions.PermissionResolver;

/**
 * Created by Nil on 2015/12/26.
 */
public class DemoDialog extends DialogFragment {
    IPermissionResolver permissionResolver;
    IPermissionSession<String> sampleSession;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        permissionResolver = PermissionResolver.create(this);
        sampleSession = permissionResolver.createSession(new SampleTask());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
