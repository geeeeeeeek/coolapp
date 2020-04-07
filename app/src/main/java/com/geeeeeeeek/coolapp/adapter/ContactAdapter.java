package com.geeeeeeeek.coolapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.geeeeeeeek.coolapp.R;
import com.geeeeeeeek.coolapp.model.ContactBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class ContactAdapter extends BaseQuickAdapter<ContactBean, BaseViewHolder> {

    public ContactAdapter(int layoutResId, @Nullable List<ContactBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ContactBean contactBean) {
        if(contactBean != null){
            baseViewHolder.setText(R.id.tvCity, contactBean.getCity());
        }
    }
}
