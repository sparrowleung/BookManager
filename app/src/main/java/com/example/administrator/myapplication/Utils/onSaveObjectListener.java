package com.example.administrator.myapplication.Utils;

/**
 * Created by 37289 on 2018/1/10.
 */

public abstract class onSaveObjectListener {

    public onSaveObjectListener(){
    }

    public abstract void onSuccess(String objectId);
    public abstract void onFail(int errorCode, String errorMessage);
}
