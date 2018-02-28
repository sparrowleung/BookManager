package com.example.administrator.myapplication.Utils;

import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 37289 on 2018/1/10.
 */

public abstract class onUpdateObjectListener{
    public onUpdateObjectListener(){}

    public abstract void onSuccess(String objectId);
    public abstract void onFail(int errorCode, String errorMessage);
}
