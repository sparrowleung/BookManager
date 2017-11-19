package com.example.administrator.myapplication.newbook;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.recycleview.book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View _rootView;
    private RecyclerView mRecyclerView;
    private List<book> _list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_newbook,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        mRecyclerView=(RecyclerView) getActivity().findViewById(R.id.newbook_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        _list=new ArrayList<>();
        for(int i=0;i<3;i++){
            book a1=new book("abcdd",R.mipmap.ic_launcher);
            _list.add(a1);
            book a2=new book("zddss",R.drawable.book);
            _list.add(a2);
            book a3=new book("fdsss",R.drawable.account);
            _list.add(a3);
        }
        NewBookAdapter _newBookAdapter=new NewBookAdapter(_list);
        mRecyclerView.setAdapter(_newBookAdapter);
    }


    class NewBookAdapter extends RecyclerView.Adapter<NewBookAdapter.ViewHolder>{

        private List<book> mList;

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView _imageView;
            TextView _textView;
            CardView _cardView;

            public ViewHolder(View view){
                super(view);
                _imageView=(ImageView) view.findViewById(R.id.recycler_newbook_image);
                _textView=(TextView) view.findViewById(R.id.recycler_newbook_text);
                _cardView=(CardView) view.findViewById(R.id.recycler_newbook_cardview);
            }
        }

        public NewBookAdapter(List<book> _list){
            this.mList=_list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int type){
            View _view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_newbook,viewGroup,false);
            ViewHolder _viewHolder=new ViewHolder(_view);
            return _viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            int heights=(int) (200 + Math.random() * 300);
            book _book=mList.get(position);
            viewHolder._textView.setText(_book.getBookName());
            Glide.with(getContext()).load(_book.getBookViewId()).into(viewHolder._imageView);
            ViewGroup.LayoutParams _layoutParams=viewHolder._imageView.getLayoutParams();
            _layoutParams.height=heights;
            viewHolder._imageView.setLayoutParams(_layoutParams);
        }

        @Override
        public int getItemCount(){
            return mList.size();
        }

    }
}

