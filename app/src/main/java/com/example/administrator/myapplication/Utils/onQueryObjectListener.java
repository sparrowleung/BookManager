package com.example.administrator.myapplication.Utils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by 37289 on 2018/1/10.
 */

public abstract class onQueryObjectListener<T> extends QueryListener<T> {

    public onQueryObjectListener(){}

    public void done(T var1, BmobException var2){
        if(var2 == null){
            onSuccess(var1);
            onComplete(true);
        }else {
            onFail(var2.getErrorCode(), var2.getMessage());
            onComplete(false);
        }
    }

    public abstract void onSuccess(T t);
    public abstract void onFail(int errorCode, String errorMessage);
    public abstract void onComplete(boolean normal);
}
