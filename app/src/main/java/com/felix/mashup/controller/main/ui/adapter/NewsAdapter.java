package com.felix.mashup.controller.main.ui.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.felix.common.base.BaseRecyclerAdapter;
import com.felix.mashup.R;
import com.felix.mashup.databinding.ItemNewsBinding;
import com.felix.model.db.news.News;

import android.databinding.DataBindingUtil;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class NewsAdapter extends BaseRecyclerAdapter<News> {

    public NewsAdapter() {
        super(R.layout.item_news);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, News news) {
        ItemNewsBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        binding.setNews(news);
        baseViewHolder.addOnClickListener(R.id.delete);
        binding.executePendingBindings();
    }
}
