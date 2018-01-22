package com.example.administrator.myapplication.Utils;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by 37289 on 2018/1/8.
 */

public class BmobRequest {

    public BmobRequest(){}

    public static synchronized BmobRequest instance(){
        return new BmobRequest();
    }

    public static <E> void findRequest(@NonNull onFindResultsListener<E> call){
        BmobQuery<E> query = new BmobQuery<>();
        query.order("index").findObjects(call);
    }

    public static <E> void findRequest(@NonNull String order,
                                       @NonNull onFindResultsListener<E> call){
        BmobQuery<E> query = new BmobQuery<>();
        query.order(order).findObjects(call);
    }

    public static <E> void findRequest(@NonNull String order,
                                       @NonNull HashMap<String, Object> map,
                                       @NonNull onFindResultsListener<E> call){
        BmobQuery<E> query = new BmobQuery<>();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            query.addWhereEqualTo(entry.getKey(), entry.getValue());
        }
        query.order(order).findObjects(call);
    }

    public static <E> void queryObject(@NonNull String objectId,
                                       @NonNull onQueryObjectListener<E> listener){
        BmobQuery<E> query =new BmobQuery<>();
        query.getObject(objectId,listener);
    }

    public static <E> void queryObject(@NonNull String objectId,
                                       @NonNull HashMap<String ,Object> map,
                                       @NonNull onQueryObjectListener<E> listener){
        BmobQuery<E> query = new BmobQuery<>();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            query.addWhereEqualTo(entry.getKey(), entry.getValue());
        }
        query.getObject(objectId,listener);
    }

    public static <E extends BmobObject> void saveObject(@NonNull E obj,
                                                         @NonNull final onSaveObjectListener listener){
        obj.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    listener.onSuccess(s);
                }else {
                    listener.onFail(e.getErrorCode(), e.getMessage());
                }
            }
        });
    }

    public static <E extends BmobObject> void updateObject(@NonNull E obj,
                                                           @NonNull final String objectId,
                                                           @NonNull final onUpdateObjectListener listener){
        obj.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    listener.onSuccess(objectId);
                }else {
                    listener.onFail(e.getErrorCode(), e.getMessage());
                }
            }
        });
    }

    public static <E extends BmobObject> void updateObjectByValues(@NonNull E obj,
                                                                   @NonNull final String objectId,
                                                                   @NonNull HashMap<String, Object> map,
                                                                   @NonNull final  onUpdateObjectListener listener){
        for(Map.Entry<String, Object> entry : map.entrySet()){
            obj.setValue(entry.getKey(), entry.getValue());
        }

        obj.update(objectId,new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    listener.onSuccess(objectId);
                }else {
                    listener.onFail(e.getErrorCode(), e.getMessage());
                }
            }
        });
    }

    public static <E extends BmobObject> void deleteObject(@NonNull E obj,
                                                           @NonNull final String objectId,
                                                           @NonNull final onUpdateObjectListener listener){
        obj.delete(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    listener.onSuccess(objectId);
                }else {
                    listener.onFail(e.getErrorCode(), e.getMessage());
                }
            }
        });
    }

    public static <E extends BmobObject> void deleteObjectByKey(@NonNull E obj,
                                                                @NonNull final String objectId,
                                                                @NonNull String key,
                                                                @NonNull final onUpdateObjectListener listener){
        obj.setObjectId(objectId);
        obj.remove(key);
        obj.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    listener.onSuccess(objectId);
                }else {
                    listener.onFail(e.getErrorCode(), e.getMessage());
                }
            }
        });
    }
}
