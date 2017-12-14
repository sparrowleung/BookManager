package com.example.administrator.myapplication.advice;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;
import com.example.administrator.myapplication.bmob.AdviceInformation;
import com.example.administrator.myapplication.bmob.UserInformation;
import com.example.administrator.myapplication.recycleview.Advice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BuyAdviceFragment extends BaseFragment implements View.OnClickListener {

    private View mRootView;
    private Button mNewBuild;
    private Button mCommit;

    private CardView mShowAdvice;
    private CardView mCommitAdvice;
    private SwipeRefreshLayout mSwipeRefresh;

    private EditText mNameEdit;
    private EditText mAuthorEdit;
    private EditText mPressEdit;
    private EditText mPriceEdit;
    private EditText mReasonEdit;

    private RecyclerView mRecyclerView;
    private List<Advice> mList;
    private AdviceAdapter mAdviceAdapter;

    private String mName;
    private String mAuthor;
    private String mPress;
    private String mPrice;
    private String mReason;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        mRootView = inflater.inflate(R.layout.fragment_advice,container,false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);

        InitSharePreferences(TAG);
        init();
    }

    public void init() {
        mNewBuild = (Button) getActivity().findViewById(R.id.advice_newbuild);
        mNewBuild.setOnClickListener(this);
        mCommit = (Button) getActivity().findViewById(R.id.advice_commit);
        mCommit.setOnClickListener(this);

        mShowAdvice = (CardView) getActivity().findViewById(R.id.advice_card1);
        mShowAdvice.setContentPadding(5, 5, 5, 5);
        mShowAdvice.setRadius(16);
        mShowAdvice.setCardElevation(8);
        mCommitAdvice = (CardView) getActivity().findViewById(R.id.advice_card2);
        mCommitAdvice.setContentPadding(5, 5, 5, 5);
        mCommitAdvice.setRadius(16);
        mCommitAdvice.setCardElevation(8);

        mNameEdit = (EditText) getActivity().findViewById(R.id.advice_name);
        mAuthorEdit = (EditText) getActivity().findViewById(R.id.advice_author);
        mPressEdit = (EditText) getActivity().findViewById(R.id.advice_press);
        mPriceEdit = (EditText) getActivity().findViewById(R.id.advice_price);
        mReasonEdit = (EditText) getActivity().findViewById(R.id.advice_reason);

        mSwipeRefresh = (SwipeRefreshLayout) getActivity().findViewById(R.id.advice_swipe);
        mSwipeRefresh.setColorSchemeResources(R.color.smssdk_gray);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Bquery();
                                mAdviceAdapter.notifyDataSetChanged();
                                mSwipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });

        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.advice_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mList = new ArrayList<>();

        if (_set != null) {
            _save.addAll(_set);
            for (int i = 0; i < _save.size(); i++) {
                mList.add(i, _gson.fromJson(_save.get(i), Advice.class));
            }
            mAdviceAdapter = new AdviceAdapter(mList);
            mRecyclerView.setAdapter(mAdviceAdapter);
        } else {
            Bquery();
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.advice_newbuild:
                if(mCommitAdvice.getVisibility() ==View.VISIBLE) {
                    mCommitAdvice.setVisibility(View.GONE);
                    mNewBuild.setText("新建");
                }else {
                    mCommitAdvice.setVisibility(View.VISIBLE);
                    mNewBuild.setText("收起");
                }break;
            case R.id.advice_commit:
                BmobUser _user = BmobUser.getCurrentUser();
                if(_user != null) {
                    mName = mNameEdit.getText().toString();
                    mAuthor = mAuthorEdit.getText().toString();
                    mPrice = mPriceEdit.getText().toString();
                    mPress = mPressEdit.getText().toString();
                    mReason = mReasonEdit.getText().toString();
                    if(!TextUtils.isEmpty(mName) && !TextUtils.isEmpty(mAuthor) && !TextUtils.isEmpty(mPrice) && !TextUtils.isEmpty(mPress) && !TextUtils.isEmpty(mReason)) {

                        AdviceInformation _advice = new AdviceInformation();
                        _advice.setBookName(mName);
                        _advice.setAuthor(mAuthor);
                        _advice.setPrice(mPrice);
                        _advice.setPress(mPress);
                        _advice.setReason(mReason);
                        _advice.setAdvicer(_user.getUsername());
                        _advice.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(getContext(), "已收到您的建议，谢谢", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getContext(), "信息有误，请修正后再提交", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(getContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getContext(),"请先登录账号",Toast.LENGTH_SHORT).show();
                }
                mNameEdit.setText(null);
                mAuthorEdit.setText(null);
                mPressEdit.setText(null);
                mPriceEdit.setText(null);
                mReasonEdit.setText(null);
                mCommitAdvice.setVisibility(View.GONE);break;
        }
    }

    public void Bquery(){
        if(mList != null) {
            mList.clear();
        }
        if(_set == null){
            _set = new HashSet<>();
        }
        BmobQuery<AdviceInformation> _query = new BmobQuery<>();
        _query.findObjects(new FindListener<AdviceInformation>() {
            @Override
            public void done(List<AdviceInformation> list, BmobException e) {
                _save = new ArrayList<>(list.size());
                if(e==null){
                    for(int i = 0; i < list.size(); i++){
                        Advice _advice = new Advice(list.get(i).getBookName(),list.get(i).getAuthor(),list.get(i).getPress(),list.get(i).getPrice()
                                ,list.get(i).getAdvicer());
                        mList.add(_advice);
                        _save.add(i, _gson.toJson(_advice));
                    }
                }
                _set.addAll(_save);
                _editor.putStringSet(TAG, _set).apply();
                mAdviceAdapter = new AdviceAdapter(mList);
                mRecyclerView.setAdapter(mAdviceAdapter);
            }
        });
    }

    class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.ViewHolder>{

        private List<Advice> _list;

        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView mName;
            private TextView mAuthor;
            private TextView mPress;
            private TextView mAdvicer;
            private TextView mPrice;
            private ImageView mImage;

            public ViewHolder(View view){
                super(view);
                mName = (TextView) view.findViewById(R.id.adviceRe_name);
                mAuthor = (TextView) view.findViewById(R.id.adviceRe_author);
                mPress = (TextView) view.findViewById(R.id.adviceRe_press);
                mAdvicer = (TextView) view.findViewById(R.id.adviceRe_advicer);
                mPrice = (TextView) view.findViewById(R.id.adviceRe_price);
                mImage = (ImageView) view.findViewById(R.id.adviceRe_image);
            }
        }

        public AdviceAdapter(List<Advice> mList){
            this._list=mList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int type){
            View _view = LayoutInflater.from(getContext()).inflate(R.layout.recylcer_advice,viewGroup,false);
            ViewHolder _viewHolder = new ViewHolder(_view);
            return _viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder,int position){
            Advice _advice = _list.get(position);
            viewHolder.mName.setText(_advice.getName());
            viewHolder.mAuthor.setText(_advice.getAuthor());
            viewHolder.mPress.setText(_advice.getAuthor());
            viewHolder.mAdvicer.setText(_advice.getAdvicer());
            viewHolder.mPrice.setText(_advice.getPrice());
            BmobQuery<UserInformation> _user = new BmobQuery<>();
            _user.addWhereEqualTo("username",_advice.getAdvicer());
            _user.findObjects(new FindListener<UserInformation>() {
                @Override
                public void done(List<UserInformation> list, BmobException e) {
                    if(e == null){
                        Glide.with(getContext()).load(list.get(0).getImage().getFileUrl()).into(viewHolder.mImage);
                    }else {
                        Log.d("BuyAdviceFragment","errorMessage = "+e.getMessage()+" errorCode = "+e.getErrorCode());
                    }
                }
            });

        }

        @Override
        public int getItemCount(){
            return _list.size();
        }
    }
}
