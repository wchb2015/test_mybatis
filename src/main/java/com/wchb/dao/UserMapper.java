package com.wchb.dao;

import com.wchb.model.User;
import com.wchb.model.QueryVo;

import java.util.List;

public interface UserMapper {

    User findUserById(Long id);

    List<User> findUserList(QueryVo vo);

    int findUserCount(QueryVo QueryVo);

    User findUserByIdResultMap(Long id);

    List<User> findUserByName(String name);

    int insertUser(User user);

    int deleteUser(Long id);

    int updateUser(User user);

}
