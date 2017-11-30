package com.example.administrator.myapplication.borrowbook;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bmob.BookInformation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 37289 on 2017/11/22.
 */

public class BookDetailActivity extends BaseActivity{

    private Toolbar mToolbar;
    private Button mBorrow;
    private Button mBack;
    private BmobUser mUser;
    private Date mDate;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_bookdetail);

        upDateActionBar();

        mToolbar=(Toolbar) findViewById(R.id.toolbar_detail);

        setSupportActionBar(mToolbar);
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        BookDetailFragment bookDetailFragment = new BookDetailFragment();
        attachFragmentAsSingle(bookDetailFragment);

        final BookInformation _book = new BookInformation();
        mBorrow = (Button) findViewById(R.id.detail_borrow);
        mBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _book.setBorrowcount(_book.getBorrowcount()+1);
                _book.setBorrowper(mUser.getUsername());
                _book.setState(false);
                _book.setBorrowtime(mDate);
                _book.update(mUser.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){
                            Toast.makeText(BookDetailActivity.this,"借阅成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mBack = (Button) findViewById(R.id.detail_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _book.setBorrowper(null);
                _book.setState(true);
                _book.setBorrowtime(null);
                _book.setBacktime(mDate);
                _book.update(mUser.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e == null){
                            Toast.makeText(BookDetailActivity.this,"借阅成功",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mUser = BmobUser.getCurrentUser();
        mDate = new Date(System.currentTimeMillis());
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }
}
