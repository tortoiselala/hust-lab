package com.tortoiselala.course.java.lab2.base;

import com.tortoiselala.course.java.lab2.tools.Debug;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DatabaseInit {
    // 禁止实例化该类，使用静态方法init来初始化数据库
    private DatabaseInit(){};
    /**
     * T_KSXX  (科室信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * KSBH	CHAR(6)	是	是	否	科室编号，数字
     * KSMC	CHAR(10)	否	否	否	科室名称
     * PYZS	CHAR(8)	否	否	否	科室名称的拼音字首
     *
     * T_BRXX  (病人信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * BRBH	CHAR(6)	是	是	否	病人编号，数字
     * BRMC	CHAR(10)	否	否	否	病人名称
     * DLKL	CHAR(8)	否	否	否	登录口令
     * YCJE	DECIMAL(10,2)	否	否	否	病人预存金额
     * DLRQ	DateTime	否	否	是	最后一次登录日期及时间
     *
     * T_KSYS  (科室医生表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * YSBH	CHAR(6)	是	是	否	医生编号，数字，第1索引
     * KSBH	CHAR(6)	否	是	否	所属科室编号，第2索引
     * YSMC	CHAR(10)	否	否	否	医生名称
     * PYZS	CHAR(4)	否	否	否	医生名称的拼音字首
     * DLKL	CHAR(8)	否	否	否	登录口令
     * SFZJ	BOOL	否	否	否	是否专家
     * DLRQ	DATETIME	否	否	是	最后一次登录日期及时间
     *
     * T_HZXX  (号种信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * HZBH	CHAR(6)	是	是	否	号种编号，数字，第1索引
     * HZMC	CHAR(12)	否	否	否	号种名称
     * PYZS	CHAR(4)	否	否	否	号种名称的拼音字首
     * KSBH	CHAR(6)	否	是	否	号种所属科室，第2索引
     * SFZJ	BOOL	否	否	否	是否专家号
     * GHRS	INT	否	否	否	每日限定的挂号人数
     * GHFY	DECIMAL(8,2)	否	否	否	挂号费
     *
     * T_GHXX  (挂号信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * GHBH	CHAR(6)	是	是	否	挂号的顺序编号，数字
     * HZBH	CHAR(6)	否	是	否	号种编号
     * YSBH	CHAR(6)	否	是	否	医生编号
     * BRBH	CHAR(6)	否	是	否	病人编号
     * GHRC	INT	否	是	否	该病人该号种的挂号人次
     * THBZ	BOOL	否	否	否	退号标志=true为已退号码
     * GHFY	DECIMAL(8,2)	否	否	否	病人的实际挂号费用
     * RQSJ	DATETIME	否	否	否	挂号日期时间
     */
    public static void init() throws SQLException {
        // init two database
        Connection connection = getConnection(DatabaseField.getDatabaseURL());
        Statement statement = connection.createStatement();
        statement.executeUpdate("create database if not exists " + DatabaseField.USER_DB_NAME);
        Debug.log("[database] try create database" + DatabaseField.USER_DB_NAME);
        statement.executeUpdate("create database if not exists " + DatabaseField.BASE_DB_NAME);
        Debug.log("[database] try create database" + DatabaseField.BASE_DB_NAME);
        connection.close();

        // init user database tables
        DatabaseManager.userDatabaseConnection = getConnection(DatabaseField.getDatabaseUserURL());
        DatabaseManager.databaseStatement = DatabaseManager.userDatabaseConnection.createStatement();

        DatabaseManager.userDatabaseConnection.close();

        // init base database tables
        DatabaseManager.baseDatabaseConnection = getConnection(DatabaseField.getDatabaseBaseURL());
        DatabaseManager.baseStatement = DatabaseManager.baseDatabaseConnection.createStatement();

        DatabaseManager.baseStatement.executeUpdate(DatabaseField.CREATE_TABLE_T_TSXX);
        DatabaseManager.baseStatement.executeUpdate(DatabaseField.CREATE_TABLE_T_BRXX);
        DatabaseManager.baseStatement.executeUpdate(DatabaseField.CREATE_TABLE_T_KSYS);
        DatabaseManager.baseStatement.executeUpdate(DatabaseField.CREATE_TABLE_T_HZXX);
        DatabaseManager.baseStatement.executeUpdate(DatabaseField.CREATE_TABLE_T_GHXX);

        DatabaseManager.baseDatabaseConnection.close();
    }


}
