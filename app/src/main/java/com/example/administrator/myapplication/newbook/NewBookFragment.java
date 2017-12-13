package com.example.administrator.myapplication.newbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.recycleview.Book;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View mRootView;
    private RecyclerView mRecyclerView;
    private List<Book> mList;
    private NewBookAdapter mNewBookAdapter;
    private String _TAG = NewBookFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mRootView = inflater.inflate(R.layout.fragment_newbook,container,false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.newbook_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mList=new ArrayList<>();

        InitSharePreferences(_TAG);
        if(mSet != null){
            mSave.addAll(mSet);
            for(int i = 0; i < mSave.size(); i++){
                mList.add(i, mGson.fromJson(mSave.get(i), Book.class));
            }
            mNewBookAdapter=new NewBookAdapter(mList);
            mRecyclerView.setAdapter(mNewBookAdapter);
            Log.d(_TAG,"has download to local");
        }else {
            Bquery();
        }
    }

    public void Bquery(){
        BmobQuery<BookInformation> _query = new BmobQuery<>();
        _query.order("-createdAt");
        _query.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> list, BmobException e) {
                if(e == null){
                    mSave = new ArrayList<>(15);
                    for(int i = 0; i < 15; i++) {
                        Book _book = new Book(list.get(i).getName(), list.get(i).getPhoto());
                        mList.add(_book);
                        mSave.add(i, mGson.toJson(_book));
                    }
                    mSet.addAll(mSave);
                    mEditor.putStringSet(_TAG, mSet).apply();
                }
                mNewBookAdapter=new NewBookAdapter(mList);
                mRecyclerView.setAdapter(mNewBookAdapter);
            }
        });
    }

    class NewBookAdapter extends RecyclerView.Adapter<NewBookAdapter.ViewHolder>{

        private List<Book> mList;

        class ViewHolder extends RecyclerView.ViewHolder{
            View _view;
            ImageView _imageView;
            TextView _textView;
            CardView _cardView;

            public ViewHolder(View view){
                super(view);
                _view = view;
                _imageView = (ImageView) view.findViewById(R.id.recycler_newbook_image);
                _textView = (TextView) view.findViewById(R.id.recycler_newbook_text);
                _cardView = (CardView) view.findViewById(R.id.recycler_newbook_cardview);
            }
        }

        public NewBookAdapter(List<Book> _list){
            this.mList=_list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int type){
            View _view = LayoutInflater.from(getContext()).inflate(R.layout.recycler_newbook,viewGroup,false);
            final ViewHolder _viewHolder = new ViewHolder(_view);
            _viewHolder._view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int _position = _viewHolder.getAdapterPosition();
                    Book _books = mList.get(_position);
                    Intent _intent = new Intent(getActivity(), BookDetailActivity.class);
                    _intent.putExtra("bookName",_books.getBookName());
                    startActivity(_intent);
                }
            });
            return _viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            int heights = (int) (300 + Math.random() * 300);
            Book _book = mList.get(position);
            viewHolder._textView.setText(_book.getBookName());
            Glide.with(getContext()).load(_book.getBookViewId().getFileUrl()).into(viewHolder._imageView);
            ViewGroup.LayoutParams _layoutParams=viewHolder._imageView.getLayoutParams();
            _layoutParams.height = heights;
            viewHolder._imageView.setLayoutParams(_layoutParams);
        }

        @Override
        public int getItemCount(){
            return mList.size();
        }

    }
}

