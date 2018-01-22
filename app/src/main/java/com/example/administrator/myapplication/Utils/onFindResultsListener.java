package com.example.administrator.myapplication.Utils;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 37289 on 2018/1/9.
 */

public abstract class onFindResultsListener<T> extends FindListener<T> {
    public onFindResultsListener(){}

    public void done(List<T> var1, BmobException var2){
        if(var2 == null){
            onSuccess(var1);
            onComplete(true);
        }else {
            onFail(var2.getErrorCode(), var2.getMessage());
            onComplete(true);
        }
    }

    public abstract void onSuccess(List<T> list);
    public abstract void onFail(int errorCode, String errorMessage);
    public abstract void onComplete(boolean normal);
}
