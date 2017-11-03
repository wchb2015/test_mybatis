package com.wchb.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtils {

    private static DataSource dataSource = new ComboPooledDataSource();
    private static Connection con = null;

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        if (con == null) {
            return dataSource.getConnection();
        }
        return con;
    }

    public static void beginTranscation() throws SQLException {
        if (con != null) {
            throw new SQLException("事务已经开启,在没有结束当前事务时,不能再开启事务！");
        }
        con = dataSource.getConnection();
        con.setAutoCommit(false);
    }

    public static void commitTransaction() throws SQLException {
        if (con == null) {
            throw new SQLException("当前没有事务,所以不能提交事务！");
        }
        con.commit();
        con.close();
        con = null;
    }

    public static void rollbackTransaction() throws SQLException {
        if (con == null) {
            throw new SQLException("当前没有事务,所以不能回滚事务！");
        }
        con.rollback();
        con.close();
        con = null;
    }

}
