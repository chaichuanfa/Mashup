package com.felix.model.db.joke;

import org.threeten.bp.ZonedDateTime;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Created by chaichuanfa on 2019/1/17
 */
@Entity(tableName = "joke_info")
public class Joke {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "hash_id")
    private String hashId;

    private String content;

    private long unixtime;

    private ZonedDateTime updatetime;

    public Joke(String hashId, String content, long unixtime, ZonedDateTime updatetime) {
        this.hashId = hashId;
        this.content = content;
        this.unixtime = unixtime;
        this.updatetime = updatetime;
    }

    public String getHashId() {
        return hashId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(long unixtime) {
        this.unixtime = unixtime;
    }

    public ZonedDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(ZonedDateTime updatetime) {
        this.updatetime = updatetime;
    }
}
