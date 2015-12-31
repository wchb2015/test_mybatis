import com.lvmama.dao.UserMapper;
import com.lvmama.model.QueryVo;
import com.lvmama.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangchongbei on 15-12-30.
 */
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();

        userMapper = sqlSession.getMapper(UserMapper.class);

    }

    @After
    public void after() throws Exception {
        sqlSession.commit();
        sqlSession.close();
    }


    @org.junit.Test
    public void testFindUserById() throws Exception {

        User user = userMapper.findUserById(16);

        System.out.println(user);

    }

    @Test
    public void testFindUserList() throws Exception {
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(22);
        ids.add(24);
        queryVo.setIds(ids);
        System.out.println(userMapper.findUserList(queryVo));
    }

    @Test
    public void testFindUserCount() throws Exception {
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(22);
        ids.add(24);
        queryVo.setIds(ids);
        System.out.println(userMapper.findUserCount(queryVo));

    }

    @Test
    public void testFindUserByIdResultMap() throws Exception {
        System.out.println(userMapper.findUserByIdResultMap(16));
    }

    @Test
    public void testFindUserByName() throws Exception {
        System.out.println(userMapper.findUserByName("小"));
    }

    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setSex("F");
        user.setUsername("Jerry");
        user.setBirthday(new Date());
        user.setAddress("LA");
        System.out.println(userMapper.insertUser(user));
    }

    @Test
    public void testDeleteUser() throws Exception {
        System.out.println(userMapper.deleteUser(40));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setSex("F");
        user.setUsername("Jerry222");
        user.setBirthday(new Date());
        user.setAddress("LA222");
        user.setId(41);

        System.out.println(userMapper.updateUser(user));
    }

}
/*
* package cn.itcast.mybatis.mapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.QueryVo;

public class UserMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	// 此方法是在执行testFindUserById之前执行
	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory

		// mybatis配置文件
		String resource = "mybatis-config.xml";
		// 得到配置文件流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息
		sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
	}

	//用户信息的综合 查询
	@Test
	public void testFindUserList() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//创建包装对象，设置查询条件
		QueryVo userQueryVo = new QueryVo();
		UserCustom userCustom = new UserCustom();
		//由于这里使用动态sql，如果不设置某个值，条件不会拼接在sql中
//		userCustom.setSex("1");
		userCustom.setUsername("小明");
		//传入多个id
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(10);
		ids.add(16);
		//将ids通过userQueryVo传入statement中
		userQueryVo.setIds(ids);
		userQueryVo.setUserCustom(userCustom);
		//调用userMapper的方法

		List<UserCustom> list = userMapper.findUserList(userQueryVo);

		System.out.println(list);


	}

	@Test
	public void testFindUserCount() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//创建包装对象，设置查询条件
		QueryVo userQueryVo = new QueryVo();
		UserCustom userCustom = new UserCustom();
		userCustom.setSex("1");
		userCustom.setUsername("张三丰");
		userQueryVo.setUserCustom(userCustom);
		//调用userMapper的方法

		int count = userMapper.findUserCount(userQueryVo);

		System.out.println(count);


	}

	@Test
	public void testFindUserById() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//调用userMapper的方法

		User user = userMapper.findUserById(1);

		System.out.println(user);


	}

	@Test
	public void testFindUserByIdResultMap() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//调用userMapper的方法

		User user = userMapper.findUserByIdResultMap(1);

		System.out.println(user);


	}
	@Test
	public void testFindUserByName() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession();

		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		//调用userMapper的方法

		List<User> list = userMapper.findUserByName("小明");

		sqlSession.close();

		System.out.println(list);


	}

}
*/