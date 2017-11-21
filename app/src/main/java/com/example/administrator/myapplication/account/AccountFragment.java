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

import cn.bmob.v3.BmobQuery;
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

    private EditText mEditPassword;
    private EditText mEditNickName;
    private EditText mEditPart;
    private EditText mEditTg;
    private TextView mNickName;
    private TextView mNumbers;

    private String _nickName;
    private String _password;
    private String _phoneNum;
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

        mEditPassword=(EditText) getActivity().findViewById(R.id.news_passwrod);
        mEditNickName=(EditText) getActivity().findViewById(R.id.news_nickname);
        mEditPart=(EditText) getActivity().findViewById(R.id.news_part);
        mEditTg=(EditText) getActivity().findViewById(R.id.news_tg);
        mNickName=(TextView) getActivity().findViewById(R.id.news_account);
        mNumbers=(TextView) getActivity().findViewById(R.id.news_number);

        BmobUser _user=BmobUser.getCurrentUser();
        mNickName.setText(_user.getUsername());
        mNumbers.setText(_user.getMobilePhoneNumber());

        _nickName=mEditNickName.getText().toString();
        _password=mEditPassword.getText().toString();
        _part=mEditPart.getText().toString();
        _teamGroup=mEditTg.getText().toString();

        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
