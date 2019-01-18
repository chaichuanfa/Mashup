package com.felix.model.db.wechat;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by chaichuanfa on 2019/1/17
 */
@Entity(tableName = "wechat_sift")
public class WeChatSift {

    @PrimaryKey
    @NonNull
    private String id;

    private String title;

    private String source;

    @Nullable
    @ColumnInfo(name = "first_img")
    private String firstImg;

    @Nullable
    private String mark;

    private String url;

    public WeChatSift(String id, String title, String source, @Nullable String firstImg,
            @Nullable String mark, String url) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.firstImg = firstImg;
        this.mark = mark;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Nullable
    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(@Nullable String firstImg) {
        this.firstImg = firstImg;
    }

    @Nullable
    public String getMark() {
        return mark;
    }

    public void setMark(@Nullable String mark) {
        this.mark = mark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
