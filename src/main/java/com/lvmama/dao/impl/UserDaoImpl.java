package com.lvmama.dao.impl;

import com.lvmama.dao.UserDao;
import com.lvmama.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * Created by wangchongbei on 15-12-28.
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public int insertUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.insert("test.insertUser", user);

        sqlSession.commit();

        sqlSession.close();

        return result;

    }

    public int deleteUser(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.delete("test.deleteUser", id);

        sqlSession.commit();

        sqlSession.close();

        return result;

    }

    public int updateUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.update("test.updateUser", user);

        sqlSession.commit();

        sqlSession.close();

        return result;
    }

    public User findUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("test.findUserById", id);

        sqlSession.close();

        return user;

    }

    public List<User> findUserByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("test.findUserByName", name);

        sqlSession.close();

        return list;
    }


}

