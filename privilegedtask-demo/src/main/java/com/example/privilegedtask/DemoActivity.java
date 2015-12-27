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
        setContentView(R.layout.activity_demo);
        permissionResolver = PermissionResolver.create(this);
        sampleSession =  permissionResolver.createSession(new DemoTask());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionResolver.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    public void onDemoTask(View view) {
        sampleSession.initiate("Demo arguments");
    }
    public void onDemoDialog(View view) {
        new DemoDialog().show(getSupportFragmentManager(),null);
    }
}
