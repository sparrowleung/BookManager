package com.example.administrator.myapplication.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.bmob.UserInformation;
import com.example.administrator.myapplication.base.BaseFragment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by samsung on 2017/11/21.
 */

public class AccountLoginFragment extends BaseFragment {

    private View _rootView;
    private EditText mPhoneNum;
    private EditText mPassword;
    private Button mRegister;
    private Button mLogin;

    private String _phoneNum;
    private String _password;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(layoutInflater,container,saveInstanceState);
        _rootView=layoutInflater.inflate(R.layout.fragment_login,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mPhoneNum=(EditText) getActivity().findViewById(R.id.login_phoneNum);
        mPassword=(EditText) getActivity().findViewById(R.id.login_password);
        mRegister=(Button) getActivity().findViewById(R.id.login_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountRegisterFragment registerFragment=new AccountRegisterFragment();
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,registerFragment);
                fragmentTransaction.commit();
            }
        });

        mLogin=(Button) getActivity().findViewById(R.id.login_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _phoneNum=mPhoneNum.getText().toString();
                _password=mPassword.getText().toString();
                BmobUser.loginByAccount(_phoneNum, _password, new LogInListener<UserInformation>() {
                    @Override
                    public void done(UserInformation user, BmobException e){
                        if(e == null){
                            Toast.makeText(getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = getContext().getPackageManager()
                                    .getLaunchIntentForPackage(getContext().getPackageName());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getContext(),"登录失败，请输入正确信息",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
