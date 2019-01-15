package com.felix.model.db.user;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Dao
public interface UserInfoDao {

    @Query("SELECT * FROM user_info")
    Flowable<List<User>> getAll();

    @Query("SELECT * FROM user_info WHERE uid = :uid")
    LiveData<User> load(String uid);

    @Query("SELECT * FROM user_info")
    LiveData<List<User>> getAllWithLiveData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
