package com.example.privilegedtask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Nil on 2015/12/26.
 */
public class DemoDialog extends DialogFragment {
    DemoTask task = new DemoTask();
    Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DemoDialog.STYLE_NORMAL, android.R.style.);
    }

    View.OnClickListener onBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            task.start(DemoDialog.this,"DemoDialog arguments");
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.dialog_demo, container, true);

        btn = (Button) result.findViewById(R.id.btn);
        btn.setOnClickListener(onBtnClick);
        return result;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        task.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
