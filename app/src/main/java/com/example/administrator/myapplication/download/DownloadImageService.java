package com.example.administrator.myapplication.download;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.main.MainActivity;

import java.io.File;
import java.lang.annotation.Target;

/**
 * Created by 37289 on 2017/12/7.
 */

public class DownloadImageService implements Runnable {

    private String mUrl;
    private Context mContext;
    private MainActivity.ImageDownloadCallBack mImageDownloadCallBack;

    public DownloadImageService(Context context, String url, MainActivity.ImageDownloadCallBack imageDownloadCallBack){
        this.mContext = context;
        this.mUrl = url;
        this.mImageDownloadCallBack = imageDownloadCallBack;
    }

    @Override
    public void run(){
        File _file = null;
        try{
            _file = Glide.with(mContext).load(mUrl)
                    .downloadOnly(com.bumptech.glide.request.target.Target.SIZE_ORIGINAL, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                    .get();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(_file != null){
                mImageDownloadCallBack.onDownloadSuccess(_file);
            }else {
                mImageDownloadCallBack.onDownloadFFailed();
            }
        }
    }
}
