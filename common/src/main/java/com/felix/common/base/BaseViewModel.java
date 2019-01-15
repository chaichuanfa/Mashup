package com.felix.common.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by chaichuanfa on 2019/1/14
 */
public abstract class BaseViewModel extends AndroidViewModel {

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        clearDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        if (this.mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable = new CompositeDisposable();
        }
        this.mCompositeDisposable.add(disposable);
    }

    protected void removeDisposable(Disposable disposable) {
        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.remove(disposable);
        }
    }

    private void clearDisposable() {
        if (this.mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            this.mCompositeDisposable.clear();
        }
        mCompositeDisposable = null;
    }
}
