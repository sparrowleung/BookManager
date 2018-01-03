package com.example.administrator.myapplication.main;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;



/**
 * Created by 37289 on 2017/12/18.
 */

public class BookManageApplication extends Application {

    private static String _TAG = BookManageApplication.class.getSimpleName();

    private static BookManageApplication _manageApplication = null;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private ActivityLifecycleCallbacks _callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity != null) {
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (activity != null) {
            }
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();
        _manageApplication = this;
        registerActivityLifecycleCallbacks(_callbacks);
    }

    public BookManageApplication(){}

    public static BookManageApplication getManageApplication(){
        return _manageApplication;
    }

    @Override
    public void onTerminate(){
        mHandler.removeCallbacksAndMessages(null);
        unregisterActivityLifecycleCallbacks(_callbacks);
        super.onTerminate();
    }
}
