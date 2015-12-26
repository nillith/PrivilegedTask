package com.example.privilegedtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nillith.android.permissions.IPermissionResolver;
import com.nillith.android.permissions.IPermissionSession;
import com.nillith.android.permissions.PermissionResolver;

public class SampleActivity extends AppCompatActivity {
    IPermissionResolver permissionResolver;
    IPermissionSession sampleSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        permissionResolver = PermissionResolver.create(this);
        sampleSession = permissionResolver.createSession(new SampleTask());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClick(View view) {
        sampleSession.initiate();
    }
}
