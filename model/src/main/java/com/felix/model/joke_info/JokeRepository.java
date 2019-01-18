package com.felix.model.joke_info;

import com.felix.model.base.NetworkBoundResource;
import com.felix.model.base.Resource;
import com.felix.model.base.juhe.JuHeApiInfo;
import com.felix.model.base.juhe.JuHeApiResult;
import com.felix.model.db.DatabaseHelper;
import com.felix.model.db.joke.Joke;
import com.felix.model.db.joke.JokeDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public class JokeRepository {

    private static final String APP_KEY = "ffa4d0ffabb5e4462d0ef318adf23c6f";

    private final JokeInfoApi mJokeInfoApi;

    private final JokeDao mJokeDao;

    @Inject
    public JokeRepository(JokeInfoApi jokeInfoApi, DatabaseHelper databaseHelper) {
        mJokeInfoApi = jokeInfoApi;
        mJokeDao = databaseHelper.jokeDao();
    }

    public Observable<Resource<List<Joke>>> loadJokes(int page, String time) {
        return new NetworkBoundResource<List<Joke>, JuHeApiInfo<JuHeApiResult<Joke>>>() {

            @Override
            protected void saveCallResult(JuHeApiInfo<JuHeApiResult<Joke>> resultType) {
                if (resultType.getResult() != null && resultType.getResult().getData().size() > 0) {
                    mJokeDao.deleteAll();
                    mJokeDao.insertAll(resultType.getResult().getData());
                }
            }

            @Override
            protected boolean shouldFetch(List<Joke> data) {
                return data == null || data.size() == 0;
            }

            @Override
            protected Flowable<List<Joke>> loadFromDb() {
                return mJokeDao.getAll();
            }

            @Override
            protected Flowable<JuHeApiInfo<JuHeApiResult<Joke>>> createCall() {
                return mJokeInfoApi.loadJokes(page, time, APP_KEY);
            }
        }.getAsObservable();
    }

    public Flowable<JuHeApiInfo<JuHeApiResult<Joke>>> loadMore(int page, String time) {
        return mJokeInfoApi.loadJokes(page, time, APP_KEY)
                .doOnNext(resultType -> {
                    if (resultType.getResult() != null && resultType.getResult().getData().size() > 0) {
                        mJokeDao.insertAll(resultType.getResult().getData());
                    }
                });
    }

}
