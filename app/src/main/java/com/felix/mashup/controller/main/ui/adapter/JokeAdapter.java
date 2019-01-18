package com.felix.mashup.controller.main.ui.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.felix.common.base.BaseRecyclerAdapter;
import com.felix.mashup.R;
import com.felix.mashup.databinding.ItemJokeBinding;
import com.felix.model.db.joke.Joke;

import android.databinding.DataBindingUtil;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public class JokeAdapter extends BaseRecyclerAdapter<Joke> {

    public JokeAdapter() {
        super(R.layout.item_joke);
    }

    @Override
    protected void convert(BaseViewHolder helper, Joke item) {
        ItemJokeBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.setJoke(item);
        binding.executePendingBindings();
    }
}
