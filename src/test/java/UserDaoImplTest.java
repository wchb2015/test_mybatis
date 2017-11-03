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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);

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

        logger.info("user:{}", user);
    }

    @Test
    public void testFindUserByName() throws Exception {

        List<User> userList = userDao.findUserByName("王");

     }

    @Test
    public void testInsertUser() throws Exception {

        User user = new User();
        user.setAddress("LA");
        user.setBirthday(new Date());
        user.setUsername("Tom1");
        user.setSex("M");


    }

    @Test
    public void testDeleteUser() throws Exception {


    }

    @Test
    public void testUpdateUser() throws Exception {

        User user = new User();
        user.setAddress("LA");
        user.setBirthday(new Date());
        user.setUsername("Frank");
        user.setSex("M");
        user.setId(42);

    }
}
