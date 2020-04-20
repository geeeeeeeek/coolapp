package com.geeeeeeeek.coolapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 7:43 PM
 */
public class ProgressView extends View {
    private Paint paint;
    private int w;
    private int h;
    private RectF rectF;
    private float currentAngle;

    private int round = dp2px(16);
    private float[] rids = {round, round, round, round, round, round, round, round, };

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;     //获取宽高
        this.h = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        w = getWidth();
        h = getHeight();

        rectF = new RectF(-w, -h, w * 2 , h * 2);

        Path path = new Path();
        path.addRoundRect(new RectF(0,0,w,h), rids, Path.Direction.CW);
        canvas.clipPath(path);

        canvas.drawArc(rectF, currentAngle - 90, 360 - currentAngle, true, paint);
        canvas.restore();
    }

    /**
     * 设置进度(百分比)
     * @param progress
     */
    public void setProgress(int progress){
        if(progress >= 0 && progress <= 100) {
            currentAngle = (360 * progress) / 100;
            invalidate();
        }
    }


    private int dp2px(final float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
