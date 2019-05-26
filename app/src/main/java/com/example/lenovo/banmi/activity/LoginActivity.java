package com.example.lenovo.banmi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.banmi.MainActivity;
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

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String, String> entry:data.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: "+key+",value:"+value);
            }
            Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(LoginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView qq = findViewById(R.id.um_qq);
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
       /* if (!TextUtils.isEmpty(etPhone.getText().toString())){
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
                UMShareAPI umShareAPI = UMShareAPI.get(this);
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, umAuthListener);
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
