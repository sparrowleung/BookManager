package com.example.administrator.myapplication.newbook;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.recycleview.book;
import com.example.administrator.myapplication.recycleview.bookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View _rootView;
    private RecyclerView mRecyclerView;
    private List<book> _list;
    private bookAdapter _bookAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_newbook,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.newbook_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        _list=new ArrayList<>();
        for(int i=0;i<3;i++){
            book a1=new book("abcdd",R.mipmap.ic_launcher);
            _list.add(a1);
            book a2=new book("zddss",R.mipmap.ic_launcher);
            _list.add(a2);
            book a3=new book("fdsss",R.mipmap.ic_launcher);
            _list.add(a3);
        }
        _bookAdapter=new bookAdapter(_list);
        mRecyclerView.setAdapter(_bookAdapter);
    }
}

