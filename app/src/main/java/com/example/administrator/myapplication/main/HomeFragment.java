package com.example.administrator.myapplication.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.advice.BuyAdviceActivity;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.category.CategoryActivity;
import com.example.administrator.myapplication.newbook.NewBookActivity;
import com.example.administrator.myapplication.newsandtips.NewsAndTipsActivity;
import com.example.administrator.myapplication.bmob.NewsTipsInformation;
import com.example.administrator.myapplication.recycleview.Category;
import com.example.administrator.myapplication.recycleview.News;
import com.example.administrator.myapplication.recycleview.NewsTipsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private View mRootView;
    private RecyclerView mBookRecyclerView;
    private RecyclerView mNewsRecyclerView;

    private List<Category> mBookList = new ArrayList<>();
    private List<News> mNewsList = new ArrayList<>();
    private HotBook mAdapter;
    private NewsTipsAdapter mNewsAdapter;

    private View mNewbook;
    private View mCategory;
    private View mBuyAdvice;
    private View mNews;
    private CardView mContent;
    private CardView mNotice;
    private CardView mHotBook;

    private SharedPreferences.Editor mNewsEditor;
    private SharedPreferences.Editor mHotEditor;
    private SharedPreferences mNewsPreferences;
    private SharedPreferences mHotPreferences;
    private Set<String> mNewsSet;
    private Set<String> mHotSet;
    private List<String> mHotSave;
    private List<String> mNewsSave;
    private Gson mGson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        super.onCreateView(inflater,container,saveInstanceState);
        mRootView = inflater.inflate(R.layout.fragment_home, container,false);
        return mRootView;
    }

    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mNewbook = (View) getActivity().findViewById(R.id.first_newbook);
        mCategory = (View) getActivity().findViewById(R.id.first_category);
        mBuyAdvice = (View) getActivity().findViewById(R.id.first_advice);
        mNews = (View) getActivity().findViewById(R.id.table_title);
        mContent = (CardView) getActivity().findViewById(R.id.first_content);
        mContent.setCardElevation(8);
        mContent.setRadius(16);
        mContent.setContentPadding(5,5,5,5);
        mNotice = (CardView) getActivity().findViewById(R.id.first_notice);
        mNotice.setContentPadding(5,5,5,5);
        mNotice.setRadius(8);
        mNotice.setCardElevation(8);
        mHotBook = (CardView) getActivity().findViewById(R.id.first_hotbook);
        mHotBook.setContentPadding(5,5,5,5);
        mHotBook.setRadius(8);
        mHotBook.setCardElevation(8);

        mBookRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycle_hotlist);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        mBookRecyclerView.setLayoutManager(gridLayoutManager);

        mNewsRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycle_newslist);
        mNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNewsEditor = getContext().getSharedPreferences("newstips", Context.MODE_PRIVATE).edit();
        mHotEditor = getContext().getSharedPreferences("hotbooks", Context.MODE_PRIVATE).edit();
        mNewsPreferences = getContext().getSharedPreferences("newstips", Context.MODE_PRIVATE);
        mHotPreferences = getContext().getSharedPreferences("hotbooks", Context.MODE_PRIVATE);
        mNewsSet = mNewsPreferences.getStringSet("newstips",null);
        mHotSet = mHotPreferences.getStringSet("hotbooks",null);
        mGson = new Gson();

        if(mNewsSet != null){
            mNewsSave = new ArrayList<>();
            mNewsSave.addAll(mNewsSet);

            for(int i = 0; i < mNewsSave.size(); i++){
                mNewsList.add(i, mGson.fromJson(mNewsSave.get(i), News.class));
            }

            mNewsAdapter = new NewsTipsAdapter(mNewsList);
            mNewsRecyclerView.setAdapter(mNewsAdapter);

        }else {
            NewsBquery();
        }

        if(mHotSet != null){
            mHotSave = new ArrayList<>();
            mHotSave.addAll(mHotSet);

            for(int i = 0; i < mHotSave.size(); i++){
                mBookList.add(i, mGson.fromJson(mHotSave.get(i), Category.class));
            }
            mAdapter = new HotBook(mBookList);
            mBookRecyclerView.setAdapter(mAdapter);
        }else {
            HotBquery();
        }

        mNewbook.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mBuyAdvice.setOnClickListener(this);
        mNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        Intent _intent = new Intent();
        switch (view.getId()){
            case R.id.first_newbook:
                _intent = new Intent(getActivity(),NewBookActivity.class);
                break;
            case R.id.first_category:
                _intent = new Intent(getActivity(),CategoryActivity.class);
                break;
            case R.id.first_advice:
                _intent = new Intent(getActivity(),BuyAdviceActivity.class);
                break;
            case R.id.table_title:
                _intent = new Intent(getActivity(), NewsAndTipsActivity.class);
        }
        getActivity().startActivity(_intent);
    }

    public void NewsBquery(){
        if(mNewsList.size() > 0) {
            mNewsList.clear();
        }
        BmobQuery<NewsTipsInformation> _newsQuery=new BmobQuery<>();
        _newsQuery.order("-createAt");
        _newsQuery.findObjects(new FindListener<NewsTipsInformation>() {
            @Override
            public void done(List<NewsTipsInformation> list, BmobException e) {
                if (e == null) {
                    mNewsSet = new HashSet<>();
                    mNewsSave = new ArrayList<>(list.size());
                    if (list.size() >= 2) {
                        for (int i = 0; i < 2; i++) {
                            News _news = new News(list.get(i).getTitle(), list.get(i).getSubTitle());
                            mNewsSave.add(i, mGson.toJson(_news));
                            mNewsList.add(_news);
                        }
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            News _news = new News(list.get(i).getTitle(), list.get(i).getSubTitle());
                            mNewsList.add(_news);
                            mNewsSave.add(i, mGson.toJson(_news));
                        }
                    }
                    mNewsSet.addAll(mNewsSave);
                    mNewsEditor.putStringSet("newstips", mNewsSet).apply();
                    mNewsAdapter = new NewsTipsAdapter(mNewsList);
                    mNewsRecyclerView.setAdapter(mNewsAdapter);
                }
            }
        });
    }

    public void HotBquery(){
        if(mBookList.size() > 0) {
            mBookList.clear();
        }
        BmobQuery<BookInformation> _query=new BmobQuery<>();
        _query.order("-borrowcount");
        _query.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> object, BmobException e) {
                if (e == null) {
                    mHotSave = new ArrayList<>(object.size());
                    mHotSet = new HashSet<>();
                    if (object.size() >= 6) {
                        for (int i = 0; i < 6; i++) {
                            Category a1 = new Category(object.get(i).getPhoto(),object.get(i).getName(),object.get(i).getAuthor()
                                    ,object.get(i).getPress(),object.get(i).getState(),object.get(i).getBorrowper(),object.get(i).getCategory());
                            mBookList.add(a1);
                            mHotSave.add(i, mGson.toJson(a1));
                        }
                    } else {
                        for (int i = 0; i < object.size(); i++) {
                            Category a1 = new Category(object.get(i).getPhoto(),object.get(i).getName(),object.get(i).getAuthor()
                                    ,object.get(i).getPress(),object.get(i).getState(),object.get(i).getBorrowper(),object.get(i).getCategory());
                            mBookList.add(a1);
                            mHotSave.add(i, mGson.toJson(a1));
                        }
                    }
                    mHotSet.addAll(mHotSave);
                    mHotEditor.putStringSet("hotbooks", mHotSet).apply();
                    mAdapter = new HotBook(mBookList);
                    mBookRecyclerView.setAdapter(mAdapter);
                }
            }
        });

    }

    class HotBook extends RecyclerView.Adapter<HotBook.ViewHolder>{

        private List<Category> _list;

       class ViewHolder extends RecyclerView.ViewHolder{
           private ImageView _imageView;
           private TextView _textView;
           private View _view;

          public ViewHolder(View view){
              super(view);
              _view = view;
              _imageView = (ImageView) view.findViewById(R.id.hotbook_image);
              _textView = (TextView) view.findViewById(R.id.hotbook_text);
           }
       }

       public HotBook(List<Category> mList){
           _list = mList;
       }

       @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int ViewType){
           View view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_hotbook,viewGroup,false);
           final ViewHolder viewHolder=new ViewHolder(view);
           viewHolder._view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = viewHolder.getAdapterPosition();
                   Category _category = _list.get(position);
                   Intent _intent = new Intent(getActivity(), BookDetailActivity.class);
                   _intent.putExtra("bookName", _category.getName());
                   _intent.putExtra("bookAuthor", _category.getAuthor());
                   _intent.putExtra("bookPress", _category.getPress());
                   _intent.putExtra("bookCategory", "hotbooks");
                   _intent.putExtra("bookPosition", position);
                   startActivity(_intent);
               }
           });
           return viewHolder;
       }

       @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            Category mBook = _list.get(position);
            viewHolder._textView.setText(mBook.getName());
            Glide.with(getContext()).load(mBook.getImageId().getFileUrl()).into(viewHolder._imageView);
       }

       @Override
        public int getItemCount(){
            return _list.size();
       }
    }

}