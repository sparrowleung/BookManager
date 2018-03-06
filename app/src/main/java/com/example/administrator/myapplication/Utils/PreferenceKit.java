package com.example.administrator.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by 37289 on 2018/2/28.
 */

public class PreferenceKit {

    public static SharedPreferences mSharedPreferences;
    public static PreferenceKit mPreferenceKit;
    public static Object mObject = new Object();

    public PreferenceKit(Context context,String fileName){
        mSharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static PreferenceKit getPreference(Context context, String fileName){
        synchronized (mObject){
            if(mPreferenceKit != null){
                mPreferenceKit = new PreferenceKit(context,fileName);
            }
        }
        return mPreferenceKit;
    }


    public <T> void put(String key, T value){
        SharedPreferences.Editor mEdit = mSharedPreferences.edit();
        put(mEdit, key, value);
        mEdit.apply();
    }

    public <T> void putAll(Map<String, T> map){
        SharedPreferences.Editor mEdit = mSharedPreferences.edit();
        for(Map.Entry<String, T> entry : map.entrySet()){
            String key = entry.getKey();
            Object object = entry.getValue();
            put(mEdit, key, object);
        }
        mEdit.apply();
    }

    public void put(String key, List<String> list){
        putAll(key, list, new ComparatorImpl());
    }

    public void putAll(String key, List<String> list, ComparatorImpl comparator){
        Set<String> mSet = new TreeSet<>(comparator);
        mSet.addAll(list);
        mSharedPreferences.edit().putStringSet(key, mSet).apply();
    }

    public <T> T get(String key, DataType dataType){
        return (T) getValue(key, dataType);
    }

    public Map<String, ?> getAll(){
        return mSharedPreferences.getAll();
    }

    public List<String> getAll(String key){
        List<String> mList = new ArrayList<>();
        Set<String> mSet = get(key, DataType.STRING_SET);
        if(mSet != null){
            mList.addAll(mSet);
            return mList;
        }else {
            return null;
        }
    }

    public void remove(String key){
        mSharedPreferences.edit().remove(key).apply();
    }

    public void removeAll(List<String> key){
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        for(String k : key){
            mEditor.remove(k);
        }
        mEditor.apply();
    }



    public static class ComparatorImpl implements Comparator<String>{
        @Override
        public int compare(String lhs, String rhs){
            return lhs.compareTo(rhs);
        }
    }

    public Object getValue(String key, DataType dataType){
        switch (dataType){
            case INTEGER:
                return mSharedPreferences.getInt(key, -1);
            case FLOAT:
                return mSharedPreferences.getFloat(String, -1f);
            case LONG:
                return mSharedPreferences.getLong(key, -1L);
            case STRING:
                return mSharedPreferences.getString(key, null);
            case BOOLEAN:
                return mSharedPreferences.getBoolean(key, false);
            case STRING_SET:
                return mSharedPreferences.getStringSet(key, null);
            default:
                    return null;
        }
    }

    public void put(SharedPreferences.Editor editor, String key, Object object){
        if(key != null){
            if( object instanceof Integer){
                editor.putInt(key, (Integer) object);
            }else if (object instanceof Long){
                editor.putLong(key, (Long) object);
            }else if(object instanceof Boolean){
                editor.putBoolean(key, (Boolean) object);
            }else if(object instanceof Float){
                editor.putFloat(key, (Float)object);
            }else if(object instanceof String){
                editor.putString(key, String.valueOf(object));
            }else if(object instanceof Set){
                editor.putStringSet(key, (Set<String>) object);
            }
        }
    }

    enum DataType{
        INTEGER, FLOAT, LONG, BOOLEAN, STRING, STRING_SET;
    }

}
