package com.geeeeeeeek.coolapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.geeeeeeeek.coolapp.R;
import com.geeeeeeeek.coolapp.model.Student;
import com.geeeeeeeek.coolapp.utils.CommonUtils;
import com.geeeeeeeek.coolapp.utils.MockDataUtil;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:43 PM
 */
public class JiankangActivity extends AppCompatActivity {
    public static final String TAG = "RxJavaActivity";
    private Disposable intervalDisposable;
    private int i = 0;

    private ImageView mImageOne;
    private ImageView mImageTwo;
    private TextView mTime;
    private TextView mStartTime;
    private TextView mEndTime;

    private String startTime;
    private String endTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiankang);
        mImageOne = findViewById(R.id.iv_one);
        mImageTwo = findViewById(R.id.iv_two);
        mStartTime = findViewById(R.id.tv_start_time);
        mEndTime = findViewById(R.id.tv_end_time);
        mTime = findViewById(R.id.tv_time);

        startTime = getIntent().getStringExtra("startTime");
        endTime = getIntent().getStringExtra("endTime");

        mStartTime.setText(startTime);
        mEndTime.setText(endTime);

        mHandler.sendEmptyMessageDelayed(1, 100);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intervalDisposable != null) {
            intervalDisposable.dispose();
        }
    }

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            i++;
            if(i % 2 == 0){
                mImageOne.setVisibility(View.VISIBLE);
                mImageTwo.setVisibility(View.INVISIBLE);
            }else{
                mImageOne.setVisibility(View.INVISIBLE);
                mImageTwo.setVisibility(View.VISIBLE);
            }
            String dateString = CommonUtils.getDateToString(System.currentTimeMillis(), "yyyy年MM月dd日");
            String timeString = CommonUtils.getDateToString(System.currentTimeMillis(), "HH:mm:ss");
            mTime.setText(dateString + "\n" + timeString);
            sendEmptyMessageDelayed(1, 500);
        }
    };
}
