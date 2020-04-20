package com.geeeeeeeek.coolapp.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.geeeeeeeek.coolapp.R;

import androidx.appcompat.widget.Toolbar;


public class CustomToolbar extends Toolbar {

    private TextView toolbarTitleTv;
    private TextView rightBtn;
    private LinearLayout mRootView;
    private ImageView mNavigationBackBtn;
    private TextView mBackTitle;
    private View mTranslucentStatusPlaceholderView;

    private String title;
    private String rightText;
    private int mBgResId, mTitleColor, mNavigationBackResId, mNavigationBackColor;
    private boolean showNavigationBack;
    private boolean showBottomLine;
    private boolean isTranslucentStatus;

    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar);
        title = typedArray.getString(R.styleable.CustomToolbar_title);
        rightText = typedArray.getString(R.styleable.CustomToolbar_right_text);
        mBgResId = typedArray.getResourceId(R.styleable.CustomToolbar_background, R.color.transparent);
        mTitleColor = typedArray.getColor(R.styleable.CustomToolbar_titleColor, getResources().getColor(R.color.textDark));
        mNavigationBackColor = typedArray.getColor(R.styleable.CustomToolbar_navigationBackColor, getResources().getColor(R.color.md_white_1000));
        showNavigationBack = typedArray.getBoolean(R.styleable.CustomToolbar_showNavigationBack, false);
        showBottomLine = typedArray.getBoolean(R.styleable.CustomToolbar_showBottomLine, false);
        isTranslucentStatus = typedArray.getBoolean(R.styleable.CustomToolbar_translucentStatus, false);
        mNavigationBackResId = typedArray.getResourceId(R.styleable.CustomToolbar_navigationBackIcon, R.mipmap.ic_back);
        typedArray.recycle();
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_toolbar, this, true);
        toolbarTitleTv = (TextView) findViewById(R.id.toolbar_title_tv);
        rightBtn = (TextView) findViewById(R.id.right_btn);
        rightBtn.setTextColor(mTitleColor);
        mRootView = (LinearLayout) findViewById(R.id.toolbar_custom);
        mNavigationBackBtn = (ImageView) findViewById(R.id.navigation_back_btn);
        mBackTitle = (TextView) findViewById(R.id.tv_left_title);
        mTranslucentStatusPlaceholderView = findViewById(R.id.placeholder_for_translucent);
        View bottomLine = findViewById(R.id.bottom_line);

        mRootView.setBackgroundResource(mBgResId);
        toolbarTitleTv.setText(title);
        toolbarTitleTv.setTextColor(mTitleColor);
        rightBtn.setText(rightText);
        if (showBottomLine) {
            bottomLine.setVisibility(VISIBLE);
        } else {
            bottomLine.setVisibility(GONE);
        }

        if (isTranslucentStatus) {
            mTranslucentStatusPlaceholderView.setVisibility(VISIBLE);
        } else {
            mTranslucentStatusPlaceholderView.setVisibility(GONE);
        }

        mNavigationBackBtn.setImageResource(mNavigationBackResId);
        setShowNavigationBack(showNavigationBack);
    }

    public void setShowNavigationBack(boolean showNavigationBack) {
        if (showNavigationBack) {
            mNavigationBackBtn.setVisibility(VISIBLE);
            mNavigationBackBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).onBackPressed();
                    }
                }
            });
        } else {
            mNavigationBackBtn.setVisibility(GONE);
        }
    }

    public void setShowBackTitle(boolean show){
        if(show){
            mBackTitle.setVisibility(VISIBLE);
            mBackTitle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).onBackPressed();
                    }
                }
            });
        }else{
            mBackTitle.setVisibility(GONE);
        }
    }

    public void setTitleText(CharSequence title) {
        toolbarTitleTv.setText(title);
    }

    public void setRightBtnText(CharSequence text) {
        rightBtn.setText(text);
    }

    public void setRightBtnOnClickListener(OnClickListener clickListener) {
        rightBtn.setOnClickListener(clickListener);
    }

    public void setRightBtnShow(boolean show) {
        rightBtn.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    public void setRightBtnEnable(boolean enable) {
        rightBtn.setEnabled(enable);
    }

}
