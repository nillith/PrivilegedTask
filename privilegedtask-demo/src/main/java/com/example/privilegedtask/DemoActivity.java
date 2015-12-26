package com.example.privilegedtask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nillith.android.permissions.IPermissionResolver;
import com.nillith.android.permissions.IPermissionSession;
import com.nillith.android.permissions.PermissionResolver;

public class DemoActivity extends AppCompatActivity {
    IPermissionResolver permissionResolver;
    IPermissionSession<String> sampleSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        permissionResolver = PermissionResolver.create(this);
        sampleSession =  permissionResolver.createSession(new SampleTask());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onSampleTask(View view) {
        sampleSession.initiate("sample arguments");
    }
    public void onSampleDialog(View view) {
        new SampleDialog().show(getSupportFragmentManager(),null);
    }
}
