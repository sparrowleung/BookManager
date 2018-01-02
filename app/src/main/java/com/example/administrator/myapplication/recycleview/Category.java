package com.example.administrator.myapplication.recycleview;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 37289 on 2017/11/17.
 */

public class Category {

    private String name;
    private String author;
    private int borrowcount;
    private String press;
    private Double price;
    private Boolean state;
    private Date borrowtime;
    private Date backtime;
    private String category;
    private String borrowper;
    private BmobFile photo;
    private String _objectId;
    private String _createdAt;

    public Category(String objectId, String createdAt, String name, String author, int borrowcount,
                    String press, double price, Boolean state, String category, String borrowper, BmobFile photo,
                    Date borrowtime, Date backtime){
        this._objectId = objectId;
        this._createdAt = createdAt;
        this.name = name;
        this.author = author;
        this.borrowcount = borrowcount;
        this.press = press;
        this.price = price;
        this.state = state;
        this.category = category;
        this.borrowper = borrowper;
        this.photo = photo;
        this.borrowtime = borrowtime;
        this.backtime = backtime;
    }

    public String get_objectId(){
        return _objectId;
    }

    public String get_createdAt(){
        return _createdAt;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return  author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getPress(){
        return press;
    }

    public void setPress(String press){
        this.press = press;
    }

    public Double getPrice(){
        return  price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public Boolean  getState(){
        return state;
    }

    public void setState(Boolean  state){
        this.state = state;
    }

    public Date getBorrowtime(){
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime){
        this.borrowtime = borrowtime;
    }

    public Date getBacktime(){
        return backtime;
    }

    public void setBacktime(Date backtime){
        this.backtime = backtime;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getBorrowper(){
        return borrowper;
    }

    public void setBorrowper(String borrowper){
        this.borrowper = borrowper;
    }

    public int getBorrowcount(){
        return borrowcount;
    }

    public void setBorrowcount(int borrowcount){
        this.borrowcount = borrowcount;
    }

    public BmobFile getPhoto(){
        return photo;
    }

    public void setPhoto(BmobFile photo){
        this.photo = photo;
    }

}
