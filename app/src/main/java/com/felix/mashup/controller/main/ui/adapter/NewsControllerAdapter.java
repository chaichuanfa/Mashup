package com.felix.mashup.controller.main.ui.adapter;

import com.alibaba.android.arouter.launcher.ARouter;
import com.felix.mashup.controller.main.ui.NewsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class NewsControllerAdapter extends FragmentPagerAdapter {

    public NewsControllerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return (Fragment) ARouter.getInstance()
                .build("/news/fragment")
                .withString("type", getNewsType(i).toString())
                .navigation();
    }

    @Override
    public int getCount() {
        return 10;
    }

    private NewsFragment.NewsType getNewsType(int i) {
        NewsFragment.NewsType type;
        switch (i) {
            case 0:
                type = NewsFragment.NewsType.top;
                break;
            case 1:
                type = NewsFragment.NewsType.shehui;
                break;
            case 2:
                type = NewsFragment.NewsType.guonei;
                break;
            case 3:
                type = NewsFragment.NewsType.guoji;
                break;
            case 4:
                type = NewsFragment.NewsType.yule;
                break;
            case 5:
                type = NewsFragment.NewsType.tiyu;
                break;
            case 6:
                type = NewsFragment.NewsType.junshi;
                break;
            case 7:
                type = NewsFragment.NewsType.keji;
                break;
            case 8:
                type = NewsFragment.NewsType.caijing;
                break;
            case 9:
                type = NewsFragment.NewsType.shishang;
                break;
            default:
                type = NewsFragment.NewsType.top;
                break;
        }
        return type;
    }
}