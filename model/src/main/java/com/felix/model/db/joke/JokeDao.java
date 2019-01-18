package com.felix.model.db.joke;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Dao
public interface JokeDao {

    @Query("SELECT * FROM joke_info")
    Flowable<List<Joke>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Joke> news);

    @Query("DELETE FROM joke_info")
    void deleteAll();

}
