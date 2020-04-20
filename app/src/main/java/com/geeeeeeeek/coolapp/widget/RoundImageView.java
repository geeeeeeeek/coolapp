package com.geeeeeeeek.coolapp.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/18
 * Time: 10:37 AM
 */
public class RoundImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int round = dp2px(16);
    private float[] rids = {round, round, round, round, round, round, round, round, };

    public RoundImageView(Context context) {
        super(context);
    }


    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        path.addRoundRect(new RectF(0, 0, w, h), rids, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }

    public void setRound(int roundDp){
        this.round = dp2px(round);
        invalidate();
    }

    private int dp2px(final float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
