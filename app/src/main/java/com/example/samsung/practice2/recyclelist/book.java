package com.example.samsung.practice2.recyclelist;

import android.widget.ImageView;

/**
 * Created by samsung on 2017/7/31.
 */

public class book {

    String bookName;
    int bookViewId;

    public book(String bookName,int bookViewId){
        this.bookName=bookName;
        this.bookViewId=bookViewId;
    }

    public String getBookName(){
        return bookName;
    }

    public int getBookViewId(){
        return bookViewId;
    }

}
