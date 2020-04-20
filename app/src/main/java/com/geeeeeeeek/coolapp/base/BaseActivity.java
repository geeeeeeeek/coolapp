package com.geeeeeeeek.coolapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/15
 * Time: 3:19 PM
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mDialog;
    public CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgress(){
        if (mDialog == null){
            mDialog = new ProgressDialog(this);
        }
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("");
        mDialog.setCancelable(true);
        mDialog.show();
    }

    public void hideProgress(){
        if (mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
