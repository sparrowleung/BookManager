package com.example.administrator.myapplication.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    public SharedPreferences.Editor mEditor;
    public SharedPreferences mPreferences;
    public List<String> mSave;
    public Set<String> mSet;
    public Gson mGson;

    public void InitSharePreferences(String FileName){
        mSave = new ArrayList<>();
        mSet = new HashSet<>();
        mEditor = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE).edit();
        mPreferences = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        mSet = mPreferences.getStringSet(FileName, null);
        mGson = new Gson();
    }
}
