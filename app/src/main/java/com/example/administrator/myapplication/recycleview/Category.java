package com.example.administrator.myapplication.recycleview;

import android.widget.ImageView;
import android.widget.TextView;

import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 37289 on 2017/11/17.
 */

public class Category {

    BmobFile _image;
    String _name;
    String _author;
    String _press;
    String _borrowper;
    String _category;
    Boolean _status;

    public Category(BmobFile image,String name,String author,String press,Boolean status,String borrowper,String category){
        this._image = image;
        this._name = name;
        this._author = author;
        this._press = press;
        this._status = status;
        this._borrowper = borrowper;
        this._category = category;
    }

    public BmobFile getImageId(){
        return _image;
    }

    public String getName(){
        return _name;
    }

    public String getAuthor(){
        return _author;
    }

    public String getPress(){
        return _press;
    }

    public Boolean getStatus(){
        return _status;
    }

    public String getBorrowper(){
        return _borrowper;
    }

    public String getCategory(){
        return _category;
    }

}
