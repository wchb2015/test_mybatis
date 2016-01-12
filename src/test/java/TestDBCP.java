
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.*;

import java.sql.Connection;

/**
 * Created by wangchongbei on 16-1-12.
 */
public class TestDBCP {


    @org.junit.Test
    public void testConnection() throws Exception {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername("root");
        ds.setPassword("111111");
        ds.setUrl("jdbc:mysql://localhost:3306/mysql_test");
        ds.setDriverClassName("com.mysql.jdbc.Driver");

        ds.setMaxActive(20);
        ds.setMaxIdle(10);
        ds.setInitialSize(10);
        ds.setMinIdle(2);
        ds.setMaxWait(1000);

        Connection con = ds.getConnection();
        System.out.println(con.getClass().getName());
        con.close();
    }

}
