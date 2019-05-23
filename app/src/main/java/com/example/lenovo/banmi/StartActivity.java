package com.example.lenovo.banmi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.lenovo.banmi.activity.LoginActivity;
import com.example.lenovo.banmi.activity.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler(){
            public void handleMessage(android.os.Message msg) {
                Intent intent=new Intent(StartActivity.this,WelcomeActivity.class);
                startActivity(intent);
            }
        }.sendEmptyMessageDelayed(0, 3000);

    }
}
