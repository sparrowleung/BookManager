package com.example.administrator.myapplication.bmob;


import java.util.Date;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by samsung on 2017/11/20.
 */

public class BookInformation extends BmobObject {

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

    public Double getPrice(){
        return  price;
    }

    public void setPrice(Double price){
        this.price=price;
    }

    public Boolean  getState(){
        return state;
    }

    public void setState(Boolean  state){
        this.state=state;
    }

    public Date getBorrowtime(){
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime){
        this.borrowtime=borrowtime;
    }

    public Date getBacktime(){
        return backtime;
    }

    public void setBacktime(Date backtime){
        this.backtime=backtime;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public String getBorrowper(){
        return borrowper;
    }

    public void setBorrowper(String borrowper){
        this.borrowper=borrowper;
    }

    public int getBorrowcount(){
        return borrowcount;
    }

    public void setBorrowcount(int borrowcount){
        this.borrowcount=borrowcount;
    }

    public BmobFile getPhoto(){
        return photo;
    }

    public void setPhoto(BmobFile photo){
        this.photo=photo;
    }
}
