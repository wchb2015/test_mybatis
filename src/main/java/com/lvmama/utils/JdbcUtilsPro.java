package com.lvmama.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by wangchongbei on 16-1-12.
 */
public class JdbcUtilsPro {
    private static DataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();//[tl其实是一个Map，每个线程都可以来保存自己的Connection]

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();//[获取当前线程的连接]
        if (con == null) {
            return dataSource.getConnection();
        }
        return con;
    }

    public static void beginTranscation() throws SQLException {
        Connection con = tl.get();//获取当前线程的连接
        if (con != null) {   //如果当前线程已经有了连接，说明已经开启了事务
            throw new SQLException("事务已经开启,在没有结束当前事务时,不能再开启事务！");
        }
        con = dataSource.getConnection();//如果当前线程没有连接,说明还没有开启事务,那么就获取一个连接
        con.setAutoCommit(false);//设置连接为手动提交
        tl.set(con);//把连接放到tl中,当前线程就有了连接了.
    }

    public static void commitTransaction() throws SQLException {
        Connection con = tl.get();//获取当前线程的连接
        if (con == null) {//如果当前线程没有连接，那么就不能提交事务
            throw new SQLException("当前没有事务,所以不能提交事务！");
        }
        con.commit();//如果当前线程有连接,那么就提交事务
        con.close();//关闭连接
        tl.remove();//把连接从tl中移除,当前线程就没有连接了,表示事务结束
    }

    public static void rollbackTransaction() throws SQLException {
        Connection con = tl.get();
        if (con == null) {
            throw new SQLException("当前没有事务,所以不能回滚事务！");
        }
        con.rollback();
        con.close();
        tl.remove();
    }
}
