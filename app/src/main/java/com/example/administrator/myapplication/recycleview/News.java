package com.example.administrator.myapplication.recycleview;

/**
 * Created by Administrator on 2017/11/26.
 */

public class News {

    String mTitle;
    String mSubTitle;
    String mCreatedAt;

    public News(String title,String subTitle, String createdAt){
        this.mSubTitle = subTitle;
        this.mTitle = title;
        this.mCreatedAt = createdAt;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getSubTitle(){
        return mSubTitle;
    }

    public String getCreatedAt(){
        return mCreatedAt;
    }

    @Override
    public String toString() {
        return "News=[mTitle=" + mTitle + ",mSubTitle=" + mSubTitle + "]";
    }
}
