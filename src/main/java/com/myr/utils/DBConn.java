package com.myr.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn extends Exception{

    //连接数据库
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/myerp", "root", "123456");
        return conn;
    }
}
