package com.felix.mashup.controller.splash;

import com.felix.common.base.BaseViewModel;
import com.felix.common.uitls.live_data.SingleLiveEvent;

import org.greenrobot.eventbus.EventBus;

import android.app.Application;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class SplashViewModel extends BaseViewModel {

    public final ObservableInt mCountdownObservable;

    private SingleLiveEvent<Void> mRouteLiveEvent;

    @Inject
    EventBus mBus;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        mCountdownObservable = new ObservableInt(3);
    }

    @Override
    protected EventBus getBus() {
        return mBus;
    }

    public SingleLiveEvent<Void> getRouteLiveEvent() {
        if (mRouteLiveEvent == null) {
            mRouteLiveEvent = new SingleLiveEvent<>();
            addDisposable(Flowable.interval(0, 1, TimeUnit.SECONDS)
                    .take(4)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        mCountdownObservable.set(3 - aLong.intValue());
                        if (aLong == 3) {
                            mRouteLiveEvent.call();
                        }
                    }, throwable -> {
                        Timber.e(throwable, "interval error");
                    }));
        }
        return mRouteLiveEvent;
    }
}
