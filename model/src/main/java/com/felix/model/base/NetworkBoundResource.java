package com.felix.model.base;

import com.felix.common.uitls.net.RetrofitUtils;
import com.felix.common.uitls.reactivex.RxUtils;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/11
 */
// ResultType: Type for the Resource data
// RequestType: Type for the API response
public abstract class NetworkBoundResource<ResultType, RequestType> {

    private CompositeDisposable mCompositeDisposable;

    private Subject mSubject = PublishSubject.create().<Resource<ResultType>>toSerialized();

    private AtomicBoolean mLocalFirst;

    @MainThread
    public NetworkBoundResource() {
        this(false);
    }

    /**
     * 创建缓存-网络数据加载辅助类
     *
     * @param forceRefresh 缓存可用时强制刷新数据
     */
    @MainThread
    public NetworkBoundResource(boolean forceRefresh) {
        mCompositeDisposable = new CompositeDisposable();
        mLocalFirst = new AtomicBoolean(true);
        mSubject.onNext(Resource.loading(null));
        mCompositeDisposable.add(loadFromDb().subscribeOn(Schedulers.io())
                .subscribe(resultType -> {
                    if (mLocalFirst.get()) {
                        if (shouldFetch(resultType)) {
                            mLocalFirst.set(false);
                            mSubject.onNext(Resource.loading(resultType));
                            fetchFromNetwork(resultType);
                        } else {
                            mLocalFirst.set(false);
                            if (forceRefresh) {
                                mSubject.onNext(Resource.loading(resultType));
                                fetchFromNetwork(resultType);
                            } else {
                                mSubject.onNext(Resource.success(resultType));
                            }
                        }
                    } else {
                        mSubject.onNext(Resource.success(resultType));
                    }
                }, throwable -> {
                    mSubject.onNext(Resource.error(throwable.toString(), null));
                }));
    }

    private void fetchFromNetwork(final ResultType dbSource) {
        mCompositeDisposable.add(createCall()
                .doOnNext(this::saveCallResult)
                .subscribe(RxUtils.idleConsumer(), throwable -> {
                    mSubject.onNext(
                            Resource.error(RetrofitUtils.getErrorMessage(throwable), dbSource));
                }));
    }

    @MainThread
    public Observable<Resource<ResultType>> getAsObservable() {
        return mSubject
                .share()
                .doOnDispose(() -> {
                    mCompositeDisposable.dispose();
                    mCompositeDisposable = null;
                });
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType resultType);

    @WorkerThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @WorkerThread
    protected abstract Flowable<RequestType> createCall();

}