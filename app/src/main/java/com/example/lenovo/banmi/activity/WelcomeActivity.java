package com.example.lenovo.banmi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.banmi.MainActivity;
import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.ViewpagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        View t1 = LayoutInflater.from(this).inflate(R.layout.viewpager_item, null);
        View t2 = LayoutInflater.from(this).inflate(R.layout.viewpager_item1, null);
        View t3 = LayoutInflater.from(this).inflate(R.layout.viewpager_item2, null);
        ArrayList<View> list=new ArrayList<>();
        list.add(t1);
        list.add(t2);
        list.add(t3);
        ViewpagerAdapter adapter = new ViewpagerAdapter(list);

        vp.setAdapter(adapter);

        Button bt = (Button) t3.findViewById(R.id.viewpager_btn);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        boolean bl = sp.getBoolean("bl", false);
        if (bl) {
            Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putBoolean("bl", true);
                edit.commit();
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
