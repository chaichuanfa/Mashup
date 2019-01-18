package com.felix.common.base;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public abstract class BaseRecyclerAdapter<T extends Object>
        extends BaseQuickAdapter<T, BaseViewHolder> {

    public BaseRecyclerAdapter(int layoutResId,
            @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseRecyclerAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseRecyclerAdapter(int layoutResId) {
        super(layoutResId);
    }
}
