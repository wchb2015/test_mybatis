package com.lvmama.dao;

import com.lvmama.model.User;

import java.util.List;

public interface UserDao {

    User findUserById(int id);

    List<User> findUserByName(String name);

    int insertUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

}
