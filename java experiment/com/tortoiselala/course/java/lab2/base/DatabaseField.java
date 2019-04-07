package com.tortoiselala.course.java.lab2.base;

public class DatabaseField {
    private DatabaseField() {
    }

    ;
    /**
     * {@code JDBC_DRIVER} 注冊驱动
     * {@code DB_URL_HEAD} 连接头
     * {@code DB_URL_IP} 数据库IP
     * {@code DB_URL_PORT}数据库连接端口
     */
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL_HEAD = "jdbc:mysql";
    public static final String DB_URL_IP = "127.0.0.1";
    public static final int DB_URL_PORT = 3306;

    /**
     * <ul>
     * <li>{@code DB_DATABASE_ROOT_NAME}数据库登录账号</li>
     * <li>{@code DB_DATABASE_ROOT_PASS}数据库登录密码</li>
     * </ul>
     */
    public static final String DB_DATABASE_ROOT_NAME = "root";
    public static final String DB_DATABASE_ROOT_PASS = "88888888";

    /**
     * 登录信息数据库名称：user_database
     * 登录信息，医生表名称：user_doctor
     * 登录信息，病人表名称：user_patient
     */
    public static final String USER_DB_NAME = "user_database";
    public static final String USER_DB_TABLE_DOCTOR_NAME = "user_doctor";
    public static final String USER_DB_TABLE_PATIENT_NAME = "user_patient";

    /**
     * 医院信息数据库名称：base_database
     *
     * @return
     */
    public static final String BASE_DB_NAME = "base_database";


    /**
     * T_KSXX  (科室信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * KSBH	CHAR(6)	是	是	否	科室编号，数字
     * KSMC	CHAR(10)	否	否	否	科室名称
     * PYZS	CHAR(8)	否	否	否	科室名称的拼音字首
     */
    public static final String BASE_DB_TABLE_T_KSXX = "T_KSXX";
    public static final String BASE_DB_TABLE_T_KSXX_KSBH = "KSBN";
    public static final String BASE_DB_TABLE_T_KSXX_KSMC = "KSMC";
    public static final String BASE_DB_TABLE_T_KSXX_PYZS = "PYZS";
    /**
     * T_BRXX  (病人信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * BRBH	CHAR(6)	是	是	否	病人编号，数字
     * BRMC	CHAR(10)	否	否	否	病人名称
     * DLKL	CHAR(8)	否	否	否	登录口令
     * YCJE	DECIMAL(10,2)	否	否	否	病人预存金额
     * DLRQ	DateTime	否	否	是	最后一次登录日期及时间
     */
    public static final String BASE_DB_TABLE_T_BRXX = "T_BRXX";
    public static final String BASE_DB_TABLE_T_BRXX_BRBH = "BRBH";
    public static final String BASE_DB_TABLE_T_BRXX_BRMC = "BRMC";
    public static final String BASE_DB_TABLE_T_BRXX_DLKL = "DLKL";
    public static final String BASE_DB_TABLE_T_BRXX_YCJE = "YCJE";
    public static final String BASE_DB_TABLE_T_BRXX_DLRQ = "DLRQ";
    /**
     * T_KSYS  (科室医生表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * YSBH	CHAR(6)	是	是	否	医生编号，数字，第1索引
     * KSBH	CHAR(6)	否	是	否	所属科室编号，第2索引
     * YSMC	CHAR(10)	否	否	否	医生名称
     * PYZS	CHAR(4)	否	否	否	医生名称的拼音字首
     * DLKL	CHAR(8)	否	否	否	登录口令
     * SFZJ	BOOL	否	否	否	是否专家
     * DLRQ	DATETIME	否	否	是	最后一次登录日期及时间
     */
    public static final String BASE_DB_TABLE_T_KSYS = "T_KSYS";
    public static final String BASE_DB_TABLE_T_KSYS_YSBH = "YSBH";

    public static final String BASE_DB_TABLE_T_KSYS_KSBH = "KSBH";
    public static final String BASE_DB_TABLE_T_KSYS_YSMC = "YSMC";
    public static final String BASE_DB_TABLE_T_KSYS_PYZS = "PYZS";
    public static final String BASE_DB_TABLE_T_KSYS_DLKL = "DLKL";
    public static final String BASE_DB_TABLE_T_KSYS_SFZJ = "SFZJ";
    public static final String BASE_DB_TABLE_T_KSYS_DLRQ = "DLRQ";

