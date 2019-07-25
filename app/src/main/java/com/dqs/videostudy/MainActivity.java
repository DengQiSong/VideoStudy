package com.dqs.videostudy;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dqs.videostudy.app.BaseActivity;
import com.dqs.videostudy.module.Camera2RecordingActivity;
import com.dqs.videostudy.module.RecordingActivity;
import com.yanzhenjie.permission.AndPermission;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    //视频地址
    private TextView tvVideoAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvVideoAddress = findViewById(R.id.tv_videoAddress);

        AndPermission.with(this).runtime().permission(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(data -> {
                    Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_LONG).show();
                }).onDenied(data -> {
            Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
        }).start();
    }

    public void startVideo(View view) {//开始录像
        Intent intent = new Intent(this, RecordingActivity.class);
        startActivityForResult(intent, 1);
    }

    public void startVideos(View view) {//开始录像
        Intent intent = new Intent(this, Camera2RecordingActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (resultCode == 1) {
                Log.e(TAG, "onActivityReenter: 来了");

            }
        }
    }
}
