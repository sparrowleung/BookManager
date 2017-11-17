package com.example.administrator.myapplication.recycleview;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 37289 on 2017/11/17.
 */

public class Category {
    int _image;
    String _name;
    String _author;
    String _press;
    String _status;

    public Category(int image,String name,String author,String press,String status){
        this._image=image;
        this._name=name;
        this._author=author;
        this._press=press;
        this._status=status;
    }

    public int getImageId(){
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

    public String getStatus(){
        return _status;
    }

}
