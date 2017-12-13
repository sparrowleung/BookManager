package com.example.administrator.myapplication.advice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

/**
 * Created by Administrator on 2017/12/3.
 */

public class BuyAdviceDetailFragment extends BaseFragment {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container , Bundle saveInstanceState){
        mRootView = layoutInflater.inflate(R.layout.fragment_advicedetail,container,false);
        return mRootView;
    }
}
