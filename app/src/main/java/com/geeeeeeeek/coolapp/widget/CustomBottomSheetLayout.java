package com.geeeeeeeek.coolapp.widget;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.geeeeeeeek.coolapp.R;
import com.geeeeeeeek.coolapp.model.DialogBean;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CustomBottomSheetLayout extends BottomSheetDialog {
    private OnItemClickListener mOnItemClickListener;
    private LinearLayout contentView;
    private RecyclerView mRvList;
    private TextView mCancelView;
    private List<DialogBean> mData = new ArrayList<>();
    private DialogAdapter dialogAdapter;

    public CustomBottomSheetLayout(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_bottom_sheet);
        Objects.requireNonNull(getWindow()).findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        setCancelable(false);
        setCanceledOnTouchOutside(true);

        mRvList = findViewById(R.id.rv_list);
        mCancelView = findViewById(R.id.tv_cancel);
        dialogAdapter = new DialogAdapter(R.layout.item_bottom_dialog, mData);
        dialogAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(mData.get(position).tag);
            }
        });
        mRvList.setLayoutManager(new LinearLayoutManager(context));
        mRvList.setAdapter(dialogAdapter);
    }

    public CustomBottomSheetLayout setData(List<DialogBean> data){
        this.mData = data;
        dialogAdapter.addData(data);
        return this;
    }

    public CustomBottomSheetLayout setOnClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(String tag);
    }

    static class DialogAdapter extends BaseQuickAdapter<DialogBean, BaseViewHolder>{

        public DialogAdapter(int layoutResId, @Nullable List<DialogBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, DialogBean dialogBean) {
            if(dialogBean != null){
                baseViewHolder.setText(R.id.tv_title, dialogBean.title);
                baseViewHolder.setImageResource(R.id.iv_icon, dialogBean.resId);
            }
        }
    }

}
