package com.example.administrator.myapplication.download;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by samsung on 2017/12/8.
 */

public class DownLoadImageService implements Runnable{

    public interface DownLoadImageCallBack{
        void dowloadSuccess(File file);
        void downloadFail();
    }

    private Context mContext;
    private String mUrl;
    private DownLoadImageCallBack mImageCallBack;

    public DownLoadImageService(Context context,String url,DownLoadImageCallBack downLoadImageCallBack){
        this.mContext = context;
        this.mUrl = url;
        this.mImageCallBack = downLoadImageCallBack;
    }

    @Override
    public void run(){
        File _file = null;
        try {
            _file = Glide.with(mContext).load(mUrl)
                    .downloadOnly(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).get();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(_file != null){
                saveImage();
            }else {

            }
        }
    }

    public void saveImage(){}
}
