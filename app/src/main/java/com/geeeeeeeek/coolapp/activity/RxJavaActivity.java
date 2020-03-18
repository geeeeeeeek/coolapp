package com.geeeeeeeek.coolapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.geeeeeeeek.coolapp.model.Student;
import com.geeeeeeeek.coolapp.utils.MockDataUtil;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by XiaoQingsong
 * Date: 2020/3/13
 * Time: 2:43 PM
 */
public class RxJavaActivity extends AppCompatActivity {
    public static final String TAG = "RxJavaActivity";
    private Disposable intervalDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        use_just();
//        use_map();
//        use_flatmap();
//        use_interval();
//        use_scan();
        use_reduce();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intervalDisposable != null) {
            intervalDisposable.dispose();
        }
    }

    private void use_just() {
        Observable.just("hello", "world")
                .subscribe(s -> Log.w(TAG, "==>" + s));

    }

    private void use_map() {
        Observable.just("hello", "world", "beijing")
                .map(s -> s.length())
                .map(integer -> integer.toString())
                .subscribe(s -> Log.w(TAG, "===>" + s));
    }

    private void use_flatmap() {
        Flowable.fromIterable(MockDataUtil.getStudents())
                .flatMap((Function<Student, Publisher<Student.Course>>) student -> Flowable.fromIterable(student.courses))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(course -> Log.w(TAG, "==>" + course.couseName + ":" + course.score));
    }

    private void use_interval() {
        intervalDisposable = Observable
                .interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(aLong -> Log.w(TAG, "===>" + aLong));
    }

    private void use_scan() {
        Observable.range(0, 5)
                .scan((integer, integer2) -> {
                    Log.w(TAG, "integer+integer2==>" + (integer + integer2));
                    return integer + integer2;
                })
                .subscribe(integer -> Log.w(TAG, "integer==>" + integer));
    }

    private void use_reduce(){
        Observable.range(0, 5)
                .reduce((integer, integer2) -> {
                    Log.w(TAG, "integer+integer2==>" + (integer + integer2));
                    return integer + integer2;
                })
                .subscribe(integer -> Log.w(TAG, "integer==>" + integer));
    }
}
