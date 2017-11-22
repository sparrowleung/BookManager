package com.example.administrator.myapplication.advice;

import cn.bmob.v3.BmobObject;

/**
 * Created by samsung on 2017/11/22.
 */

public class AdviceInformation extends BmobObject {

    private String bookName;
    private String author;
    private String press;
    private String price;
    private String reason;
    private String advicer;

    public String getBookName(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName=bookName;
    }

    public String getAuthor(){
        return  author;
    }

    public void setAuthor(String author){
        this.author=author;
    }

    public String getPress(){
        return press;
    }

    public void setPress(String press){
        this.press=press;
    }

    public String getPrice(){
        return  price;
    }

    public void setPrice(String price){
        this.price=price;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason=reason;
    }

    public String getAdvicer(){
        return  advicer;
    }

    public void setAdvicer(String advicer){
        this.advicer=advicer;
    }


}
