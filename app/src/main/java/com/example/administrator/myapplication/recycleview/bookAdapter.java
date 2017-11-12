package com.example.administrator.myapplication.recycleview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;

import java.util.List;

/**
 * Created by 37289 on 2017/11/11.
 */

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.ViewHolder> {

    private Context context;
    private List<book> mbook;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView bookView;
        TextView bookname;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView) view;
            bookname=(TextView) view.findViewById(R.id.book_name);
            bookView=(ImageView) view.findViewById(R.id.book_image);
        }
    }

    public bookAdapter(List<book> bookList){
        mbook=bookList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        if(context==null){
            context=viewGroup.getContext();
        }
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_cardview,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        book books=mbook.get(position);
        holder.bookname.setText(books.getBookName());
        Glide.with(context).load(books.getBookViewId()).into(holder.bookView);
    }

    @Override
    public int getItemCount(){
        return mbook.size();
    }
}
