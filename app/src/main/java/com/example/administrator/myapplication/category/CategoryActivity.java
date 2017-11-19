package com.example.administrator.myapplication.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30.
 */

public class CategoryActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> FragmentList;
    private LiteratureFragment mLiteratureFragment;
    private TechnologyFragment mTechnologyFragment;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_category);

        upDateActionBar();

        mToolbar=(Toolbar) findViewById(R.id.toolbar_category);
        mToolbar.setTitle("图书详细");
        setSupportActionBar(mToolbar);
        ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.back);
        }

        mTabLayout=(TabLayout) findViewById(R.id.category_tab);
        mViewPager=(ViewPager) findViewById(R.id.category_viewpage);

        mTechnologyFragment=new TechnologyFragment();
        mLiteratureFragment=new LiteratureFragment();
        FragmentList=new ArrayList<>();
        FragmentList.add(mTechnologyFragment);
        FragmentList.add(mLiteratureFragment);
        CategoryViewpager _categoryViewpager=new CategoryViewpager(getSupportFragmentManager(),FragmentList);
        mViewPager.setAdapter(_categoryViewpager);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("技术类");
        mTabLayout.getTabAt(1).setText("文学类");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();break;
        }
        return true;
    }

    class CategoryViewpager extends FragmentPagerAdapter{

        private List<Fragment> _list;

        CategoryViewpager(FragmentManager fm,List<Fragment> list){
            super(fm);
            this._list=list;
        }


        @Override
        public Fragment getItem(int position){
            return _list.get(position);
        }

        @Override
        public int getCount(){
            return _list.size();
        }
    }
}
