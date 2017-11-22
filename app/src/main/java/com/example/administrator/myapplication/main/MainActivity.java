package com.example.administrator.myapplication.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.account.AccountActivity;
import com.example.administrator.myapplication.base.BaseActivity;
import com.example.administrator.myapplication.bmob.BmobActivity;
import com.example.administrator.myapplication.borrowbook.BorrowBookFragment;
import com.example.administrator.myapplication.history.HistoryActivity;
import com.example.administrator.myapplication.newsandtips.NewsAndTipsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.b.V;


public class MainActivity extends BaseActivity {

    private DrawerLayout _drawerLayout;
    private long mFirstTime;
    private NavigationView _navigationView;
    private Toolbar _toolBar;
    private TextView _heaterName;
    private ImageView _hearterImage;
    private View _heartLayout;
    private View _unLogin;
    private View _heartName;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private List<Fragment> mFragmentList;
    private HomeFragment mFirstPageFragment;
    private BorrowBookFragment mBorrowBookFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(MainActivity.this,"3027c605e33c5f3be61405857d181153");
        setContentView(R.layout.activity_main);

        upDateActionBar();

        _toolBar=(Toolbar) findViewById(R.id.toolbar);
        _toolBar.setTitle("图书管理系统");
        setSupportActionBar(_toolBar);
        android.support.v7.app.ActionBar mActionBar=getSupportActionBar();
        if(mActionBar!=null){
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setHomeAsUpIndicator(R.drawable.navicon);
        }

        _drawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        _navigationView=(NavigationView) findViewById(R.id.nav_view);
        _navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent _intent=new Intent();
                switch (item.getItemId()) {
                    case R.id.nav_Account:
                        _intent=new Intent(MainActivity.this,AccountActivity.class);
                        break;
                    case R.id.nav_History:
                        _intent=new Intent(MainActivity.this,HistoryActivity.class);
                        break;
                    case R.id.nav_Notice:
                        _intent=new Intent(MainActivity.this,NewsAndTipsActivity.class);
                        break;
                }
                startActivity(_intent);
                return false;
            }
        });

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

        _heartLayout=_navigationView.inflateHeaderView(R.layout.navi_heater);
        _heartName=(View) _heartLayout.findViewById(R.id.nav_heart);
        _heaterName=(TextView) _heartLayout.findViewById(R.id.username);
        _unLogin=(View) _heartLayout.findViewById(R.id.nav_unlogin);
        _hearterImage=(ImageView) _heartLayout.findViewById(R.id.account_image);

        BmobUser _user = BmobUser.getCurrentUser();
        if(_user!=null) {
            if (_user.getUsername() != null) {
                _unLogin.setVisibility(View.GONE);
                _heartName.setVisibility(View.VISIBLE);
                _heaterName.setText(_user.getUsername());
            }
        }else {
            _heartName.setVisibility(View.GONE);
           _unLogin.setVisibility(View.VISIBLE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_category,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.settings:
                Intent intent=new Intent(MainActivity.this, BmobActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                _drawerLayout.openDrawer(GravityCompat.START);
                break;
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

        TabMainViewPager(FragmentManager fragmentManager, List<Fragment> _fragmentList){
            super(fragmentManager);
            this.list=_fragmentList;
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