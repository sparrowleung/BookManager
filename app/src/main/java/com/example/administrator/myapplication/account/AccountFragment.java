package com.example.administrator.myapplication.account;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by samsung on 2017/11/16.
 */

public class AccountFragment extends BaseFragment {

    private View _rootView;
    private View mCommit;

    private CardView mCardView1;
    private CardView mCardView2;

    private ImageView mImageView;
    private ImageView mNewsSetting;
    private Button mPhoneVerify;
    private EventHandler _eventHandler;

    private EditText mEditName;
    private EditText mEditPassword;
    private EditText mEditEmail;
    private EditText mEditPhone;
    private EditText mEditNickName;
    private EditText mEditSex;
    private EditText mEditPart;
    private EditText mEditTg;
    private TextView mNickName;
    private TextView mNumbers;

    private String _name;
    private String _nickName;
    private String _email;
    private String _password;
    private String _phoneNum;
    private String _sex;
    private String _part;
    private String _teamGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_account,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        init();
    }

    public void init(){
        mCardView1=(CardView) getActivity().findViewById(R.id.news_card1);
        mCardView2=(CardView) getActivity().findViewById(R.id.news_card2);
        mCommit=(View) getActivity().findViewById(R.id.news_commit);

        mImageView=(ImageView) getActivity().findViewById(R.id.news_image);
        mNewsSetting=(ImageView) getActivity().findViewById(R.id.news_setting);
        mPhoneVerify=(Button) getActivity().findViewById(R.id.news_register);

        mEditName=(EditText) getActivity().findViewById(R.id.news_name);
        mEditPassword=(EditText) getActivity().findViewById(R.id.news_passwrod);
        mEditEmail=(EditText) getActivity().findViewById(R.id.news_email);
        mEditPhone=(EditText) getActivity().findViewById(R.id.news_phone);
        mEditNickName=(EditText) getActivity().findViewById(R.id.news_nickname);
        mEditSex=(EditText) getActivity().findViewById(R.id.news_sex);
        mEditPart=(EditText) getActivity().findViewById(R.id.news_part);
        mEditTg=(EditText) getActivity().findViewById(R.id.news_tg);
        mNickName=(TextView) getActivity().findViewById(R.id.news_account);
        mNumbers=(TextView) getActivity().findViewById(R.id.news_number);

        _name=mEditName.getText().toString();
        _email=mEditEmail.getText().toString();
        _nickName=mEditNickName.getText().toString();
        _password=mEditPassword.getText().toString();
        _phoneNum=mEditPhone.getText().toString();
        _sex=mEditSex.getText().toString();
        _part=mEditPart.getText().toString();
        _teamGroup=mEditTg.getText().toString();

        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser mUser=new BmobUser();
                mUser.setUsername(_name);
                mUser.setPassword(_password);
                mUser.setEmail(_email);
                mUser.signUp(new SaveListener<UserInformation>() {
                    @Override
                    public void done(UserInformation user,BmobException e){
                        if(e==null){
                            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"该账号已存在",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mPhoneVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitializeSms();
                RegisterPage mRegisterPage =new RegisterPage();
                mRegisterPage.setRegisterCallback(_eventHandler);
                mRegisterPage.show(getContext());
            }
        });
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
                                Toast.makeText(getContext(), "发送验证码成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "手机验证成功",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        SMSSDK.unregisterEventHandler(_eventHandler);
    }

}
