package com.example.administrator.myapplication.account;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

/**
 * Created by samsung on 2017/11/16.
 */

public class AccountFragment extends BaseFragment {

    private View _rootView;

    private CardView mCardView1;
    private CardView mCardView2;

    private ImageView mImageView;
    private ImageView mNewsSetting;

    private EditText mEdit_name;
    private EditText mEdit_sex;
    private EditText mEdit_part;
    private EditText mEdit_tg;
    private TextView mNickName;
    private TextView mNumbers;

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

        mImageView=(ImageView) getActivity().findViewById(R.id.news_image);
        mNewsSetting=(ImageView) getActivity().findViewById(R.id.news_setting);

        mEdit_name=(EditText) getActivity().findViewById(R.id.news_name);
        mEdit_sex=(EditText) getActivity().findViewById(R.id.news_sex);
        mEdit_part=(EditText) getActivity().findViewById(R.id.news_part);
        mEdit_tg=(EditText) getActivity().findViewById(R.id.news_tg);
        mNickName=(TextView) getActivity().findViewById(R.id.news_account);
        mNumbers=(TextView) getActivity().findViewById(R.id.news_number);
    }

}
