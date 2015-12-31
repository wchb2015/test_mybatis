package com.lvmama.dao;

import com.lvmama.model.User;
import com.lvmama.model.QueryVo;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-29.
 */
public interface UserMapper {

    User findUserById(int id);

    List<User> findUserList(QueryVo vo);

    int findUserCount(QueryVo QueryVo);

    User findUserByIdResultMap(int id);

    List<User> findUserByName(String name);

    int insertUser(User user);

    int deleteUser(int id);

    int updateUser(User user);

}
