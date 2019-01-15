package com.felix.model.db;

import com.felix.model.db.user.User;
import com.felix.model.db.user.UserInfoDao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Database(entities = {User.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract UserInfoDao userInfoDao();
}
