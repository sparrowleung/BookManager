package com.example.administrator.myapplication.history;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

/**
 * Created by 37289 on 2017/11/15.
 */

public class HistoryActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_history);
        mToolbar=(Toolbar) findViewById(R.id.toolbar_history);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("借阅历史");
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        HistoryFragment historyFragment=new HistoryFragment();
        attachFragmentAsSingle(historyFragment);
    }
}
