package com.example.administrator.myapplication.bmob;

import android.os.Bundle;

import com.example.administrator.myapplication.base.BaseActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by samsung on 2017/11/20.
 */

public class BookInformation extends BmobObject {

    private String name;
    private String author;
    private String press;
    private String price;
    private String state;
    private String borrowtime;
    private String backtime;
    private String category;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
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

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state=state;
    }

    public String getBorrowtime(){
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime){
        this.borrowtime=borrowtime;
    }

    public String getBacktime(){
        return backtime;
    }

    public void setBacktime(String backtime){
        this.backtime=backtime;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }
}
