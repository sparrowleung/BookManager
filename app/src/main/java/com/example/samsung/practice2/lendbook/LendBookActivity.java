package com.example.samsung.practice2.lendbook;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.samsung.practice2.R;
import com.example.samsung.practice2.base.BaseActivity;

/**
 * Created by samsung on 2017/10/30.
 */

public class LendBookActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lendbook);

        upDateActionBar();

        mToolbar=(Toolbar) findViewById(R.id.toolbar_lend);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("图书详细");
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.backblack);
        }

        LendBookFragment lendBookFragment=new LendBookFragment();
        attachFragmentAsSingle(lendBookFragment);
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
