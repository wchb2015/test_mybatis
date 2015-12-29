package com.lvmama.dao;

import com.lvmama.model.User;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-29.
 */
public interface UserMapper {

    User findUserById(int id) throws Exception;

    User findUserByIdResultMap(int id) throws Exception;

    List<User> findUserByName(String name) throws Exception;

    void insertUser(User user) throws Exception;

    void deleteUser(int id) throws Exception;

    void updateUser(User user) throws Exception;

}
