package com.lvmama.dao;

import com.lvmama.model.User;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-28.
 */
public interface UserDao {
    public User findUserById(int id) throws Exception;

    public List<User> findUserByName(String name) throws Exception;

    public void insertUser(User user) throws Exception;

    public void deleteUser(int id) throws Exception;

}