    /**
     * T_HZXX  (号种信息表)
     * 字段名称	字段类型	主键	索引	可空	备注
     * HZBH	CHAR(6)	是	是	否	号种编号，数字，第1索引
     * HZMC	CHAR(12)	否	否	否	号种名称
     * PYZS	CHAR(4)	否	否	否	号种名称的拼音字首
     * KSBH	CHAR(6)	否	是	否	号种所属科室，第2索引
     * SFZJ	BOOL	否	否	否	是否专家号
     * GHRS	INT	否	否	否	每日限定的挂号人数
     * GHFY	DECIMAL(8,2)	否	否	否	挂号费
     */
    public static final String BASE_DB_TABLE_T_HZXX = "T_HZXX";
    public static final String BASE_DB_TABLE_T_HZXX_HZBH = "HZBH";
    public static final String BASE_DB_TABLE_T_HZXX_HZMC = "HZMC";
    public static final String BASE_DB_TABLE_T_HZXX_PYZS = "PYZS";
    public static final String BASE_DB_TABLE_T_HZXX_KSBH = "KSBH";
    public static final String BASE_DB_TABLE_T_HZXX_SFZJ = "SFZJ";
    public static final String BASE_DB_TABLE_T_HZXX_GHRS = "GHRS";
    public static final String BASE_DB_TABLE_T_HZXX_GHFY = "GHFY";
    /**
     * T_GHXX  (挂号信息表)
     * 字段名称	字段类型	     主键	索引	可空	备注
     * GHBH	    CHAR(6)	      是	是	否	挂号的顺序编号，数字
     * HZBH	    CHAR(6)       否	是	否	号种编号
     * YSBH	    CHAR(6)	      否	是	否	医生编号
     * BRBH    	CHAR(6)	      否	是	否	病人编号
     * GHRC	    INT	          否	是	否	该病人该号种的挂号人次
     * THBZ   	BOOL	      否	否	否	退号标志=true为已退号码
     * GHFY	    DECIMAL(8,2)  否	否	否	病人的实际挂号费用
     * RQSJ	DATETIME          否	否	否	挂号日期时间
     */
    public static final String BASE_DB_TABLE_T_GHXX = "T_GHXX";
    public static final String BASE_DB_TABLE_T_GHXX_GHBH = "GHBH";
    public static final String BASE_DB_TABLE_T_GHXX_HZBH = "HZBH";
    public static final String BASE_DB_TABLE_T_GHXX_YSBH = "YSBH";
    public static final String BASE_DB_TABLE_T_GHXX_BRBH = "BRBH";
    public static final String BASE_DB_TABLE_T_GHXX_GHRC = "GHRC";
    public static final String BASE_DB_TABLE_T_GHXX_THBZ = "THBZ";
    public static final String BASE_DB_TABLE_T_GHXX_GHFY = "GHFY";
    public static final String BASE_DB_TABLE_T_GHXX_RQSJ = "RQSJ";

    // 获取数据库连接
    public static String getDatabaseURL() {
        return DB_URL_HEAD + "://" + DB_URL_IP + ":" + DB_URL_PORT;
    }

    // 获取用户数据库的连接
    public static final String getDatabaseUserURL() {
        return DB_URL_HEAD + "://" + DB_URL_IP + ":" + DB_URL_PORT + "/" + USER_DB_NAME;
    }

    // 获取医院数据库的连接
    public static final String getDatabaseBaseURL() {
        return DB_URL_HEAD + "://" + DB_URL_IP + ":" + DB_URL_PORT + "/" + BASE_DB_NAME;
    }

