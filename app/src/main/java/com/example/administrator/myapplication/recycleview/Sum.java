package com.example.administrator.myapplication.recycleview;

/**
 * Created by samsung on 2018/1/2.
 */

public class Sum {

    String table;
    String change;
    String mObjectId;
    String mUpdatedAt;

    public Sum(String objectId, String updatedAt, String table){
        this.table = table;
        this.mObjectId = objectId;
        this.mUpdatedAt = updatedAt;
    }

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

    public String getmObjectId(){
        return mObjectId;
    }

    public String getmUpdatedAt(){
        return mUpdatedAt;
    }
}
