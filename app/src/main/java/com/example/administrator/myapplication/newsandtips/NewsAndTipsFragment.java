package com.example.administrator.myapplication.newsandtips;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.renderscript.Element;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.NewsTipsInformation;
import com.example.administrator.myapplication.recycleview.News;
import com.example.administrator.myapplication.recycleview.NewsTipsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by 37289 on 2017/11/15.
 */

public class NewsAndTipsFragment extends BaseFragment {

    private View mRootView;
    private View mEndLine;

    private List<News> mList;
    private RecyclerView mRecyclerView;
    private NewsTipsAdapter mAdapter;

    private Gson mGson;
    private ComparatorImpl mComparator = new ComparatorImpl();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(layoutInflater,container,saveInstanceState);
        mRootView = layoutInflater.inflate(R.layout.fragment_news,container,false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mEndLine=(View) getActivity().findViewById(R.id.news_view);
        mEndLine.setVisibility(View.VISIBLE);
        InitSharePreferences("newstips");
        mGson = new Gson();

        if(_set != null){
            _save.addAll(_set);
            Collections.sort(_save,mComparator);
            Collections.reverse(_save);
            for(int i = 0; i < _save.size(); i++){
                mList.add(i, mGson.fromJson(_save.get(i), News.class));
            }
            mAdapter = new NewsTipsAdapter(mList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
