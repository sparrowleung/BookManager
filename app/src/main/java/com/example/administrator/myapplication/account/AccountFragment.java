package com.example.administrator.myapplication.account;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.base.BaseFragment;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.b.V;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

/**
 * Created by samsung on 2017/11/16.
 */

public class AccountFragment extends BaseFragment {

    private View _rootView;
    private View mCommit;

    private CardView mCardView1;
    private CardView mCardView2;
    private CardView mCardView3;
    private CardView mCardView4;

    private ImageView mImageView;

    private EditText mEditPassword;
    private EditText mEditNickName;
    private EditText mEditPart;
    private EditText mEditTg;
    private TextView mNickName;
    private TextView mNumbers;

    private String _nickName;
    private String _password;
    private String _part;
    private String _teamGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        _rootView=inflater.inflate(R.layout.fragment_account,container,false);
        return _rootView;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        init();
    }

    public void init(){
        mCardView1=(CardView) getActivity().findViewById(R.id.account_card1);
        mCardView2=(CardView) getActivity().findViewById(R.id.account_card2);
        mCardView3=(CardView) getActivity().findViewById(R.id.account_card3);
        mCardView4=(CardView) getActivity().findViewById(R.id.account_card4);
        mCommit=(View) getActivity().findViewById(R.id.account_commit);

        mImageView=(ImageView) getActivity().findViewById(R.id.account_image);

        mEditPassword=(EditText) getActivity().findViewById(R.id.account_passwrod);
        mEditNickName=(EditText) getActivity().findViewById(R.id.account_nickname);
        mEditPart=(EditText) getActivity().findViewById(R.id.account_part);
        mEditTg=(EditText) getActivity().findViewById(R.id.account_tg);
        mNickName=(TextView) getActivity().findViewById(R.id.account_account);
        mNumbers=(TextView) getActivity().findViewById(R.id.account_number);

        BmobUser _user=BmobUser.getCurrentUser();
        mNickName.setText(_user.getUsername());
        mNumbers.setText(_user.getMobilePhoneNumber());

        mCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCardView3.getVisibility() == View.GONE){
                    mCardView3.setVisibility(View.VISIBLE);
                }else {
                    mCardView3.setVisibility(View.GONE);
                }
            }
        });


        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _nickName=mEditNickName.getText().toString();
                _password=mEditPassword.getText().toString();
                _part=mEditPart.getText().toString();
                _teamGroup=mEditTg.getText().toString();
                UserInformation _user=new UserInformation();
                _user.setUsername(_nickName);
                _user.setPassword(_password);
                _user.setPart(_part);
                _user.setTeamgroup(_teamGroup);
                _user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(getContext(),"更新信息成功",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(),"更新信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder _builder=new AlertDialog.Builder(getContext());
                _builder.setTitle("提示");
                _builder.setMessage("是否确定退出当前账号");
                _builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BmobUser.logOut();
                        Intent intent = getContext().getPackageManager()
                                .getLaunchIntentForPackage(getContext().getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
                _builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                _builder.create();
                _builder.show();
            }
        });

    }
}
