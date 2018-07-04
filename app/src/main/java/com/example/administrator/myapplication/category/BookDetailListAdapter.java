package com.example.administrator.myapplication.category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.recycleview.Category;

import java.util.List;

/**
 * Created by 37289 on 2018/4/11.
 */

public class BookDetailListAdapter extends RecyclerView.Adapter<BookDetailListAdapter.BookDetailViewHolder> {

    private Context mContext;
    private List<Category> mList;
    private String _TAG;
    public BookDetailListAdapter(Context context, List list, String tag){
        mContext = context;
        mList = list;
        _TAG = tag;
    }

    class BookDetailViewHolder extends RecyclerView.ViewHolder{

        ImageView _image;
        TextView _name;
        TextView _author;
        TextView _press;
        TextView _status;
        View _view;
        public BookDetailViewHolder(View view){
            super(view);
            _image = (ImageView) view.findViewById(R.id.category_image);
            _name = (TextView) view.findViewById(R.id.category_name);
            _author = (TextView)view.findViewById(R.id.category_author);
            _press = (TextView) view.findViewById(R.id.category_press);
            _status = (TextView) view.findViewById(R.id.category_status);
            _view = view;
        }
    }

    @Override
    public BookDetailViewHolder onCreateViewHolder(ViewGroup viewGroup, int type){
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_category,viewGroup,false);
        final BookDetailViewHolder bookDetailViewHolder = new BookDetailViewHolder(view);
        bookDetailViewHolder._view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = bookDetailViewHolder.getAdapterPosition();
                Category _category= mList.get(position);
                Intent _intent=new Intent(mContext, BookDetailActivity.class);
                _intent.putExtra("bookName", _category.getName());
                _intent.putExtra("bookAuthor", _category.getAuthor());
                _intent.putExtra("bookPress", _category.getPress());
                _intent.putExtra("bookCategory", _TAG);
                _intent.putExtra("objectId", _category.get_objectId());
                _intent.putExtra("borrowper", _category.getBorrowper());
                ((Activity)mContext).startActivityForResult(_intent,1);
            }
        });
        return bookDetailViewHolder;
    }

    @Override
    public void onBindViewHolder(BookDetailViewHolder viewHolder, int position){
        Category _category = mList.get(position);
        viewHolder._name.setText(_category.getName());
        viewHolder._author.setText(_category.getAuthor());
        viewHolder._press.setText(_category.getPress());
        if(_category.getState()) {
            viewHolder._status.setText("可    借");
        }else {
            viewHolder._status.setText("已借出");
        }
        Glide.with(mContext).load(_category.getPhoto().getFileUrl()).into(viewHolder._image);
    }



    @Override
    public int getItemCount(){
        return mList.size();
    }
}
