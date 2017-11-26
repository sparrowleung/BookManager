package com.example.administrator.myapplication.newsandtips;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/11/26.
 */

public class NewsTipsInformation extends BmobObject {

    String title;
    String subTitle;

    public void setTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public void setSubTitle(String subTitle){
        this.subTitle=subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }
}
