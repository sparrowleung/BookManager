package com.example.administrator.myapplication.newbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.recycleview.Category;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class NewBookFragment extends BaseFragment{

    private View mRootView;
    private RecyclerView mRecyclerView;
    private List<Category> mList;
    private NewBookAdapter mNewBookAdapter;
    private String _TAG = NewBookFragment.class.getSimpleName();
    private ProgressBar mProgressBar;

    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;
    private List<String> mSave;
    private Set<String> mSet;
    private Gson mGson;
    private ComparatorImpl mComparator = new ComparatorImpl();

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

        mSave = new ArrayList<>();
        mEditor = getContext().getSharedPreferences(_TAG, Context.MODE_PRIVATE).edit();
        mPreferences = getContext().getSharedPreferences(_TAG, Context.MODE_PRIVATE);
        mSet = mPreferences.getStringSet(_TAG, null);
        mGson = new Gson();
        mProgressBar = (ProgressBar) getActivity().findViewById(R.id.newbook_progressbar);

        if(mSet != null){
            mSave.addAll(mSet);
            Collections.sort(mSave,mComparator);
            for(int i = 0; i < mSave.size(); i++){
                mList.add(i, mGson.fromJson(mSave.get(i), Category.class));
            }
            mNewBookAdapter=new NewBookAdapter(mList);
            mRecyclerView.setAdapter(mNewBookAdapter);
        }else {
            if (NetworkAvailale(getContext())) {
                Bquery();
                mProgressBar.setVisibility(View.VISIBLE);
            }else {
                Toast.makeText(getContext(), "暂无网络，请检查网络", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Bquery(){
        if(mList.size() > 0){
            mList.clear();
        }
        if (mSet == null) {
            mSet = new HashSet<>();
        }
        BmobQuery<BookInformation> _query = new BmobQuery<>();
        _query.order("-createdAt");
        _query.setLimit(12);
        _query.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> object, BmobException e) {
                mSave = new ArrayList<>(object.size());
                if(e == null){
                    for(int i = 0; i < 10; i++) {
                        Category a1 = new Category(object.get(i).getObjectId(), object.get(i).getCreatedAt(), object.get(i).getName()
                                , object.get(i).getAuthor(), object.get(i).getBorrowcount(), object.get(i).getPress(), object.get(i).getPrice(), object.get(i).getState(),
                                object.get(i).getCategory(), object.get(i).getBorrowper(), object.get(i).getPhoto(), object.get(i).getBorrowtime(), object.get(i).getBacktime());
                        mList.add(i, a1);
                        mSave.add(i, mGson.toJson(a1));
                    }
                }
                mProgressBar.setVisibility(View.GONE);
                mSet.addAll(mSave);
                mEditor.putStringSet(_TAG, mSet).apply();
                mNewBookAdapter=new NewBookAdapter(mList);
                mRecyclerView.setAdapter(mNewBookAdapter);
            }
        });
    }

    class NewBookAdapter extends RecyclerView.Adapter<NewBookAdapter.ViewHolder>{

        private List<Category> mList;

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

        public NewBookAdapter(List<Category> _list){
            this.mList = _list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int type){
            View _view = LayoutInflater.from(getContext()).inflate(R.layout.recycler_newbook,viewGroup,false);
            final ViewHolder _viewHolder = new ViewHolder(_view);
            _viewHolder._view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int _position = _viewHolder.getAdapterPosition();
                    Category _category = mList.get(_position);
                    Intent _intent = new Intent(getActivity(), BookDetailActivity.class);
                    _intent.putExtra("bookName", _category.getName());
                    _intent.putExtra("bookAuthor", _category.getAuthor());
                    _intent.putExtra("bookPress", _category.getPress());
                    _intent.putExtra("bookCategory", _TAG);
                    _intent.putExtra("objectId", _category.get_objectId());
                    _intent.putExtra("borrowper", _category.getBorrowper());
                    startActivity(_intent);
                }
            });
            return _viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder,int position){
            int heights = (int) (300 + Math.random() * 300);
            Category _book = mList.get(position);
            viewHolder._textView.setText(_book.getName());
            Glide.with(getContext()).load(_book.getPhoto().getFileUrl()).into(viewHolder._imageView);
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

