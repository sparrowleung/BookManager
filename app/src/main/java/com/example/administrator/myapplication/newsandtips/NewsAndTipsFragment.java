package com.example.administrator.myapplication.newsandtips;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.category.BookInformation;
import com.example.administrator.myapplication.recycleview.book;
import com.example.administrator.myapplication.recycleview.bookAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 37289 on 2017/11/15.
 */

public class NewsAndTipsFragment extends BaseFragment {

    private View _rootView;

    private List<book> mList;
    private RecyclerView mRecyclerView;
    private bookAdapter mAdapter;
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

        BookInformation _object=new BookInformation();
        _object.setName("孤岛");
        _object.setAuthor("Sparrow");
        _object.setPrice(123.5);
        _object.setState(false);
        _object.setBorrowper("SparrowLeung");
        _object.setCategory("literature");
        _object.setBorrowtime(new Date(System.currentTimeMillis()));
        _object.setBacktime(new Date(System.currentTimeMillis()));
        _object.setPress("中国第一出版社");
        _object.setBorrowcount(1);
        _object.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT).show();
                }
            }
        });


        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList=new ArrayList<>();
        for(int i=0;i<6;i++){
            book b1=new book("a",R.mipmap.ic_launcher);
            mList.add(b1);
            book b2=new book("b",R.mipmap.ic_launcher);
            mList.add(b2);
            book b3=new book("c",R.mipmap.ic_launcher);
            mList.add(b3);
        }
        mAdapter=new bookAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mCard=(CardView) getActivity().findViewById(R.id.news_card);
    }
}
