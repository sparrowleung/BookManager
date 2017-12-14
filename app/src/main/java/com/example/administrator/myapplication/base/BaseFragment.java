package com.example.administrator.myapplication.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import com.example.administrator.myapplication.category.TechnologyFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {


    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected String TAG ;

    @CallSuper
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    public SharedPreferences.Editor _editor;
    public SharedPreferences _preferences;
    public List<String> _save;
    public Set<String> _set;
    public Gson _gson;

    public void InitSharePreferences(String FileName){
        _save = new ArrayList<>();
        _editor = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE).edit();
        _preferences = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        _set = _preferences.getStringSet(FileName, null);
        _gson = new Gson();
    }

}
