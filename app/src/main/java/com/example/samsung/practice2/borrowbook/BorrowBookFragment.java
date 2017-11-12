package com.example.samsung.practice2.borrowbook;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samsung.practice2.R;
import com.example.samsung.practice2.base.BaseFragment;
import com.example.samsung.practice2.recyclelist.book;
import com.example.samsung.practice2.recyclelist.bookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samsung on 2017/11/2.
 */

public class BorrowBookFragment extends BaseFragment {

    private View _rootView;
    private CardView mContent;
    private CardView mBookDetail;
    private View mDetail;
    private RecyclerView mRecyclerView;

    private bookAdapter _bookAdapter;
    private List<book> _list;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=layoutInflater.inflate(R.layout.fragment_borrow,container,false);
        return _rootView;

    }

    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        mContent=(CardView) getActivity().findViewById(R.id.borrow_card1);
        mContent.setRadius(16);
        mContent.setCardElevation(16);
        mContent.setContentPadding(6,6,6,6);

        mBookDetail=(CardView) getActivity().findViewById(R.id.borrow_card2);
        mBookDetail.setRadius(16);
        mBookDetail.setCardElevation(16);
        mBookDetail.setContentPadding(6,6,6,6);

        mDetail=(View) getActivity().findViewById(R.id.borrow_detail);
        mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBookDetail.getVisibility() == View.VISIBLE){
                    mBookDetail.setVisibility(View.GONE);
                }else {
                    mBookDetail.setVisibility(View.VISIBLE);

                }

            }
        });

        _list=new ArrayList<>();
        initbook();

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _bookAdapter=new bookAdapter(_list);
        mRecyclerView.setAdapter(_bookAdapter);

    }

    public void initbook(){
        for(int i=0;i<6;i++){
            book b1=new book("a",R.mipmap.ic_launcher);
            _list.add(b1);
            book b2=new book("b",R.mipmap.ic_launcher);
            _list.add(b2);
            book b3=new book("c",R.mipmap.ic_launcher);
            _list.add(b3);
        }
    }
}
