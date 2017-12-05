package com.example.administrator.myapplication.history;

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
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.recycleview.Book;

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
                    _intent.putExtra("bookname",_books.getBookName());

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
