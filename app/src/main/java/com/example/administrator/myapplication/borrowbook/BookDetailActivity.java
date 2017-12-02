package com.example.administrator.myapplication.borrowbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.account.AccountActivity;
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

        mUser = BmobUser.getCurrentUser();
        mDate = new Date(System.currentTimeMillis());

        final BookInformation _book = new BookInformation();
        mBorrow = (Button) findViewById(R.id.detail_borrow);
        mBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser == null){
                    AccountLogin();
                }else {
                    _book.setBorrowcount(_book.getBorrowcount() + 1);
                    _book.setBorrowper(mUser.getUsername());
                    _book.setState(false);
                    _book.setBorrowtime(mDate);
                    _book.update(mUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(BookDetailActivity.this, "借阅成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        mBack = (Button) findViewById(R.id.detail_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUser == null){
                    AccountLogin();
                }else {
                    _book.setBorrowper(null);
                    _book.setState(true);
                    _book.setBorrowtime(null);
                    _book.setBacktime(mDate);
                    _book.update(mUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(BookDetailActivity.this, "借阅成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void AccountLogin(){
        AlertDialog.Builder _builder = new AlertDialog.Builder(BookDetailActivity.this);
        _builder.setTitle("提示");
        _builder.setMessage("进行该操作需要登录账号，是否马上登录?");
        _builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent _intent = new Intent(BookDetailActivity.this, AccountActivity.class);
                startActivity(_intent);
            }
        });
        _builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        _builder.create();
        _builder.show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:finish();break;
        }
        return true;
    }
}
