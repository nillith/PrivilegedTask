package com.example.privilegedtask;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nillith.android.permissions.PrivilegedTask;

public class DemoActivity extends AppCompatActivity {
    DemoTask task = new DemoTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PrivilegedTask.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onDemoTask(View view) {
        task.initiate(this, "Demo arguments");
    }

    public void onDemoDialog(View view) {
        new DemoDialog().show(getSupportFragmentManager(), null);
    }
}
