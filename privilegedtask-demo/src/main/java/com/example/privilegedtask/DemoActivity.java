package com.example.privilegedtask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DemoActivity extends AppCompatActivity {
    DemoTask task = new DemoTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        task.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onDemoTask(View view) {
        task.start(this, "Demo arguments");
    }

    public void onDemoDialog(View view) {
        new DemoDialog().show(getSupportFragmentManager(), null);
    }
}
