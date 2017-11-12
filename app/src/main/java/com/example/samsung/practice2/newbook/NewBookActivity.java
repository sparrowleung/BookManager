package com.example.samsung.practice2.newbook;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.samsung.practice2.R;
import com.example.samsung.practice2.base.BaseActivity;


/**
 * Created by samsung on 2017/10/30.
 */

public class NewBookActivity extends BaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_newbook);

        upDateActionBar();
        mToolbar=(Toolbar) findViewById(R.id.toolbar_newbook);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("新书推荐");
        android.support.v7.app.ActionBar mActionBar=getSupportActionBar();
        if (mActionBar != null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.backblack);
        }

        NewBookFragment newBookFragment=new NewBookFragment();
        attachFragmentAsSingle(newBookFragment);
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
