import com.alibaba.fastjson.JSON;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class TestC3P0 {

    @org.junit.Test
    public void testConnection() throws Exception {

        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:32769/test_mysql");
        ds.setUser("root");
        ds.setPassword("111111");
        ds.setDriverClass("com.mysql.jdbc.Driver");

        ds.setAcquireIncrement(5);//[每次的增量为5]
        ds.setInitialPoolSize(20);
        ds.setMinPoolSize(2);
        ds.setMaxPoolSize(50);

        Connection con = ds.getConnection();
        System.out.println(ds.getJdbcUrl());
        con.close();
    }
}
