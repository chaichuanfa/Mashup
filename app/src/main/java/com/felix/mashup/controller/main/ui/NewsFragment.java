package com.felix.mashup.controller.main.ui;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.main.MainViewModel;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.controller.main.ui.adapter.NewsAdapter;
import com.felix.mashup.databinding.NewsFragmentBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Route(path = "/news/fragment")
public class NewsFragment extends BaseFragment<MainViewModel, NewsFragmentBinding> {

    @Autowired(name = "type")
    String mNewsType;

    @Inject
    EventBus mBus;

    private NewsAdapter mNewsAdapter;

    @Override
    protected void injectDependencies() {
        MainComponent component = this.getComponent(MainComponent.class);
        component.inject(this);
        if (mViewModel != null) {
            component.inject(mViewModel);
            if (!mBus.isRegistered(mViewModel)) {
                try {
                    mBus.register(mViewModel);
                } catch (EventBusException e) {
                    // ignore
                }
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.news_fragment;
    }

    @Override
    protected void bindViewModel() {
        super.bindViewModel();
        mDataBinding.setViewmodel(mViewModel);
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    @Override
    protected void bindViews(View view) {
        mNewsAdapter = new NewsAdapter();
        mNewsAdapter.bindToRecyclerView(mDataBinding.mRecyclerView);
        mNewsAdapter.setOnItemClickListener((baseQuickAdapter, view1, i) -> {
            if (i < mNewsAdapter.getData().size()) {
                ARouter.getInstance().build("/web_view/activity")
                        .withOptionsCompat(
                                ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity))
                        .withString("url", mNewsAdapter.getData().get(i).getUrl())
                        .withString("title", mNewsAdapter.getData().get(i).getCategory())
                        .navigation(mActivity);
            }
        });
        mNewsAdapter.setOnItemChildClickListener((baseQuickAdapter, view12, i) -> {
            if (i < mNewsAdapter.getData().size()) {
                mViewModel.deleteNews(mNewsAdapter.getData().get(i));
            }
        });
        mViewModel.getNewsDataMap().observe(this, map -> {
            if (TextUtils.equals(map.keyAt(0), mNewsType)) {
                mNewsAdapter.replaceData(map.get(mNewsType));
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mViewModel.loadNews(mNewsType);
    }

    public enum NewsType {
        top,
        shehui,
        guonei,
        guoji,
        yule,
        tiyu,
        junshi,
        keji,
        caijing,
        shishang;
    }

}
