package com.example.administrator.myapplication.category;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.bmob.UserInformation;
import com.example.administrator.myapplication.borrowbook.BookDetailActivity;
import com.example.administrator.myapplication.recycleview.Category;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by samsung on 2017/11/17.
 */

public class LiteratureFragment extends BaseFragment {

    private View mRootView;
    private String _TAG = LiteratureFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private List<Category> mList;
    private CategoryRecyclerView mCategoryRecyclerView;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        mRootView = layoutInflater.inflate(R.layout.fragment_literature,container,false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.litera_recylcerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();
        InitSharePreferences(_TAG);

        if (mSet != null) {
            mSave.addAll(mSet);
            for(int i = 0; i < mSave.size(); i++){
                mList.add(i, mGson.fromJson(mSave.get(i), Category.class));
            }
            mCategoryRecyclerView = new CategoryRecyclerView(mList);
            mRecyclerView.setAdapter(mCategoryRecyclerView);
            Log.d(_TAG,"has download to local");
        } else {
            Bquery();
        }
    }

    public void Bquery(){
        BmobQuery<BookInformation> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("category","literature");
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<BookInformation>() {
            @Override
            public void done(List<BookInformation> object, BmobException e) {
                if(e == null){
                    mSave = new ArrayList<>(object.size());
                    Log.d(_TAG,"hasn't download to local");
                    for(int i = 0; i < object.size(); i++){
                        Category a1 = new Category(object.get(i).getPhoto(),object.get(i).getName(),object.get(i).getAuthor()
                                ,object.get(i).getPress(),object.get(i).getState());
                        mList.add(a1);
                        mSave.add(i, mGson.toJson(a1));
                    }
                    mSet.addAll(mSave);
                    mEditor.putStringSet(_TAG, mSet).apply();
                    mCategoryRecyclerView = new CategoryRecyclerView(mList);
                    mRecyclerView.setAdapter(mCategoryRecyclerView);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(200);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            Bquery();
                            mCategoryRecyclerView.notifyDataSetChanged();
                        }
                    }).start();
                }
                break;
        }
    }

    class CategoryRecyclerView extends RecyclerView.Adapter<CategoryRecyclerView.ViewHolder>{

        private List<Category> _list;

        class ViewHolder extends RecyclerView.ViewHolder{
            View _view;
            ImageView _image;
            TextView _name;
            TextView _author;
            TextView _press;
            TextView _status;

            public ViewHolder(View view){
                super(view);
                _view = view;
                _image = (ImageView) view.findViewById(R.id.category_image);
                _name = (TextView) view.findViewById(R.id.category_name);
                _author = (TextView)view.findViewById(R.id.category_author);
                _press = (TextView) view.findViewById(R.id.category_press);
                _status = (TextView) view.findViewById(R.id.category_status);
            }
        }

        public CategoryRecyclerView(List<Category> Categorylist){
            _list = Categorylist;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type){
            View view=LayoutInflater.from(getContext()).inflate(R.layout.recycler_category,viewGroup,false);
            final ViewHolder viewHolder=new ViewHolder(view);
            viewHolder._view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = viewHolder.getAdapterPosition();
                    Category _category=_list.get(position);
                    Intent _intent=new Intent(getActivity(), BookDetailActivity.class);
                    _intent.putExtra("bookName",_category.getName());
                    _intent.putExtra("bookAuthor",_category.getAuthor());
                    _intent.putExtra("bookPress",_category.getPress());
                    startActivityForResult(_intent,1);
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position){
            Category _category = _list.get(position);
            viewHolder._name.setText(_category.getName());
            viewHolder._author.setText(_category.getAuthor());
            viewHolder._press.setText(_category.getPress());
            if(_category.getStatus()) {
                viewHolder._status.setText("可    借");
            }else {
                viewHolder._status.setText("已借出");
            }
            Glide.with(getContext()).load(_category.getImageId().getFileUrl()).into(viewHolder._image);
        }

        @Override
        public int getItemCount(){
            return _list.size();
        }
    }
}
