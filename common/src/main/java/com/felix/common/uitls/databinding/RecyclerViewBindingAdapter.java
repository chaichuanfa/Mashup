package com.felix.common.uitls.databinding;

import com.chad.library.adapter.base.BaseQuickAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"android:onItemClick", "android:onLoadMore",
            "android:loadMoreEnable", "android:itemLoadAnimation"}, requireAll = false)
    public static void setupAdapter(RecyclerView recyclerView,
            final ItemClickListener itemClickListener,
            final LoadMoreListener loadMoreListener,
            final boolean loadMoreEnable,
            final LoadAnim loadAnimation) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (!(adapter instanceof BaseQuickAdapter)) {
            return;
        }
        BaseQuickAdapter quickAdapter = (BaseQuickAdapter) adapter;
        if (itemClickListener != null) {
            quickAdapter.setOnItemClickListener(itemClickListener::onItemClick);
        }
        if (loadMoreListener != null) {
            quickAdapter.setOnLoadMoreListener(loadMoreListener::onLoadMore, recyclerView);
        }
        quickAdapter.setEnableLoadMore(loadMoreEnable);
        setLoadAnimation(quickAdapter, loadAnimation);
    }

    public interface ItemClickListener {

        void onItemClick(BaseQuickAdapter adapter, View view, int position);
    }

    public interface LoadMoreListener {

        void onLoadMore();
    }

    public enum LoadAnim {

        NONE(0),
        ALPHAIN(1),
        SCALEIN(2),
        SLIDEIN_BOTTOM(3),
        SLIDEIN_LEFT(4),
        SLIDEIN_RIGHT(5);

        int value;

        LoadAnim(int value) {
            this.value = value;
        }
    }

    private static void setLoadAnimation(BaseQuickAdapter quickAdapter, LoadAnim loadAnimation) {
        if (loadAnimation != null && loadAnimation != LoadAnim.NONE) {
            quickAdapter.openLoadAnimation(loadAnimation.value);
        } else {
            quickAdapter.closeLoadAnimation();
        }
    }
}
