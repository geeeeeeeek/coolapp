package com.geeeeeeeek.coolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.geeeeeeeek.coolapp.R;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:34 PM
 */
public class MainActivity extends AppCompatActivity {
    private TextView tvRxjava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRxjava = findViewById(R.id.tv_rxjava);
        setListeners();
    }

    private void setListeners() {
        tvRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent);
            }
        });
    }
}
