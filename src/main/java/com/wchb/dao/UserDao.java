package com.wchb.dao;

import com.wchb.model.User;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public interface UserDao {

    User findUserById(Long id);

    List<User> findUserByName(String name);

    int insertUser(User user);

    int deleteUser(Long id);

    int updateUser(User user);

}
