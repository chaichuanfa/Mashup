package com.felix.model.db.news;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_info where type = :type")
    Flowable<List<News>> getAllWithType(String type);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(News... news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> news);

    @Delete
    void delete(News user);
}
