package com.dqs.videostudy.module;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dqs.videostudy.R;
import com.dqs.videostudy.util.RecordUtil;
import com.dqs.videostudy.widget.LineProgressView;
import com.dqs.videostudy.widget.RecordView;
import com.lansosdk.videoeditor.LanSoEditor;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class RecordingActivity extends AppCompatActivity {

    public static final float MAX_VIDEO_TIME = 60f * 1000;  //最大录制时间
    public static final float MIN_VIDEO_TIME = 2f * 1000;  //最小录制时间

    //是否在录制视频
    private AtomicBoolean isRecordVideo = new AtomicBoolean(false);
    //拍照
    private AtomicBoolean isShotPhoto = new AtomicBoolean(false);

    private ArrayList<String> segmentList = new ArrayList<>();//分段视频地址

    private SurfaceView surfaceView;
    private RecordView recordView;
    private ImageView iv_delete, iv_next, iv_change_camera, iv_flash_video;
    private LineProgressView lineProgressView;
    private TextView editorTextView, tv_hint;

    private RecordUtil recordUtil;
    private ArrayBlockingQueue<byte[]> mYUVQueue = new ArrayBlockingQueue<>(10);


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

    private void initData() {
        lineProgressView.setMinProgress(MIN_VIDEO_TIME / MAX_VIDEO_TIME);
        recordView.setOnGestureListener(new RecordView.OnGestureListener() {
            @Override
            public void onDown() {
                //长按录像
                isRecordVideo.set(true);
                goneRecordLayout();
                startRecord();

            }

            @Override
            public void onUp() {
                if (isRecordVideo.get()) {
                    isRecordVideo.set(false);
                }

            }

            @Override
            public void onClick() {
                //拍照
                if (segmentList.size() == 0) {
                    isShotPhoto.set(true);
                }
            }
        });
    }

    //隐藏部分控件
    private void goneRecordLayout(){
        tv_hint.setVisibility(View.GONE);
        iv_delete.setVisibility(View.GONE);
        iv_next.setVisibility(View.GONE);
    }
    private String audioPath;
    private String videoPath;
    //录像方法
    private void startRecord(){

        /*videoPath = LanSongFileUtil.getPath()+System.currentTimeMillis()+".h264";
        audioPath = LanSongFileUtil.getPath()+System.currentTimeMillis()+".pcm";
        recordUtil = new RecordUtil(videoPath, audioPath, mCameraHelp.getWidth(), mCameraHelp.getHeight(), mYUVQueue);
        recordUtil.start(mCameraHelp.getCameraId()== Camera.CameraInfo.CAMERA_FACING_FRONT);

        videoDuration = 0;
        lineProgressView.setSplit();
        recordTime = System.currentTimeMillis();
        RxJavaUtil.loop(20, new RxJavaUtil.OnRxLoopListener() {
            @Override
            public Boolean takeWhile(){
                return recordUtil.isRecording();
            }
            @Override
            public void onExecute() {
                long currentTime = System.currentTimeMillis();
                videoDuration += currentTime - recordTime;
                recordTime = currentTime;
                long countTime = videoDuration;
                for (long time : timeList) {
                    countTime += time;
                }
                if (countTime <= MAX_VIDEO_TIME) {
                    lineProgressView.setProgress(countTime/ MAX_VIDEO_TIME);
                }else{
                    upEvent();
                    iv_next.callOnClick();
                }
            }
            @Override
            public void onFinish() {

                segmentList.add(videoPath);
                aacList.add(audioPath);
                timeList.add(videoDuration);
                initRecorderState();
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                lineProgressView.removeSplit();
            }
        });*/
    }
}
