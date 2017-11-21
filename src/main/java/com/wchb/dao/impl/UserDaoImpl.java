package com.wchb.dao.impl;

import com.wchb.dao.UserDao;
import com.wchb.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int insertUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.insert("com.wchb.dao.UserDao.insertUser", user);

        sqlSession.commit();

        sqlSession.close();

        return result;

    }

    public int deleteUser(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.delete("com.wchb.dao.UserDao.deleteUser", id);

        sqlSession.commit();

        sqlSession.close();

        return result;

    }

    public int updateUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession.update("com.wchb.dao.UserDao.updateUser", user);

        sqlSession.commit();

        sqlSession.close();

        return result;
    }

    public User findUserById(Long id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = sqlSession.selectOne("com.wchb.dao.UserDao.findUserById", id);

        sqlSession.close();

        return user;
    }

    public List<User> findUserByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<User> list = sqlSession.selectList("com.wchb.dao.UserDao.findUserByName", name);

        sqlSession.close();

        return list;
    }
}

