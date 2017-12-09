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
    Boolean _status;

    public Category(BmobFile image,String name,String author,String press,Boolean status){
        this._image=image;
        this._name=name;
        this._author=author;
        this._press=press;
        this._status=status;
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

}
