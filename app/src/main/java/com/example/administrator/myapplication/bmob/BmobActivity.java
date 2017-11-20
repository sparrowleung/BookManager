package com.example.administrator.myapplication.bmob;

import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseActivity;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by samsung on 2017/11/20.
 */

public class BmobActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_bmob);
        final BookInformation object=new BookInformation();
        object.setName("钢铁是怎样炼成的");
        object.setAuthor("Zone-Ball");
        object.setPress("中国商务出版社");
        object.setPrice("43.98");
        object.setState("Non");
        object.setBorrowtime("Non");
        object.setBacktime("Non");
        object.setCategory("technology");
        object.save(new SaveListener<String>(){
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(getBaseContext(),"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(),"创建数据失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
