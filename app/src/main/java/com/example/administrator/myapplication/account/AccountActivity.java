package com.example.administrator.myapplication.account;

import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.myapplication.base.BaseActivity;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by samsung on 2017/11/14.
 */

public class AccountActivity extends BaseActivity {

    private EventHandler _eventHandler;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        MobSDK.init(AccountActivity.this,"22547eb1b56cc","e4d211713a8d98952bbc877d7265e9ae");

        InitializeSms();
        RegisterPage mRegisterPage =new RegisterPage();
        mRegisterPage.setRegisterCallback(_eventHandler);
        mRegisterPage.show(AccountActivity.this);
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
                                Toast.makeText(AccountActivity.this, "发送验证码成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(AccountActivity.this, "手机验证成功",Toast.LENGTH_SHORT).show();
                            }
                        });
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
