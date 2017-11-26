package com.example.administrator.myapplication.history;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.recycleview.Book;
import com.example.administrator.myapplication.recycleview.BookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 37289 on 2017/11/15.
 */

public class HistoryFragment extends BaseFragment {

    private View _rootView;

    private List<Book> mList;
    private RecyclerView mRecyclerView;
    private BookAdapter mAdapter;

    private CardView mCard1;
    private CardView mCard2;
    private ImageView mImageView;
    private TextView mName;
    private TextView mBookNumber;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(layoutInflater,container,saveInstanceState);
        _rootView=layoutInflater.inflate(R.layout.fragment_history,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_history);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList=new ArrayList<>();
        for(int i=0;i<6;i++){
            Book b1=new Book("a",R.mipmap.ic_launcher);
            mList.add(b1);
            Book b2=new Book("b",R.mipmap.ic_launcher);
            mList.add(b2);
            Book b3=new Book("c",R.mipmap.ic_launcher);
            mList.add(b3);
        }
        mAdapter=new BookAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mCard1=(CardView) getActivity().findViewById(R.id.history_card1);
        mCard2=(CardView) getActivity().findViewById(R.id.history_card2);
        mImageView=(ImageView) getActivity().findViewById(R.id.hotbook_image);
        mName=(TextView) getActivity().findViewById(R.id.history_account);
        mBookNumber=(TextView) getActivity().findViewById(R.id.history_lendcount);
    }
}
