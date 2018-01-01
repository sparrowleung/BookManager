package com.example.administrator.myapplication.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 37289 on 2017/12/25.
 */

public class Sammary extends BmobObject {

    private String table;
    private String change;

    public Sammary(String objectId, String updatedAt,String table){
        this.table = table;
    }

    public Sammary(){}

    public String getTable(){
        return table;
    }

    public void setTable(String table){
        this.table = table;
    }

    public String getChange(){
        return change;
    }

    public void setChange(String change){
        this.change = change;
    }
}
