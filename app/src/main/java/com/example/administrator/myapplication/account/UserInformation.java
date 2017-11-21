package com.example.administrator.myapplication.account;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 37289 on 2017/11/20.
 */

public class UserInformation extends BmobUser {


    private String part;
    private String teamGroup;
    private BmobFile image;


    public void setPart(String part){
        this.part=part;
    }

    public String getPart(){
        return part;
    }

    public void setTeamgroup(String teamGroup){
        this.teamGroup=teamGroup;
    }

    public String getTeamgroup(){
        return teamGroup;
    }

    public void setImage(BmobFile image){
        this.image=image;
    }

    public BmobFile getImage(){
        return image;
    }
}
