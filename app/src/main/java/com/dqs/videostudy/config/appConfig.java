package com.dqs.videostudy.config;

import com.dqs.videostudy.util.Camera2Util;

/**
 * Created by Dengqs on 2019/7/25 0025.
 */
public class appConfig {
    public static int RECORD_MAX_TIME = 30;//录制的总时长秒数，单位秒，默认10秒
    public static int RECORD_MIN_TIME = 2;//最小录制时长，单位秒，默认2秒

    public static int PREVIEW_MAX_HEIGHT = 1000;//最大高度预览尺寸，默认大于1000的第一个

   public static String PATH_SAVE_VIDEO= Camera2Util.getCamera2Path();//小视频存放地址，不设置的话默认在根目录的Camera2文件夹
    public static String PATH_SAVE_PIC= Camera2Util.getCamera2Path();//图片保存地址，不设置的话默认在根目录的Camera2文件夹

    public static Class ACTIVITY_AFTER_CAPTURE;//拍照完成后需要跳转的Activity,一般这个activity做处理照片或者视频用
    public static String INTENT_PATH_SAVE_VIDEO = "INTENT_PATH_SAVE_VIDEO"; //Intent跳转可用
    public static String INTENT_PATH_SAVE_PIC = "INTENT_PATH_SAVE_PIC";//Intent跳转可用

    public static boolean ENABLE_RECORD=true;//是否需要录像功能
    public static boolean ENABLE_CAPTURE=true;//是否需要拍照功能
}
