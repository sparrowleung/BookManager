package com.example.administrator.myapplication.recycleview;

/**
 * Created by 37289 on 2017/11/11.
 */

public class Book {

    String bookName;
    int bookViewId;

    public Book(String bookName, int bookViewId){
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
