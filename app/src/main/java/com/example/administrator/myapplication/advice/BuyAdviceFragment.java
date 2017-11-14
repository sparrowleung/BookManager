package com.example.administrator.myapplication.advice;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.recycleview.book;
import com.example.administrator.myapplication.recycleview.bookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BuyAdviceFragment extends BaseFragment implements View.OnClickListener {

    private View _rootView;
    private View mNewBuild;
    private View mCommit;

    private CardView mShowAdvice;
    private CardView mCommitAdvice;

    private EditText mNameEdit;
    private EditText mAuthorEdit;
    private EditText mPressEdit;
    private EditText mPriceEdit;
    private EditText mReasonEdit;

    private RecyclerView mRecyclerView;
    private List<book> _list;
    private bookAdapter _bookAdapter;

    private String mName;
    private String mAuthor;
    private String mPress;
    private String mPrice;
    private String mReason;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_advice,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        init();


    }

    public void init(){
        mNewBuild=(View) getActivity().findViewById(R.id.advice_newbuild);
        mNewBuild.setOnClickListener(this);
        mCommit=(View) getActivity().findViewById(R.id.advice_commit);
        mCommit.setOnClickListener(this);

        mShowAdvice=(CardView) getActivity().findViewById(R.id.advice_card1);
        mShowAdvice.setContentPadding(5,5,5,5);
        mShowAdvice.setRadius(16);
        mShowAdvice.setCardElevation(8);
        mCommitAdvice=(CardView) getActivity().findViewById(R.id.advice_card2);
        mCommitAdvice.setContentPadding(5,5,5,5);
        mCommitAdvice.setRadius(16);
        mCommitAdvice.setCardElevation(8);

        mNameEdit=(EditText) getActivity().findViewById(R.id.advice_name);
        mAuthorEdit=(EditText) getActivity().findViewById(R.id.advice_author);
        mPressEdit=(EditText) getActivity().findViewById(R.id.advice_press);
        mPriceEdit=(EditText) getActivity().findViewById(R.id.advice_price);
        mReasonEdit=(EditText) getActivity().findViewById(R.id.advice_reason);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.advice_recyclerview);
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

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.advice_newbuild: mCommitAdvice.setVisibility(View.VISIBLE);break;
            case R.id.advice_commit: mCommitAdvice.setVisibility(View.GONE);break;
        }
    }
}
