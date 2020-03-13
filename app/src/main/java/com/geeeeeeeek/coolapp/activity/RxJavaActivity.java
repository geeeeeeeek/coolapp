package com.geeeeeeeek.coolapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.Observable;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:43 PM
 */
public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        use_just();
    }

    private void use_just() {
        Observable.just("hello", "world").subscribe(new Action1<String>(){

        });
    }


}
