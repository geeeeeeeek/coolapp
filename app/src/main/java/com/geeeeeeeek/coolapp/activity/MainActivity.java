package com.geeeeeeeek.coolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geeeeeeeek.coolapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:34 PM
 */
public class MainActivity extends AppCompatActivity {
    private Button btnRxjava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRxjava = findViewById(R.id.btn_rxjava);
        setListeners();

        startActivity(new Intent(this, CoolTabActivity.class));
    }

    private void setListeners() {
        btnRxjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(intent);
            }
        });
    }
}
