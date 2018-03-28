package com.example.administrator.myapplication.Utils;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 37289 on 2018/3/15.
 */

public class ImageKit {

    private static String TAG = ImageKit.class.getSimpleName();

    public ImageKit(){}

    public static <E extends Context> void  load(@NonNull E e, @NonNull String url, @NonNull ImageView view){

        final WeakReference<ImageView> imageView = new WeakReference<ImageView>(view);
        ImageView image = imageView.get();
        if(image != null){
            Glide.with(e).load(url).into(view);
        }

    }


    private static Set<ImageView> mLoadingImageView = new HashSet<>();

    public static void clearLoading(Context context){
        for(ImageView view : mLoadingImageView){
            Glide.with(context).clear(view);
        }
        mLoadingImageView.clear();
    }

    public static boolean isLoading(@NonNull ImageView view){
        return mLoadingImageView.contains(view);
    }

}
