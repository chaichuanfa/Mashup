package com.felix.mashup.controller.main.ui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.felix.mashup.R;
import com.felix.mashup.base.BaseFragment;
import com.felix.mashup.controller.main.MainViewModel;
import com.felix.mashup.controller.main.di.MainComponent;
import com.felix.mashup.controller.main.ui.adapter.NewsControllerAdapter;
import com.felix.mashup.databinding.NewsControllerFragmentBinding;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Route(path = "/news_controller/fragment")
public class NewsControllerFragment
        extends BaseFragment<MainViewModel, NewsControllerFragmentBinding> {

    @Inject
    EventBus mBus;

    String[] mNewsTypes;

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
        return R.layout.news_controller_fragment;
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
        mNewsTypes = getResources().getStringArray(R.array.news_types);
        mDataBinding.mViewPager.setAdapter(new NewsControllerAdapter(getChildFragmentManager()));
        CommonNavigator commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mNewsTypes.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView
                        = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(mNewsTypes[index]);
                colorTransitionPagerTitleView.setOnClickListener(view1 -> {
                    mDataBinding.mViewPager.setCurrentItem(index);
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        mDataBinding.magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mDataBinding.magicIndicator, mDataBinding.mViewPager);
    }

}
