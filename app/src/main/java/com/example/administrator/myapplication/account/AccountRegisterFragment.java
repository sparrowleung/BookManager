package com.example.administrator.myapplication.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bmob.UserInformation;
import com.example.administrator.myapplication.base.BaseFragment;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by samsung on 2017/11/21.
 */

public class AccountRegisterFragment extends BaseFragment {

    private View mRootView;
    private EventHandler _eventHandler;
    private Boolean phoneVerify=false;

    private EditText mEditPhoneNum;
    private EditText mEditPassword;
    private EditText mEditVerifyCode;
    private EditText mEditName;
    private EditText mEditPart;
    private EditText mEditTeam;

    private String mPhoneNum;
    private String mPassword;
    private String mVerifyCode;
    private String mName;
    private String mPart;
    private String mTeam;

    private Button mPhoneVer;
    private Button mRegister;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        mRootView = layoutInflater.inflate(R.layout.fragment_register,container,false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        mEditPhoneNum = (EditText) getActivity().findViewById(R.id.register_phone);
        mEditPassword = (EditText) getActivity().findViewById(R.id.register_password);
        mEditVerifyCode = (EditText) getActivity().findViewById(R.id.register_veifycode);
        mEditName = (EditText) getActivity().findViewById(R.id.register_name);
        mEditPart = (EditText) getActivity().findViewById(R.id.register_part);
        mEditTeam = (EditText) getActivity().findViewById(R.id.register_tg);

        InitializeSms();
        mPhoneVer = (Button) getActivity().findViewById(R.id.register_verb);
        mPhoneVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneNum = mEditPhoneNum.getText().toString();
                SMSSDK.getVerificationCode("86", mPhoneNum);
            }
        });


        mRegister = (Button) getActivity().findViewById(R.id.register_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVerifyCode = mEditVerifyCode.getText().toString();
                SMSSDK.submitVerificationCode("86", mPhoneNum, mVerifyCode);
            }
        });
    }

    public void InitializeSms(){
        _eventHandler=new EventHandler(){
            @Override
            public void afterEvent(int event,int result,Object data){
                final Message _message = new Message();
                if(result == SMSSDK.RESULT_COMPLETE){
                    if(event == SMSSDK.EVENT_GET_VERIFICATION_CODE){

                        _message.what=1;
                        handler.sendMessage(_message);

                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        phoneVerify = true;
                        mPassword = mEditPassword.getText().toString();
                        mName = mEditName.getText().toString();
                        mPart = mEditPart.getText().toString();
                        mTeam = mEditTeam.getText().toString();

                        UserInformation userInformation = new UserInformation();
                        userInformation.setPart(mPart);
                        userInformation.setTeamgroup(mTeam );
                        userInformation.setUsername(mName);
                        userInformation.setMobilePhoneNumber(mPhoneNum);
                        userInformation.setMobilePhoneNumberVerified(phoneVerify);
                        userInformation.setPassword(mPassword);
                        userInformation.signUp(new SaveListener<UserInformation>() {
                                @Override
                                public void done(UserInformation object,BmobException e){
                                    if(e == null){
                                        _message.what = 2;
                                        onDestroy();
                                    }else {
                                        _message.what = 3;
                                    }
                                    handler.sendMessage(_message);
                                }
                            });
                    }else {
                        ((Throwable)data).printStackTrace();
                    }
                }
            }
        };
        SMSSDK.registerEventHandler(_eventHandler);
    }


    public Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(getContext(), "发送验证码成功", Toast.LENGTH_SHORT).show();break;
                case 2:
                    Toast.makeText(getContext(),"注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = getContext().getPackageManager()
                            .getLaunchIntentForPackage(getContext().getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    break;
                case 3:
                    Toast.makeText(getContext(),"信息有误，注册失败",Toast.LENGTH_SHORT).show();break;
            }
            return false;
        }
    });

    @Override
    public void onDestroy(){
        super.onDestroy();
        SMSSDK.unregisterEventHandler(_eventHandler);
    }

}
