package com.felix.model.db.user;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by chaichuanfa on 2019/1/15
 */
@Entity(tableName = "user_info")
public class User {

    @PrimaryKey
    private long uid;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "avatar_url")
    private String avatar;

    public User(long uid, String username, String avatar) {
        this.uid = uid;
        this.username = username;
        this.avatar = avatar;
    }

    public long getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
