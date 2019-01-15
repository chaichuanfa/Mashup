package com.felix.model.user_info;

import com.felix.model.db.DatabaseHelper;
import com.felix.model.db.user.User;
import com.felix.model.db.user.UserInfoDao;

import javax.inject.Inject;

/**
 * Created by chaichuanfa on 2019/1/15
 */
public class UserRepository {

    private final UserInfoApi mUserInfoApi;

    private final UserInfoDao mUserInfoDao;

    @Inject
    public UserRepository(UserInfoApi userInfoApi, DatabaseHelper databaseHelper) {
        mUserInfoApi = userInfoApi;
        mUserInfoDao = databaseHelper.userInfoDao();
    }

    public void insertUser(User ... users) {
        mUserInfoDao.insertAll(users);
    }
}
