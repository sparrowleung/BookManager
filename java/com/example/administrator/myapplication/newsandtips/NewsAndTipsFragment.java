package com.example.administrator.myapplication.newsandtips;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.NewsTipsInformation;
import com.example.administrator.myapplication.recycleview.News;
import com.example.administrator.myapplication.recycleview.NewsTipsAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by 37289 on 2017/11/15.
 */

public class NewsAndTipsFragment extends BaseFragment {

    private View _rootView;
    private View _endLine;

    private List<News> mList;
    private RecyclerView mRecyclerView;
    private NewsTipsAdapter mAdapter;
    private CardView mCard;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(layoutInflater,container,saveInstanceState);
        _rootView=layoutInflater.inflate(R.layout.fragment_news,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList=new ArrayList<>();
        _endLine=(View) getActivity().findViewById(R.id.news_view);
        _endLine.setVisibility(View.VISIBLE);
        BmobQuery<NewsTipsInformation> _query=new BmobQuery<>();
        _query.order("-createAt");
        _query.findObjects(new FindListener<NewsTipsInformation>() {
            @Override
            public void done(List<NewsTipsInformation> list, BmobException e) {
                if(list.size() > 0) {
                    for (NewsTipsInformation object : list) {
                        News _news = new News(object.getTitle(), object.getSubTitle());
                        mList.add(_news);
                    }
                }
                mAdapter = new NewsTipsAdapter(mList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

}
