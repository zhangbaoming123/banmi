package com.example.lenovo.banmi.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.banmi.MainActivity;
import com.example.lenovo.banmi.MainActivity;
import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.base.Constants;
import com.example.lenovo.banmi.utils.SpUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.loader.ImageLoader;

import java.util.Map;
import java.util.Set;

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

            Set<String> keySet = data.keySet();
//得到头像
            String iconurl = new String();
//得到昵称
            String screenname = new String();
            for (String string : keySet) {
                Log.i("TAG", string);
                if (string.equals("screen_name")) {
//获取登录的名字
                    screenname = data.get("screen_name");
//                    SpUtil.setParam(screenname,Constants.DATA);
//                    name.setText(screenname);
                }
                if (string.equals("profile_image_url")) {
//获取登录的图片
                    iconurl = data.get("profile_image_url");
//                    SpUtil.setParam(iconurl,Constants.DATA);
                    /*RequestOptions options = new RequestOptions().circleCrop();
                    Glide.with(LoginActivity.this).load(iconurl).apply(options).into(img);*/
                }

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("name", screenname);
                intent.putExtra("img", iconurl);
                startActivity(intent);
                Toast.makeText(LoginActivity.this, "成功了", Toast.LENGTH_LONG).show();
            }
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(LoginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
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
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnSendVerif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = etPhone.getText().toString();
                if (s.matches("^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "手机号错误，请重新输入", Toast.LENGTH_LONG).show();
                }
            }
        });

        SharedPreferences sp = getSharedPreferences("user1", MODE_PRIVATE);
        boolean bl = sp.getBoolean("bll", false);
        if (bl) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if (!TextUtils.isEmpty(etPhone.getText().toString())) {
            btnSendVerif.setBackgroundResource(R.drawable.button_no);
        } else {
            btnSendVerif.setBackgroundResource(R.drawable.button_yes);
        }
    }

        @OnClick({R.id.btn_send_verif, R.id.um_wechat, R.id.um_qq, R.id.um_weibo, R.id.tv_name})
        public void onViewClicked (View view){
            switch (view.getId()) {
                case R.id.btn_send_verif:
                    break;
                case R.id.um_wechat:
                    Intent intent = new Intent(LoginActivity.this, PhoneActivity.class);
                    startActivity(intent);
                    break;
                case R.id.um_qq:
                    SharedPreferences sp = getSharedPreferences("user1", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("bll", true);
                    edit.commit();
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
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        }
    }

