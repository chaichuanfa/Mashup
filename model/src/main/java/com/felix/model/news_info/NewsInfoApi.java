package com.felix.model.news_info;

import com.felix.model.base.juhe.JuHeApiInfo;
import com.felix.model.base.juhe.JuHeApiResult;
import com.felix.model.db.news.News;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chaichuanfa on 2019/1/17
 */
public interface NewsInfoApi {

    @GET("/toutiao/index")
    Flowable<JuHeApiInfo<JuHeApiResult<News>>> loadNews(@Query("type") String type,
            @Query("key") String appKey);

}
