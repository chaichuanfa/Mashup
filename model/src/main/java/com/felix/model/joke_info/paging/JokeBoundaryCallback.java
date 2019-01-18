package com.felix.model.joke_info.paging;

import com.felix.model.db.joke.Joke;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public class JokeBoundaryCallback extends PagedList.BoundaryCallback<Joke> {


    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull Joke itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Joke itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
    }
}
