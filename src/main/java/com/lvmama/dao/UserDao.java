package com.lvmama.dao;

import com.lvmama.model.User;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-28.
 */
public interface UserDao {

    User findUserById(int id) throws Exception;

    List<User> findUserByName(String name) throws Exception;

    int insertUser(User user) throws Exception;

    int deleteUser(int id) throws Exception;

    int updateUser(User user) throws Exception;

}