    // 创建TSXX表格
    public static final String CREATE_TABLE_T_TSXX = "create table "
            + BASE_DB_TABLE_T_KSXX + "("
            + BASE_DB_TABLE_T_KSXX_KSBH + " char(6) not null, "
            + BASE_DB_TABLE_T_KSXX_KSMC + " char(10) not null, "
            + BASE_DB_TABLE_T_KSXX_PYZS + " char(8) not null, "
            + "PRIMARY KEY (" + BASE_DB_TABLE_T_KSXX_KSBH + "), "
            + "INDEX ("
            + BASE_DB_TABLE_T_KSXX_KSBH + ")"
            + ");";
    // 创建BRXX表格
    public static final String CREATE_TABLE_T_BRXX = "create table "
            + BASE_DB_TABLE_T_BRXX + "("
            + BASE_DB_TABLE_T_BRXX_BRBH + " char(6) not null, "
            + BASE_DB_TABLE_T_BRXX_BRMC + " char(10) not null, "
            + BASE_DB_TABLE_T_BRXX_DLKL + " char(8) not null, "
            + BASE_DB_TABLE_T_BRXX_YCJE + " decimal(10, 2) not null, "
            + BASE_DB_TABLE_T_BRXX_DLRQ + " timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "
            + "PRIMARY KEY (" + BASE_DB_TABLE_T_BRXX_BRBH + "), "
            + "INDEX ("
            + BASE_DB_TABLE_T_BRXX_BRBH + ")"
            + ");";
    // 创建KSYS表格
    public static final String CREATE_TABLE_T_KSYS = "create table "
            + BASE_DB_TABLE_T_KSYS + "("
            + BASE_DB_TABLE_T_KSYS_YSBH + " char(6) not null, "
            + BASE_DB_TABLE_T_KSYS_KSBH + " char(6), "
            + BASE_DB_TABLE_T_KSYS_YSMC + " char(10) not null, "
            + BASE_DB_TABLE_T_KSYS_PYZS + " char(4) not null, "
            + BASE_DB_TABLE_T_KSYS_DLKL + " char(8) not null, "
            + BASE_DB_TABLE_T_KSYS_SFZJ + " bool not null, "
            + BASE_DB_TABLE_T_KSYS_DLRQ + " timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "
            + "PRIMARY KEY (" + BASE_DB_TABLE_T_KSYS_YSBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_KSYS_YSBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_KSYS_KSBH + ")"
            + ");";
    // 创建HZXX表格
    public static final String CREATE_TABLE_T_HZXX = "create table "
            + BASE_DB_TABLE_T_HZXX + "("
            + BASE_DB_TABLE_T_HZXX_HZBH + " char(6) not null, "
            + BASE_DB_TABLE_T_HZXX_HZMC + " char(12), "
            + BASE_DB_TABLE_T_HZXX_PYZS + " char(4) not null, "
            + BASE_DB_TABLE_T_HZXX_KSBH + " char(6) not null, "
            + BASE_DB_TABLE_T_HZXX_SFZJ + " bool not null, "
            + BASE_DB_TABLE_T_HZXX_GHRS + " int not null, "
            + BASE_DB_TABLE_T_HZXX_GHFY + " decimal(8, 2), "
            + "PRIMARY KEY (" + BASE_DB_TABLE_T_HZXX_HZBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_HZXX_HZBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_HZXX_KSBH + ")"
            + ");";
    // 创建GHXX表格
    public static final String CREATE_TABLE_T_GHXX = "create table "
            + BASE_DB_TABLE_T_GHXX + "("
            + BASE_DB_TABLE_T_GHXX_GHBH + " char(6) not null, "
            + BASE_DB_TABLE_T_GHXX_HZBH + " char(6), "
            + BASE_DB_TABLE_T_GHXX_YSBH + " char(6) not null, "
            + BASE_DB_TABLE_T_GHXX_BRBH + " char(6) not null, "
            + BASE_DB_TABLE_T_GHXX_GHRC + " int not null, "
            + BASE_DB_TABLE_T_GHXX_THBZ + " bool not null, "
            + BASE_DB_TABLE_T_GHXX_GHFY + " decimal(8, 2) not null, "
            + "PRIMARY KEY (" + BASE_DB_TABLE_T_GHXX_GHBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_GHXX_GHBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_GHXX_HZBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_GHXX_YSBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_GHXX_BRBH + "), "
            + "INDEX (" + BASE_DB_TABLE_T_GHXX_GHRC + ")"
            + ");";

    public static void main(String[] args) {
        System.out.println(CREATE_TABLE_T_TSXX);
        System.out.println(CREATE_TABLE_T_BRXX);
        System.out.println(CREATE_TABLE_T_KSYS);
        System.out.println(CREATE_TABLE_T_HZXX);
        System.out.println(CREATE_TABLE_T_GHXX);
    }
}
