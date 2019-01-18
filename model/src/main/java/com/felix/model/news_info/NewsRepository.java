package com.felix.model.news_info;

import com.felix.model.base.NetworkBoundResource;
import com.felix.model.base.Resource;
import com.felix.model.base.juhe.JuHeApiInfo;
import com.felix.model.base.juhe.JuHeApiResult;
import com.felix.model.db.DatabaseHelper;
import com.felix.model.db.news.News;
import com.felix.model.db.news.NewsDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public class NewsRepository {

    private static final String APP_KEY = "8b00d5336ab4e64bc5e9217e61763687";

    private final NewsInfoApi mNewsInfoApi;

    private final NewsDao mNewsDao;

    @Inject
    public NewsRepository(NewsInfoApi newsInfoApi, DatabaseHelper databaseHelper) {
        mNewsInfoApi = newsInfoApi;
        mNewsDao = databaseHelper.newsDao();
    }

    public Observable<Resource<List<News>>> loadNews(final String type,
            final boolean forceRefresh) {
        return new NetworkBoundResource<List<News>, JuHeApiInfo<JuHeApiResult<News>>>(
                forceRefresh) {

            @Override
            protected void saveCallResult(JuHeApiInfo<JuHeApiResult<News>> resultType) {
                if (resultType.getResult() != null && resultType.getResult().getData().size() > 0) {
                    for (News news : resultType.getResult().getData()) {
                        news.setType(type);
                    }
                    mNewsDao.insertAll(resultType.getResult().getData());
                }
            }

            @Override
            protected boolean shouldFetch(List<News> data) {
                return data == null || data.size() == 0;
            }

            @Override
            protected Flowable<List<News>> loadFromDb() {
                return mNewsDao.getAllWithType(type);
            }

            @Override
            protected Flowable<JuHeApiInfo<JuHeApiResult<News>>> createCall() {
                return mNewsInfoApi.loadNews(type, APP_KEY);
            }
        }.getAsObservable();
    }

    public Completable deleteNews(News news) {
        return Completable.create(emitter -> {
            mNewsDao.delete(news);
            emitter.onComplete();
        });
    }
}
