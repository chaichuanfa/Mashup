package com.felix.model.joke_info;

import com.felix.model.base.juhe.JuHeApiInfo;
import com.felix.model.base.juhe.JuHeApiResult;
import com.felix.model.db.joke.Joke;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public interface JokeInfoApi {

    @GET("/joke/content/list.php")
    Flowable<JuHeApiInfo<JuHeApiResult<Joke>>> loadJokes(@Query("page") int page,
            @Query("time") String time, @Query("key") String key);

}
