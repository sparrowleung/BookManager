package com.example.administrator.myapplication.recycleview;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 37289 on 2017/11/11.
 */

public class Book {

    String bookName;
    BmobFile bookViewId;

    public Book(String bookName, BmobFile bookViewId){
        this.bookName=bookName;
        this.bookViewId=bookViewId;
    }

    public String getBookName(){
        return bookName;
    }

    public BmobFile getBookViewId(){
        return bookViewId;
    }

}
