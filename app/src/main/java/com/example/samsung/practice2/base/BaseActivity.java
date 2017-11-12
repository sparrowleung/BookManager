package com.example.samsung.practice2.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.samsung.practice2.R;

/**
 * Created by samsung on 2017/10/30.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstancedState){
        super.onCreate(saveInstancedState);
    }

    public void upDateActionBar(){
        View DecorView=getWindow().getDecorView();
        DecorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.WHITE);
    }


    public void attachFragmentAsSingle(android.support.v4.app.Fragment fragment){
        if(fragment == null){
            return;
        }

        android.support.v4.app.FragmentManager fragmentManager =getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

}
