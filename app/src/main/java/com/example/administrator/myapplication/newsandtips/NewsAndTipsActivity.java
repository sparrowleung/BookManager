package com.example.administrator.myapplication.newsandtips;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;


/**
 * Created by 37289 on 2017/11/15.
 */

public class NewsAndTipsActivity extends BaseActivity {
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_news);
        mToolbar=(Toolbar) findViewById(R.id.toolbar_news);
        mToolbar.setTitle("消息通知");
        setSupportActionBar(mToolbar);
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        NewsAndTipsFragment newsAndTipsFragment=new NewsAndTipsFragment();
        attachFragmentAsSingle(newsAndTipsFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();break;
        }
        return true;
    }
}
