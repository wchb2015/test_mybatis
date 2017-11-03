import com.wchb.dao.UserDao;
import com.wchb.dao.impl.UserDaoImpl;
import com.wchb.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImplTest.class);

    private SqlSessionFactory sqlSessionFactory;

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        userDao = new UserDaoImpl(sqlSessionFactory);


    }

    @Test
    public void testFindUserById() throws Exception {

        User user = userDao.findUserById(16);

     }

    @Test
    public void testFindUserByName() throws Exception {

        List<User> userList = userDao.findUserByName("王");

        LOG.info("Size------" + userList.size());
     }

    @Test
    public void testInsertUser() throws Exception {

        User user = new User();
        user.setAddress("LA");
        user.setBirthday(new Date());
        user.setUsername("Tom1");
        user.setSex("M");

        LOG.info("result----" + userDao.insertUser(user));

    }

    @Test
    public void testDeleteUser() throws Exception {

        LOG.info("result----" + userDao.deleteUser(29));

    }

    @Test
    public void testUpdateUser() throws Exception {

        User user = new User();
        user.setAddress("LA");
        user.setBirthday(new Date());
        user.setUsername("Frank");
        user.setSex("M");
        user.setId(42);

        LOG.info("result----" + userDao.updateUser(user));

    }
}
