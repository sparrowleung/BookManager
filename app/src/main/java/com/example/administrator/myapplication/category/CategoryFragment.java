package com.example.administrator.myapplication.category;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class CategoryFragment extends BaseFragment {

    private View _rootView;

    private RecyclerView mRecyclerView;
    private List<book> _list;
    private bookAdapter _bookAdapter;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=layoutInflater.inflate(R.layout.fragment_category,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.category_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _list=new ArrayList<>();
        for(int i=0;i<2;i++){
            book a1=new book("a",R.mipmap.ic_launcher);
            _list.add(a1);
            book a2=new book("b",R.mipmap.ic_launcher);
            _list.add(a2);
        }
        _bookAdapter=new bookAdapter(_list);
        mRecyclerView.setAdapter(_bookAdapter);

    }
}
