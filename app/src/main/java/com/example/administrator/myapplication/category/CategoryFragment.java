package com.example.administrator.myapplication.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

/**
 * Created by Administrator on 2017/10/30.
 */

public class CategoryFragment extends BaseFragment {

    private View _rootView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=layoutInflater.inflate(R.layout.fragment_category,container,false);
        return _rootView;
    }
}
