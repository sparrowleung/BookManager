package com.example.administrator.myapplication.recycleview;

/**
 * Created by samsung on 2017/11/22.
 */

public class Advice {

    String _name;
    String _author;
    String _press;
    String _advicer;
    String _price;

    public Advice(String name,String author,String press,String price,String advicer){
        this._name=name;
        this._author=author;
        this._press=press;
        this._advicer=advicer;
        this._price=price;
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

    public String getAdvicer(){
        return _advicer;
    }

    public String getPrice(){return _price;}
}
