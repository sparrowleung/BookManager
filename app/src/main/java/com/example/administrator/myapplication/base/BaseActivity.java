package com.example.administrator.myapplication.base;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/10/30.
 */

public class BaseActivity extends AppCompatActivity {

    protected String TAG;

    @Override
    protected void onCreate(Bundle saveInstancedState){
        super.onCreate(saveInstancedState);
        TAG = this.getClass().getSimpleName();
    }

    public void upDateActionBar(){
        View DecorView=getWindow().getDecorView();
        DecorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.WHITE);
    }


    public void attachFragmentAsSingle(Fragment fragment){
        if(fragment == null){
            return;
        }

        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
}