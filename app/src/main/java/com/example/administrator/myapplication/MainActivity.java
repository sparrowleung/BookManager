package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.borrowbook.BorrowBookFragment;
import com.example.administrator.myapplication.main.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout _drawerLayout;
    private long mFirstTime;
    private NavigationView _navigationView;
//    private FloatingActionButton _actionButton;
    private Toolbar _toolBar;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragmentList;
    private HomeFragment mFirstPageFragment;
    private BorrowBookFragment mBorrowBookFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upDateActionBar();

        _toolBar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(_toolBar);

        _drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        _navigationView=(NavigationView) findViewById(R.id.nav_view);
        android.support.v7.app.ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.navicon);
        }

        _navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_finish:
                        _drawerLayout.closeDrawers();
                        break;
                    case R.id.nav_Account:
                        Intent _intent=new Intent(MainActivity.this,VerifySmsActivity.class);
                        startActivity(_intent);break;
                }
                return false;
            }
        });

//        _actionButton=(FloatingActionButton) findViewById(R.id.floatb);
//        _actionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"Data delete",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getBaseContext(),"AAA",Toast.LENGTH_SHORT).show();
//                    }
//                }).show();
//            }
//        });

        mTabLayout=(TabLayout) findViewById(R.id.Tab_TabLayout);
        mViewPager=(ViewPager) findViewById(R.id.Tab_ViewPager);
        mFragmentList=new ArrayList<>();
        mFirstPageFragment=new HomeFragment();
        mBorrowBookFragment=new BorrowBookFragment();
        mFragmentList.add(mFirstPageFragment);
        mFragmentList.add(mBorrowBookFragment);
        TabMainViewPager tabMainViewPager=new TabMainViewPager(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(tabMainViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText("主        页");
        mTabLayout.getTabAt(1).setText("已借书籍");
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_category,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.settings:
                Toast.makeText(getBaseContext(),"aaa",Toast.LENGTH_SHORT).show();break;
            case android.R.id.home:
                _drawerLayout.openDrawer(GravityCompat.START);break;
        }
        return true;
    }

    public boolean onKeyDown(int keycode, KeyEvent event){
        long SecondTime=System.currentTimeMillis();
        if(keycode==event.KEYCODE_BACK){
            if(SecondTime-mFirstTime<2000){
                finish();
            }else {
                mFirstTime=System.currentTimeMillis();
                Toast.makeText(getBaseContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            }
            return  true;
        }
        return super.onKeyDown(keycode,event);
    }

    static class TabMainViewPager extends FragmentPagerAdapter {

        private List<Fragment> list;

        TabMainViewPager(FragmentManager fragmentManager, List<Fragment> fragmentslist){
            super(fragmentManager);
            this.list=fragmentslist;
        }

        @Override
        public Fragment getItem(int position){
            return list.get(position);
        }

        @Override
        public int getCount(){
            return list.size();
        }

    }

}