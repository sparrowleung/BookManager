package com.example.administrator.myapplication.recycleview;

/**
 * Created by Administrator on 2017/11/26.
 */

public class News {

    String mTitle;
    String mSubTitle;

    public News(String title,String subTitle){
        this.mSubTitle=subTitle;
        this.mTitle=title;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getSubTitle(){
        return mSubTitle;
    }

    @Override
    public String toString() {
        return "News=[mTitle=" + mTitle + ",mSubTitle=" + mSubTitle + "]";
    }
}
