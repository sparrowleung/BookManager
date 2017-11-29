package com.example.administrator.myapplication.account;

import android.Manifest;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.b.V;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static android.app.Activity.RESULT_OK;

/**
 * Created by samsung on 2017/11/16.
 */

public class AccountFragment extends BaseFragment {

    private View _rootView;
    private View mCommit;
    private View mExchange;

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
    private String mImagePath;
    private Uri mUri;

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
        mExchange=(View) getActivity().findViewById(R.id.account_changeIamge);

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

        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else {
                    Intent _intent = new Intent("android.intent.action.GET_CONTENT");
                    _intent.setType("image/*");
                    startActivityForResult(_intent, 2);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 2:
                if(resultCode == RESULT_OK) {
                    mUri = data.getData();
                    if (DocumentsContract.isDocumentUri(getContext(), mUri)) {
                        String docId = DocumentsContract.getDocumentId(mUri);
                        if ("com.android.providers.media.documents".equals(mUri.getAuthority())) {
                            String _id = docId.split(":")[1];
                            String _selection = MediaStore.Images.Media._ID + "=" + _id;
                            mImagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, _selection);
                        } else if ("com.android.providers.downloads.documents".equals(mUri.getAuthority())) {
                            Uri _uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                            mImagePath = getImagePath(_uri, null);
                        }
                    } else if ("content".equalsIgnoreCase(mUri.getScheme())) {
                        mImagePath = getImagePath(mUri, null);
                    } else if ("file".equalsIgnoreCase(mUri.getScheme())) {
                        mImagePath = mUri.getPath();
                    }

                    if (mImagePath != null) {
                        Bitmap _bitmap = BitmapFactory.decodeFile(mImagePath);
                        mImageView.setImageBitmap(_bitmap);
                    }

                    BmobUser _bUser = BmobUser.getCurrentUser();
                    BmobFile _file = new BmobFile(_bUser.getUsername()+"'s photo",null,new File(mImagePath).toString());
                    UserInformation _user = new UserInformation();
                    _user.setImage(_file);
                    _user.update(_bUser.getObjectId(),new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e == null){
                                Toast.makeText(getContext(),"更换头像成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;
                }
        }
    }

    public String getImagePath(Uri uri,String selection){
        String _path = null;
        Cursor _cursor = getContext().getContentResolver().query(uri,null,selection,null,null);
        if(_cursor != null){
            if(_cursor.moveToFirst()){
                _path = _cursor.getString(_cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            _cursor.close();
        }
        return _path;
    }
}
