package com.felix.mashup.controller.main;

import com.felix.common.base.BaseViewModel;
import com.felix.common.uitls.reactivex.RxUtils;
import com.felix.mashup.R;
import com.felix.mashup.controller.main.ui.MainFragment.MainMenu;
import com.felix.model.db.news.News;
import com.felix.model.news_info.NewsRepository;
import com.felix.model.user_info.UserRepository;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public class MainViewModel extends BaseViewModel {

    public final ObservableField<String> mToolbarTitle;

    private MutableLiveData<ArrayMap<String, List<News>>> mNewsDataMap;

    @Inject
    EventBus mBus;

    @Inject
    UserRepository mUserRepository;

    @Inject
    NewsRepository mNewsRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mToolbarTitle = new ObservableField<>(application.getString(R.string.nav_title_news));
        mNewsDataMap = new MutableLiveData<>();
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    public void setMenuItemSelected(MainMenu item) {
        switch (item) {
            case NEWS:
                mToolbarTitle.set(getApplication().getString(R.string.nav_title_news));
                break;
            case WECHAT_SIFT:
                mToolbarTitle.set(getApplication().getString(R.string.nav_title_wechat));
                break;
        }
    }

    public void loadNews(String type) {
        addDisposable(mNewsRepository.loadNews(type, true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listResource -> {
                    if (listResource.data != null) {
                        ArrayMap<String, List<News>> map = new ArrayMap<>();
                        map.put(type, listResource.data);
                        mNewsDataMap.setValue(map);
                    }
                }, RxUtils.IgnoreErrorProcessor));
    }

    public void deleteNews(News news) {
        addDisposable(mNewsRepository.deleteNews(news)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(RxUtils.idleAction(), RxUtils.IgnoreErrorProcessor));
    }

    public MutableLiveData<ArrayMap<String, List<News>>> getNewsDataMap() {
        return mNewsDataMap;
    }
}
