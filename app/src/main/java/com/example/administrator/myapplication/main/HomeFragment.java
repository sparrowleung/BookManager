package com.example.administrator.myapplication.main;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.advice.BuyAdviceActivity;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.category.CategoryActivity;
import com.example.administrator.myapplication.newbook.NewBookActivity;
import com.example.administrator.myapplication.newsandtips.NewsAndTipsActivity;
import com.example.administrator.myapplication.recycleview.book;
import com.example.administrator.myapplication.recycleview.bookAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private View _rootView;
    private SwipeRefreshLayout _swipeRefreshLayout;
    private RecyclerView _recyclerView;

    private List<book> _list=new ArrayList<>();
    private HotBook _adapter;

    private View mNewbook;
    private View mCategory;
    private View mBuyAdvice;
    private View mNews;
    private CardView mContent;
    private CardView mNotice;
    private CardView mHotBook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        _rootView=inflater.inflate(R.layout.fragment_home,container,false);
        return _rootView;
    }

    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mNewbook=(View) getActivity().findViewById(R.id.first_newbook);
        mCategory=(View) getActivity().findViewById(R.id.first_category);
        mBuyAdvice=(View) getActivity().findViewById(R.id.first_advice);
        mNews=(View) getActivity().findViewById(R.id.table_title);
        mContent=(CardView) getActivity().findViewById(R.id.first_content);
        mContent.setCardElevation(8);
        mContent.setRadius(16);
        mContent.setContentPadding(5,5,5,5);
        mNotice=(CardView) getActivity().findViewById(R.id.first_notice);
        mNotice.setContentPadding(5,5,5,5);
        mNotice.setRadius(8);
        mNotice.setCardElevation(8);
        mHotBook=(CardView) getActivity().findViewById(R.id.first_hotbook);
        mHotBook.setContentPadding(5,5,5,5);
        mHotBook.setRadius(8);
        mHotBook.setCardElevation(8);


        initbook();

        _recyclerView=(RecyclerView) _rootView.findViewById(R.id.recycle_view);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        _recyclerView.setLayoutManager(gridLayoutManager);
        _adapter=new HotBook(_list);
        _recyclerView.setAdapter(_adapter);

        mNewbook.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mBuyAdvice.setOnClickListener(this);
        mNews.setOnClickListener(this);
    }

    public void initbook(){
        for(int i=0;i<2;i++){
            book b1=new book("a",R.mipmap.ic_launcher);
            _list.add(b1);
            book b2=new book("b",R.mipmap.ic_launcher);
            _list.add(b2);
            book b3=new book("c",R.mipmap.ic_launcher);
            _list.add(b3);
        }
    }

    @Override
    public void onClick(View view){
        Intent _intent= new Intent();
        switch (view.getId()){
            case R.id.first_newbook:
                _intent=new Intent(getActivity(),NewBookActivity.class);
                break;
            case R.id.first_category:
                _intent=new Intent(getActivity(),CategoryActivity.class);
                break;
            case R.id.first_advice:
                _intent=new Intent(getActivity(),BuyAdviceActivity.class);
                break;
            case R.id.table_title:
                _intent=new Intent(getActivity(), NewsAndTipsActivity.class);
        }
        getActivity().startActivity(_intent);
    }

    class HotBook extends RecyclerView.Adapter<HotBook.ViewHolder>{

        private List<book> _list;

       class ViewHolder extends RecyclerView.ViewHolder{
           private ImageView _imageView;
           private TextView _textView;

          public ViewHolder(View view){
               super(view);
               _imageView=(ImageView) view.findViewById(R.id.hotbook_image);
               _textView=(TextView) view.findViewById(R.id.hotbook_text);
           }
       }

       public HotBook(List<book> mList){
           _list=mList;
       }

       @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int ViewType){
           View view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_hotbook,viewGroup,false);
           ViewHolder viewHolder=new ViewHolder(view);
           return viewHolder;
       }

       @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            book mBook=_list.get(position);
            viewHolder._textView.setText(mBook.getBookName());
            Glide.with(getContext()).load(mBook.getBookViewId()).into(viewHolder._imageView);
       }

       @Override
        public int getItemCount(){
            return _list.size();
       }
    }

}