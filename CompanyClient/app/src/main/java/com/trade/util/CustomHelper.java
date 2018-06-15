package com.trade.util;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;


/**
 * - 支持通过相机拍照获取图片
 * - 支持从相册选择图片
 * - 支持从文件选择图片
 * - 支持多图选择
 * - 支持批量图片裁切
 * - 支持批量图片压缩
 * - 支持对图片进行压缩
 * - 支持对图片进行裁剪
 * - 支持对裁剪及压缩参数自定义
 * - 提供自带裁剪工具(可选)
 * - 支持智能选取及裁剪异常处理
 * - 支持因拍照Activity被回收后的自动恢复
 * Author: crazycodeboy
 * Date: 2016/9/21 0007 20:10
 * Version:4.0.0
 * 技术博文：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 */
public class CustomHelper {
    private View rootView;

    public static CustomHelper of(View rootView) {
        return new CustomHelper(rootView);
    }

    private CustomHelper(View rootView) {
        this.rootView = rootView;
    }

//    public void onClick(TakePhoto takePhoto) {
//        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
//        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
//        Uri imageUri = Uri.fromFile(file);
//
//        configCompress(takePhoto);
//        takePhoto.onPickFromCapture(imageUri);
//    }

    // 配置压缩信息
    private void configCompress(TakePhoto takePhoto) {
        int maxSize = 102400;
        int width = 1080;
        int height = 1400;
        boolean showProgressBar = false; // 不显示压缩进度条
        boolean enableRawFile = true; // 拍照压缩后保存原图
        CompressConfig config;
        // 使用takePhoto自带的压缩工具
        config = new CompressConfig.Builder()
                .setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)
                .enableReserveRaw(enableRawFile)
                .create();
        takePhoto.onEnableCompress(config, showProgressBar);

    }

    public void onClick(TakePhoto takePhoto, boolean camera, int limit) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        if (!camera) {
            takePhoto.onPickMultiple(limit);
        } else {
            configTakePhotoOption(takePhoto);
            takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions(true));
        }
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true); // 使用自带的相册
        //            builder.setCorrectImage(true);// 纠正拍照旋转角度
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private CropOptions getCropOptions(boolean crop) {
        if (!crop) {
            return null;
        }
        int height = 800;
        int width = 800;
        boolean withWonCrop = true; // 是否使用第三方裁剪工具，而不用takePhoto自带的

        CropOptions.Builder builder = new CropOptions.Builder();

        builder.setOutputX(width).setOutputY(height); // 设置裁剪的宽高
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }
}
