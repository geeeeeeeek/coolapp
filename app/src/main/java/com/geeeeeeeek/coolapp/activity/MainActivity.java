package com.geeeeeeeek.coolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geeeeeeeek.coolapp.R;
import com.geeeeeeeek.coolapp.base.BaseActivity;
import com.geeeeeeeek.coolapp.http.ApiClient;
import com.geeeeeeeek.coolapp.http.RequestBodyUtil;
import com.geeeeeeeek.coolapp.utils.CommonUtils;
import com.geeeeeeeek.coolapp.utils.DownloadProgressListener;
import com.geeeeeeeek.coolapp.widget.ProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:34 PM
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = "CoolApp";
    private Button btnRxjava;
    private Button btnJiankang;
    private Button btnUCrop;
    private EditText mEditStart;
    private EditText mEditEnd;
    private ProgressView progressView;
    private int progress = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRxjava = findViewById(R.id.btn_rxjava);
        btnJiankang = findViewById(R.id.btn_beijing);
        btnUCrop = findViewById(R.id.btn_ucrop);
        progressView = findViewById(R.id.pv_view);
        mEditStart = findViewById(R.id.et_start_date);
        mEditEnd = findViewById(R.id.et_end_date);
        setListeners();
//        testApi();
        testDownload();
        handler.sendEmptyMessageDelayed(1, 200);
    }

    private void setListeners() {
        btnRxjava.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CoolTabActivity.class)));
        btnJiankang.setOnClickListener(view -> {
            String startTime = mEditStart.getText().toString().trim();
            String endTime = mEditEnd.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, JiankangActivity.class);
            intent.putExtra("startTime", startTime);
            intent.putExtra("endTime", endTime);
            startActivity(intent);
        });
        btnUCrop.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CustomUCropActivity.class));
        });
    }

    private void testApi() {
        Map<String, Object> map = new HashMap<>();
        map.put("skipLockedFolders", true);
        map.put("desc", true);
        map.put("order", "date");
        map.put("start", 0);
        map.put("limit", 10);
        map.put("returnTotal", true);

        mDisposables.add(ApiClient.INSTANCE
                .getApiService()
                .listAttachments(RequestBodyUtil.getBody(map))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(baseData -> {
                    Toast.makeText(this, baseData.code, Toast.LENGTH_SHORT).show();
                    hideProgress();
                }, throwable -> {
                    hideProgress();
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                }, () -> Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show()));
    }

    private void testDownload() {
        String mid = "2:1tbiAgAFC14FotAA1gABsy";
        String encoding = "utf8";

        Call<ResponseBody> call = ApiClient.INSTANCE.getApiService()
                .downloadFile(mid, encoding);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    File zipFile = new File(getExternalCacheDir() + File.separator + "abc.zip");
                    CommonUtils.writeResponseBodyToDisk(response.body(), zipFile, downloadProgressListener);

                    File outFile = new File(zipFile.getParent() + File.separator + "abc.jpg");
                    CommonUtils.unzip(zipFile, outFile);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    DownloadProgressListener downloadProgressListener = new DownloadProgressListener() {
        @Override
        public void onProgress(long current, long totalSize) {
            // 下载进度
            Log.w(TAG, "current=" + current + " totalSize=" + totalSize);
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressView.setProgress(progress++);
            sendEmptyMessageDelayed(1, 200);
        }
    };

}
