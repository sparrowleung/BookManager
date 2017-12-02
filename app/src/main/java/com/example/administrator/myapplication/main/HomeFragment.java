package com.example.administrator.myapplication.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.category.CategoryActivity;
import com.example.administrator.myapplication.category.TechnologyFragment;
import com.example.administrator.myapplication.newbook.NewBookActivity;
import com.example.administrator.myapplication.newsandtips.NewsAndTipsActivity;
import com.example.administrator.myapplication.bmob.NewsTipsInformation;
import com.example.administrator.myapplication.recycleview.Category;
import com.example.administrator.myapplication.recycleview.News;
import com.example.administrator.myapplication.recycleview.NewsTipsAdapter;
import com.example.administrator.myapplication.recycleview.Book;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private View _rootView;
    private RecyclerView _bookRecyclerView;
    private RecyclerView _newsRecyclerView;

    private List<Book> _bookList=new ArrayList<>();
    private List<News> _newsList=new ArrayList<>();
    private HotBook _adapter;
    private NewsTipsAdapter _newsAdapter;

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

        _bookRecyclerView=(RecyclerView) _rootView.findViewById(R.id.recycle_hotlist);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        _bookRecyclerView.setLayoutManager(gridLayoutManager);

        _newsRecyclerView=(RecyclerView) _rootView.findViewById(R.id.recycle_newslist);
        _newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Bquery();

        mNewbook.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mBuyAdvice.setOnClickListener(this);
        mNews.setOnClickListener(this);
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

    public void Bquery(){
        if(_bookList.size() > 0) {
            _bookList.clear();
        }
        if(_newsList.size() > 0) {
            _newsList.clear();
        }
        BmobQuery<BookInformation> _query=new BmobQuery<>();
        _query.order("-borrowcount");
        _query.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> list, BmobException e) {
                if(list.size() >= 6) {
                    for (int i = 0; i < 6; i++) {
                        Book _book = new Book(list.get(i).getName(), R.drawable.account);
                        _bookList.add(_book);
                    }
                }else {
                    for(BookInformation object : list){
                        Book _book = new Book(object.getName(), R.drawable.account);
                        _bookList.add(_book);
                    }
                }
                _adapter=new HotBook(_bookList);
                _bookRecyclerView.setAdapter(_adapter);
            }
        });

        BmobQuery<NewsTipsInformation> _newsQuery=new BmobQuery<>();
        _newsQuery.order("-createAt");
        _newsQuery.findObjects(new FindListener<NewsTipsInformation>() {
            @Override
            public void done(List<NewsTipsInformation> list, BmobException e) {
                if(list.size() >=2){
                    for(int i=0;i<2;i++){
                        News _news=new News(list.get(i).getTitle(),list.get(i).getSubTitle());
                        _newsList.add(_news);
                    }
                }else {
                    for(NewsTipsInformation object : list){
                        News _news=new News(object.getTitle(),object.getSubTitle());
                        _newsList.add(_news);
                    }
                }
                _newsAdapter=new NewsTipsAdapter(_newsList);
                _newsRecyclerView.setAdapter(_newsAdapter);
            }
        });
    }

    class HotBook extends RecyclerView.Adapter<HotBook.ViewHolder>{

        private List<Book> _list;

       class ViewHolder extends RecyclerView.ViewHolder{
           private ImageView _imageView;
           private TextView _textView;
           private View _view;

          public ViewHolder(View view){
              super(view);
              _view = view;
              _imageView=(ImageView) view.findViewById(R.id.hotbook_image);
              _textView=(TextView) view.findViewById(R.id.hotbook_text);
           }
       }

       public HotBook(List<Book> mList){
           _list=mList;
       }

       @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int ViewType){
           View view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_hotbook,viewGroup,false);
           final ViewHolder viewHolder=new ViewHolder(view);
           viewHolder._view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   int position = viewHolder.getAdapterPosition();
                   Book _hot= _list.get(position);
                   Intent _intent=new Intent(getActivity(), BookDetailActivity.class);
                   _intent.putExtra("bookName",_hot.getBookName());
                   startActivity(_intent);
               }
           });
           return viewHolder;
       }

       @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            Book mBook=_list.get(position);
            viewHolder._textView.setText(mBook.getBookName());
            Glide.with(getContext()).load(mBook.getBookViewId()).into(viewHolder._imageView);
       }

       @Override
        public int getItemCount(){
            return _list.size();
       }
    }

}