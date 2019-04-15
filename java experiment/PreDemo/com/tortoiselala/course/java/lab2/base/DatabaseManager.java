package com.tortoiselala.course.java.lab2.base;

import com.tortoiselala.course.java.lab2.tools.Debug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseManager {

    protected static Connection userDatabaseConnection;
    public static Statement databaseStatement;
    protected static Connection baseDatabaseConnection;
    public static Statement baseStatement;
    private static Logger logWriter;

    public static boolean checkUserPermission(User user) {
        return false;
    }
    public static boolean checkUserPatientOrDoctor(User user){
        return false;
    }



    private static Connection getConnection(String url) {
        Connection connection = null;
        // 注册JDBC驱动
        try {
            Class.forName(DatabaseField.JDBC_DRIVER);
            // 打开数据库
            connection = DriverManager.getConnection(url, DatabaseField.DB_DATABASE_ROOT_NAME,
                    DatabaseField.DB_DATABASE_ROOT_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            ExceptionHandler.handler(e);
        }
        Debug.log("connect to database: " + url);
        return connection;
    }
}
