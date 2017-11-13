package com.example.administrator.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.myapplication.base.BaseActivity;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by 37289 on 2017/11/13.
 */

public class VerifySmsActivity extends BaseActivity {

    private EventHandler _eventHandler;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        MobSDK.init(VerifySmsActivity.this,"22547eb1b56cc","e4d211713a8d98952bbc877d7265e9ae");

        InitializeSms();
        RegisterPage mRegisterPage =new RegisterPage();
        mRegisterPage.setRegisterCallback(_eventHandler);
        mRegisterPage.show(VerifySmsActivity.this);
    }

    public void InitializeSms(){
        _eventHandler=new EventHandler(){
            @Override
            public void afterEvent(int event,int result,Object data){
                if(result == SMSSDK.RESULT_COMPLETE){
                    if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VerifySmsActivity.this, "发送验证码成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(VerifySmsActivity.this, "手机验证成功",Toast.LENGTH_SHORT).show();
                            }
                        });
                        Log.d("VerifySmsActivity","VerifySuccess");
                        finish();
                    }else {
                        ((Throwable)data).printStackTrace();
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(_eventHandler);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        SMSSDK.unregisterEventHandler(_eventHandler);
    }
}
