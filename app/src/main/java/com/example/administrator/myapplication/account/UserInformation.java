package com.example.administrator.myapplication.account;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 37289 on 2017/11/20.
 */

public class UserInformation extends BmobUser {
    private String phoneNum;
    private String phoneNumStatus;
    private String part;
    private String teamgroup;
    private BmobFile image;

    public void setPhoneNum(String phoneNum){
        this.phoneNum=phoneNum;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNumStatus(String phoneNumStatus){
        this.phoneNumStatus=phoneNumStatus;
    }

    public String getPhoneNumStatus(){
        return  phoneNumStatus;
    }

    public void setPart(String part){
        this.part=part;
    }

    public String getPart(){
        return part;
    }

    public void setTeamgroup(String teamgroup){
        this.teamgroup=teamgroup;
    }

    public String getTeamgroup(){
        return teamgroup;
    }

    public void setImage(BmobFile image){
        this.image=image;
    }

    public BmobFile getImage(){
        return image;
    }
}
