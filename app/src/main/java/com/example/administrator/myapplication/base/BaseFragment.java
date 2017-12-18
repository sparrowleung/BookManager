package com.example.administrator.myapplication.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import com.example.administrator.myapplication.bmob.AdviceInformation;
import com.example.administrator.myapplication.bmob.BookInformation;
import com.example.administrator.myapplication.bmob.NewsTipsInformation;
import com.example.administrator.myapplication.category.TechnologyFragment;
import com.example.administrator.myapplication.main.BookManageApplication;
import com.example.administrator.myapplication.main.MainActivity;
import com.example.administrator.myapplication.recycleview.Category;
import com.google.gson.Gson;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/30.
 */

public abstract class BaseFragment extends Fragment {


    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected String TAG ;

    @CallSuper
    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        TAG = this.getClass().getSimpleName();
    }

    public SharedPreferences.Editor _editor;
    public SharedPreferences _preferences;
    public List<String> _save;
    public Set<String> _set;
    public Gson _gson;

    public void InitSharePreferences(String FileName){
        _save = new ArrayList<>();
        _editor = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE).edit();
        _preferences = getContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        _set = _preferences.getStringSet(FileName, null);
        _gson = new Gson();
    }

    public static class ComparatorImpl implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    public static boolean NetworkAvailale(){
        Context _context = BookManageApplication.getManageApplication();
        try{
            ConnectivityManager _manager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);

            return _manager != null && _manager.getActiveNetworkInfo() != null && _manager.getActiveNetworkInfo().isConnected();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
