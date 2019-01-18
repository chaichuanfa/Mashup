package com.felix.model.db;

import com.felix.model.db.joke.Joke;
import com.felix.model.db.joke.JokeDao;
import com.felix.model.db.news.News;
import com.felix.model.db.news.NewsDao;
import com.felix.model.db.user.User;
import com.felix.model.db.user.UserInfoDao;
import com.felix.model.db.wechat.WeChatSift;
import com.felix.model.db.wechat.WeChatSiftDao;
import com.felix.model.db.converters.RoomConverters;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Database(entities = {
        User.class,
        Joke.class,
        News.class,
        WeChatSift.class
},
        version = 1)
@TypeConverters({RoomConverters.class})
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract UserInfoDao userInfoDao();

    public abstract NewsDao newsDao();

    public abstract JokeDao jokeDao();

    public abstract WeChatSiftDao weChatSiftDao();
}
