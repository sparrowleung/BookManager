package com.example.administrator.myapplication.borrowbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.recycleview.Book;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 37289 on 2017/11/11.
 */

public class BorrowBookFragment extends BaseFragment {

    private View _rootView;
    private CardView mContent;
    private CardView mBookDetail;
    private View mDetail;
    private RecyclerView mRecyclerView;

    private BookAdapter _bookAdapter;
    private List<Book> _list;

    private TextView mBookCount;
    private TextView mAccountName;
    private BmobUser _user;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=layoutInflater.inflate(R.layout.fragment_borrow,container,false);
        return _rootView;
    }

    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        mContent=(CardView) getActivity().findViewById(R.id.borrow_card1);
        mBookDetail=(CardView) getActivity().findViewById(R.id.borrow_card2);
        mBookCount=(TextView) getActivity().findViewById(R.id.borrow_lendcount);
        mDetail=(View) getActivity().findViewById(R.id.borrow_detail);
        mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBookDetail.getVisibility() == View.VISIBLE){
                    mBookDetail.setVisibility(View.GONE);
                }else {
                    if (_list.size() > 0) {
                        mBookDetail.setVisibility(View.VISIBLE);
                        Bquery();
                        _bookAdapter.notifyDataSetChanged();
                    }
                }

            }
        });

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.recyclerview_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        _list=new ArrayList<>();

        mAccountName=(TextView) getActivity().findViewById(R.id.borrow_account);
        _user=BmobUser.getCurrentUser();
        if(_user != null){
            if(_user.getUsername() != null){
                mAccountName.setText(_user.getUsername());
                Bquery();
            }
        }else {
            mAccountName.setText("未登录");
            mBookCount.setText(" ");
        }


    }

    public void Bquery(){
        if(_list!=null) {
            _list.clear();
        }
        BmobQuery<BookInformation> bmobQuery=new BmobQuery<BookInformation>();
        bmobQuery.addWhereEqualTo("borrowper",_user.getUsername());
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> object, BmobException e) {
                if(e==null){

                    for(BookInformation bookInformation : object){
                        Book a1=new Book(bookInformation.getName(),R.drawable.book);
                        _list.add(a1);
                    }
                    mBookCount.setText(Integer.toString(object.size()));
                    _bookAdapter=new BookAdapter(_list);
                    mRecyclerView.setAdapter(_bookAdapter);
                }
            }
        });
    }

    class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

        private Context context;
        private List<Book> mbook;

         class ViewHolder extends RecyclerView.ViewHolder{
            View _view;
            CardView cardView;
            ImageView bookView;
            TextView bookname;

            public ViewHolder(View view){
                super(view);
                _view=view;
                cardView=(CardView) view;
                bookname=(TextView) view.findViewById(R.id.book_name);
                bookView=(ImageView) view.findViewById(R.id.book_image);
            }
        }

        public BookAdapter(List<Book> bookList){
            mbook=bookList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            if(context==null){
                context=viewGroup.getContext();
            }
            View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_borrowbook,viewGroup,false);
            final ViewHolder holder=new ViewHolder(view);
            holder._view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int _position = holder.getAdapterPosition();
                    Book _books = mbook.get(_position);
                    Intent _intent = new Intent(getActivity(), BookDetailActivity.class);
                    _intent.putExtra("bookName",_books.getBookName());
                    startActivity(_intent);
                }
            });
            return  holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Book books=mbook.get(position);
            holder.bookname.setText(books.getBookName());
            Glide.with(context).load(books.getBookViewId()).into(holder.bookView);
        }

        @Override
        public int getItemCount(){
            return mbook.size();
        }
    }
}