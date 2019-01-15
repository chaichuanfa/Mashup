package com.felix.model.provider;

import com.felix.model.db.DatabaseHelper;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Module
public class ModelDatabaseModule {

    @Singleton
    @Provides
    DatabaseHelper provideDatabaseHelper(Context context) {
        DatabaseHelper db = Room.databaseBuilder(context.getApplicationContext(),
                DatabaseHelper.class, "mashup.db")
                .build();
        return db;
    }
}
