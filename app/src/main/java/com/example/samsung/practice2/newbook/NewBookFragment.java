package com.example.samsung.practice2.newbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samsung.practice2.R;
import com.example.samsung.practice2.base.BaseFragment;

/**
 * Created by samsung on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View _rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_newbook,container,false);
        return _rootView;
    }
}
