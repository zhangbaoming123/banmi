package com.example.lenovo.banmi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.banmi.MainActivity;
import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.utils.CountDownTimerUtils;
import com.tuo.customview.VerificationCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShuruActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_send)
    TextView tvCountDown;
    @BindView(R.id.icv)
    VerificationCodeView icv;

    public static void start(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuru);
        ButterKnife.bind(this);
        startCountDown();
        initView();
    }

    private void initView() {
        //监听验证码输入完成
        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                if (icv.getInputContent().length() == 4) {
                    Intent intent = new Intent(ShuruActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void deleteContent() {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startCountDown() {
        new CountDownTimerUtils(tvCountDown, 60000, 1000);
    }

    @OnClick({R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                Toast.makeText(this, "重新发送验证码", Toast.LENGTH_SHORT).show();
                startCountDown();
                break;
        }
    }
}
