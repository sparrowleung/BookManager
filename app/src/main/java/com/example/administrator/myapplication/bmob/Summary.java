package com.example.administrator.myapplication.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by 37289 on 2017/12/25.
 */

public class Summary extends BmobObject {

    private String table;
    private String change;

    public Summary(String objectId, String updatedAt,String table){
        this.table = table;
    }

    public Summary(){}

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
