package com.dqs.videostudy.module;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dqs.videostudy.R;
import com.dqs.videostudy.widget.LineProgressView;
import com.dqs.videostudy.widget.RecordView;
import com.lansosdk.videoeditor.LanSoEditor;

public class RecordingActivity extends AppCompatActivity {

    private SurfaceView surfaceView;
    private RecordView recordView;
    private ImageView iv_delete, iv_next, iv_change_camera, iv_flash_video;
    private LineProgressView lineProgressView;
    private TextView editorTextView, tv_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        //初始化蓝松sdk
        LanSoEditor.initSDK(getApplicationContext(), null);
        initView();

    }

    private void initView() {
        surfaceView = findViewById(R.id.surfaceView);
        recordView = findViewById(R.id.recordView);
        iv_delete = findViewById(R.id.iv_delete);
        iv_next = findViewById(R.id.iv_next);
        iv_flash_video = findViewById(R.id.iv_flash_video);
        iv_change_camera = findViewById(R.id.iv_camera_mode);
        lineProgressView = findViewById(R.id.lineProgressView);
        tv_hint = findViewById(R.id.tv_hint);

        surfaceView.post(() -> {
            int width = surfaceView.getWidth();
            int height = surfaceView.getHeight();
            float viewRatio = width * 1f / height;
            float videoRatio = 9f / 16f;
            ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
            if (viewRatio > videoRatio) {
                layoutParams.height = (int) (width / viewRatio);
            } else {
                layoutParams.width = (int) (height * viewRatio);
            }
            surfaceView.setLayoutParams(layoutParams);
        });


    }
}
