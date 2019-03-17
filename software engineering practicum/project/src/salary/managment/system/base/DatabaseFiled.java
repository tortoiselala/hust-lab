/**
 * 
 */
package salary.managment.system.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import sun.reflect.generics.tree.ReturnType;

/**
 * @author Tortoise
 *
 */
public final class DatabaseFiled {

	private DatabaseFiled() {
	};

	/**
	 * {@code JDBC_DRIVER} 注冊驱动
	 */
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL_HEAD = "jdbc:mysql";

	/**
	 * {@code DB_URL_PORT} 数据库连接端口
	 */

	public static String get_DB_URL_PORT() throws FileNotFoundException, IOException {
		return LoadProperties.getPort();
	}

	public static String get_DB_URL_IP() throws FileNotFoundException, IOException {
		return LoadProperties.getIP();
	}

	/**
	 * {@code DB_DATABASE_USER_INFO}存储高级管理员和普通管理员信息的数据库名
	 */
	public static final String DB_DATABASE_USER_INFO = "user_info";

	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_ADMIN}存储高级管理员信息的表名
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_ADMIN = "user_admin";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME}存储高级管理员信息表的第一列名称，用户名称
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME = "user_admin_name";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_PASS}存储高级管理员信息表的第二列名称，用户密码
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_PASS = "user_admin_pass";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_CREATE_TIME}存储高级管理员信息表的第三列，用户创建时间
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_CREATE_TIME = "user_admin_create_time";

	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_GENER}存储普通管理员信息的表名
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_GENER = "user_gener";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_GENER_NAME}存储普通管理员信息表的第一列名称，用户名称
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_NAME = "user_gener_name";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_GENER_PASS}存储普通管理员信息表的第二列名称，用户密码
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_PASS = "user_gener_pass";
	/**
	 * {@code DB_DATABASE_USER_INFO_TABLE_USER_GENER_CREATE_TIME}存储普通管理员信息表的第三列名称，用户创建时间
	 */
	public static final String DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_CREATE_TIME = "user_gener_create_time";

	/**
	 * {@code DB_DATABASE_BASE_INFORMATION}存储员工基本信息的数据库名
	 */
	public static final String DB_DATABASE_BASE_INFORMATION = "base_information";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_USER_COLLECTION_NAME} 存储员工基本信息表的名称
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM = "employee_basic_information_form";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID}存储员工基本信息表的第一列名称，员工身份证号
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID = "employee_id";
	
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_NAME}存储员工基本信息表的第二列名称，员工姓名
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_NAME = "employee_name";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SEX}存储员工基本信息表的第三列名称，员工性别，字段值为1（男）或0（女）
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SEX = "sex";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_AGE}存储员工基本信息表的第四列名称，员工年龄
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_AGE = "age";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY}存储员工基本信息表的第五列名称，
	 * 员工基本薪资
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY = "base_salary";
	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY}存储员工基本信息表的第六列名称，员工类别
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY = "category";

	/**
	 * {@code DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID}存储员工每月工资信息表的名称，
	 * 部门id
	 */
	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID = "department_id";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_EMPLOYEE_NAME = "employee_name";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE = "sick_fee";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE = "child_care_fee";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE = "rent_fee";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE = "water_ele_fee";

	public static final String DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE = "other_fee";
	/**
	 * <ul>
	 * <li>{@code DB_DATABASE_ROOT_NAME}数据库登录账号</li>
	 * <li>{@code DB_DATABASE_ROOT_PASS}数据库登录密码</li>
	 * </ul>
	 */
	public static final String DB_DATABASE_ROOT_NAME = "root";
	public static final String DB_DATABASE_ROOT_PASS = "1546191727";

	/**
	 * 获取数据库链接
	 * 
	 * @return 数据库链接
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static String getDatabaseURL() throws FileNotFoundException, IOException {
		return DB_URL_HEAD + "://" + get_DB_URL_IP() + ":" + get_DB_URL_PORT();
	}

	public static String getDatabaseUserInfoURL() throws FileNotFoundException, IOException {
		return DB_URL_HEAD + "://" + get_DB_URL_IP() + ":" + get_DB_URL_PORT() + "/" + DB_DATABASE_USER_INFO;
	}

	public static String getDatabaseBaseInformationURL() throws FileNotFoundException, IOException {
		return DB_URL_HEAD + "://" + get_DB_URL_IP() + ":" + get_DB_URL_PORT() + "/" + DB_DATABASE_BASE_INFORMATION;
	}

	public static boolean isIDIllegal(String ID) {
		char[] IDToCharArray = ID.toCharArray();
		int length = IDToCharArray.length;
		for (int i = 0; i < length - 1; i++) {
			if (!(IDToCharArray[i] >= '0' && IDToCharArray[i] <= '9')) {
				return false;
			}
		}
		if (!((IDToCharArray[length - 1] >= '0' && IDToCharArray[length - 1] <= '9') || IDToCharArray[length - 1] == 'x'
				|| IDToCharArray[length - 1] == 'X')) {
			return false;
		}
		return true;
	}
}
