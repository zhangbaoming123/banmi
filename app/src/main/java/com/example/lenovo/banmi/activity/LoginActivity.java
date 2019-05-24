package com.example.lenovo.banmi.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.banmi.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_send_verif)
    Button btnSendVerif;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.um_wechat)
    ImageView umWechat;
    @BindView(R.id.um_qq)
    ImageView umQq;
    @BindView(R.id.um_weibo)
    ImageView umWeibo;
    @BindView(R.id.tv_name)
    TextView tvName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }



    private void initView() {
        btnSendVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ShuruActivity.class);
                startActivity(intent);
            }
        });

        /*if (!TextUtils.isEmpty(etPhone.getText().toString())){
            btnSendVerif.setBackgroundResource(R.drawable.button_no);
        }else{
            btnSendVerif.setBackgroundResource(R.drawable.button_yes);
        }*/

    }

    @OnClick({R.id.btn_send_verif, R.id.um_wechat, R.id.um_qq, R.id.um_weibo, R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_verif:
                break;
            case R.id.um_wechat:
                Intent intent = new Intent(LoginActivity.this,PhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.um_qq:
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        for (Map.Entry<String, String> entry:map.entrySet()){
                            String key = entry.getKey();
                            String value = entry.getValue();
                            Log.d(TAG, "key: "+key+",value:"+value);
                        }
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
            case R.id.um_weibo:
                break;
            case R.id.tv_name:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
