package com.example.administrator.myapplication.borrowbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Utils.BmobRequest;
import com.example.administrator.myapplication.Utils.onFindResultsListener;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.bmob.UserInformation;
import com.example.administrator.myapplication.recycleview.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 37289 on 2017/11/11.
 */

public class BorrowBookFragment extends BaseFragment {

    private View mRootView;
    private CardView mContent;
    private CardView mBookDetail;
    private Button mDetail;
    private RecyclerView mRecyclerView;

    private BookAdapter mBookAdapter;
    private List<Category> mList;

    private TextView mBookCount;
    private TextView mAccountName;
    private ImageView mAccountImage;
    private BmobUser _user;
    private ComparatorImpl mComparator;
    private SharedPreferences mPreferences;
    private ProgressBar mProgerssbar;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState){
        mRootView=layoutInflater.inflate(R.layout.fragment_borrow,container,false);
        return mRootView;
    }

    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        InitSharePreferences(TAG);
        mContent = (CardView) getActivity().findViewById(R.id.borrow_card1);
        mBookDetail = (CardView) getActivity().findViewById(R.id.borrow_card2);
        mBookCount = (TextView) getActivity().findViewById(R.id.borrow_lendcount);
        mDetail = (Button) getActivity().findViewById(R.id.borrow_detail);
        mAccountImage = (ImageView) getActivity().findViewById(R.id.borrow_image);
        _user = BmobUser.getCurrentUser();
        mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBookDetail.getVisibility() == View.VISIBLE){
                    mBookDetail.setVisibility(View.GONE);
                }else {
                    if (_user != null) {
                        if (NetworkAvailale(getContext())) {
                            Bquery();
                            mProgerssbar.setVisibility(View.VISIBLE);
                            mBookAdapter.notifyDataSetChanged();
                            mBookDetail.setVisibility(View.VISIBLE);
                        }else {
                            _save.clear();
                            mList.clear();
                            if (_set != null) {
                                _save.addAll(_set);
                                Collections.sort(_save, mComparator);
                                for (int i = 0; i < _save.size(); i++) {
                                    mList.add(i, _gson.fromJson(_save.get(i), Category.class));
                                }
                                mBookCount.setText(Integer.toString(_save.size()));
                                mBookAdapter = new BookAdapter(mList);
                                mRecyclerView.setAdapter(mBookAdapter);
                            }else {
                                Toast.makeText(getContext(), "暂无网络，请检查网络", Toast.LENGTH_SHORT).show();
                            }
                            mBookDetail.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        mComparator = new ComparatorImpl();

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerview_detail);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList=new ArrayList<>();
        mProgerssbar = (ProgressBar) getActivity().findViewById(R.id.borrow_progressbar);

        mAccountName = (TextView) getActivity().findViewById(R.id.borrow_account);
        mPreferences = getContext().getSharedPreferences("userFile",Context.MODE_PRIVATE);
        if(_user != null) {
            mAccountName.setText(_user.getUsername());

            if(mPreferences.getString("imageUrl", null) != null){
                Glide.with(getContext()).load(mPreferences.getString("imageUrl", null)).into(mAccountImage);
            }else {
                DownloadImage();
            }
            if (_set != null) {
                _save.addAll(_set);
                Collections.sort(_save, mComparator);
                for (int i = 0; i < _save.size(); i++) {
                    mList.add(i, _gson.fromJson(_save.get(i), Category.class));
                }
                mBookCount.setText(Integer.toString(_save.size()));
                mBookAdapter = new BookAdapter(mList);
                mRecyclerView.setAdapter(mBookAdapter);
            }
            else {
                Bquery();
            }

        }
        else {
            mAccountName.setText("未登录");
            mBookCount.setText(" ");
        }
    }

    public void Bquery(){
        if(mList.size() > 0) {
            mList.clear();
        }

        HashMap<String, Object> mHashMap = new HashMap<>();
        BmobRequest.findRequest("-updatedAt", 50, mHashMap, new onFindResultsListener<BookInformation>() {

            @Override
            public void onSuccess(List<BookInformation> object){
                _set = new TreeSet<>(mComparator);
                _save = new ArrayList<>(object.size());
                mProgerssbar.setVisibility(View.GONE);
                for (int i = 0; i < object.size(); i++) {
                    Category a1 = new Category(object.get(i).getObjectId(), object.get(i).getCreatedAt(), object.get(i).getName()
                            , object.get(i).getAuthor(), object.get(i).getBorrowcount(), object.get(i).getPress(), object.get(i).getPrice(), object.get(i).getState(),
                            object.get(i).getCategory(), object.get(i).getBorrowper(), object.get(i).getPhoto(), object.get(i).getBorrowtime(), object.get(i).getBacktime());
                    mList.add(a1);
                    _save.add(i, _gson.toJson(a1));
                }
                mBookCount.setText(Integer.toString(object.size()));
            }

            @Override
            public void onFail(int errorCode, String errorMessage){

            }

            @Override
            public void onComplete(boolean normal){
                _set.addAll(_save);
                _editor.putStringSet(TAG, _set).apply();

                mBookAdapter = new BookAdapter(mList);
                mRecyclerView.setAdapter(mBookAdapter);
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mBookAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void DownloadImage(){

        HashMap<String, Object> mHashMap = new HashMap<>();
        mHashMap.put("mobilePhoneNumber",_user.getMobilePhoneNumber());
        BmobRequest.findRequest("index", mHashMap, new onFindResultsListener<UserInformation>() {
            @Override
            public void onSuccess(List<UserInformation> object){
                Glide.with(getContext()).load(object.get(0).getImage().getFileUrl()).into(mAccountImage);
            }

            @Override
            public void onFail(int errorCode, String errorMessage){

            }

            @Override
            public void onComplete(boolean normal){

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Bquery();
                        }
                    }, 200);
                }
                break;
        }
    }

    class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

        private Context context;
        private List<Category> mbook;

         class ViewHolder extends RecyclerView.ViewHolder{
            View _view;
            CardView cardView;
            ImageView bookView;
            TextView bookname;
            TextView bookauthor;

            public ViewHolder(View view){
                super(view);
                _view = view;
                cardView = (CardView) view;
                bookname = (TextView) view.findViewById(R.id.book_name);
                bookView = (ImageView) view.findViewById(R.id.book_image);
                bookauthor = (TextView) view.findViewById(R.id.book_author);
            }
        }

        public BookAdapter(List<Category> bookList){
            mbook = bookList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            if(context == null){
                context = viewGroup.getContext();
            }
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_borrowbook,viewGroup,false);
            final ViewHolder holder = new ViewHolder(view);
            holder._view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int _position = holder.getAdapterPosition();
                    Category _category = mbook.get(_position);
                    Intent _intent = new Intent(getActivity(), BookDetailActivity.class);
                    _intent.putExtra("bookName", _category.getName());
                    _intent.putExtra("bookAuthor", _category.getAuthor());
                    _intent.putExtra("bookPress", _category.getPress());
                    _intent.putExtra("bookCategory", TAG);
                    _intent.putExtra("objectId", _category.get_objectId());
                    _intent.putExtra("borrowper", _category.getBorrowper());
                    startActivityForResult(_intent,1);
                }
            });
            return  holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            Category _category = mbook.get(position);
            holder.bookname.setText(_category.getName());
            holder.bookauthor.setText(_category.getAuthor());
            Glide.with(context).load(_category.getPhoto().getFileUrl()).into(holder.bookView);
        }

        @Override
        public int getItemCount(){
            return mbook.size();
        }
    }
}