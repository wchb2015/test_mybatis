import com.wchb.dao.UserMapper;
import com.wchb.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class UserMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

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

        logger.info("user:{}", user);
    }
    
}
