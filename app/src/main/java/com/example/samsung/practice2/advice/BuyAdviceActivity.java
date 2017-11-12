package com.example.samsung.practice2.advice;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.samsung.practice2.R;
import com.example.samsung.practice2.base.BaseActivity;

/**
 * Created by samsung on 2017/10/30.
 */

public class BuyAdviceActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_buyadvice);

        upDateActionBar();

        mToolbar=(Toolbar) findViewById(R.id.toolbar_advice);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("购买建议");
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.backblack);
        }

        BuyAdviceFragment buyAdviceFragment=new BuyAdviceFragment();
        attachFragmentAsSingle(buyAdviceFragment);
    }
}
