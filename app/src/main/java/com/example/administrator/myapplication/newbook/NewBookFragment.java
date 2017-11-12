package com.example.administrator.myapplication.newbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View _rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_newbook,container,false);
        return _rootView;
    }
}

