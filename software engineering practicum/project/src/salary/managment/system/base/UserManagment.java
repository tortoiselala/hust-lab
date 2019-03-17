/**
 * 
 */
package salary.managment.system.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;


import salary.managment.system.base.DatabaseFiled;
import salary.managment.system.Exception.LenException;

/**
 * @author Tortoise
 * 
 *         <h1>该类用于操作系统管理员</h1>
 *         <p>
 *         高级管理员仅一个，普通管理员有多个
 *         </p>
 *         <p>
 *         高级管理员具有数据处理以及修改管理员的功能
 *         </p>
 *         普通管理员仅仅具有数据处理的功能 每次登录，管理员的登录信息将被记录
 *         <p>
 *         1.查看 允许登录的高级管理员查看其他普通管理员账号
 *         </p>
 *         <p>
 *         2.添加 允许登录的高级管理员添加其他普通管理员账号
 *         </p>
 *         <p>
 *         3.删除 允许登录的高级管理员删除其他普通管理员账号
 *         </p>
 *         <p>
 *         4.修改 允许修改当前账号的密码
 *         </p>
 */
public class UserManagment {
	public static void main(String[] args) throws ClassNotFoundException {
		UserManagment test_1 = new UserManagment("g_test_2", "g_test_2");
		try {
			System.out.println("-------------------begin: test case 1----------");
			System.out.println("       user name: g_test_2");
			System.out.println("       user pass: g_test_2");
			System.out.println("test:getAllAdmin");
			String[][] allAdmin = test_1.getAllAdminUser();
			for (String[] e : allAdmin) {
				System.out.print(e[0] + "  ");
				System.out.print(e[1] + "  ");
				System.out.println(e[2]);
			}
			System.out.println();
			System.out.println("test case 1:getAllGener");
			String[][] allGener = test_1.getAllGenerUser();
			for (String[] e : allGener) {
				System.out.print(e[0] + "  ");
				System.out.print(e[1] + "  ");
				System.out.println(e[2]);
			}
			System.out.println();
			System.out.println("test case 2.1:isAdminUser pass: false");
			if (test_1.isAdminUser(false)) {
				System.out.println("isAdminUser: true");
			} else {
				System.out.println("isAdminUser: false");
			}
			System.out.println();
			System.out.println("test case 2.2:isAdminUser pass:true");
			if (test_1.isAdminUser(true)) {
				System.out.println("isAdminUser: true");
			} else {
				System.out.println("isAdminUser: false");
			}
			System.out.println("---------------------end: test case 1----------");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			test_1.tryCreateAdminUser();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String adminName;
	private String adminPass;
	private Connection connection;
	private Statement statement;

	/**
	 * 
	 */

	public UserManagment(String adminName, String adminPass) {
		setAdminName(adminName);
		setAdminPass(adminPass);

	}

	private void getConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		// 注册JDBC驱动
		Class.forName(DatabaseFiled.JDBC_DRIVER);
		// 打开数据库
		connection = DriverManager.getConnection(DatabaseFiled.getDatabaseUserInfoURL(),
				DatabaseFiled.DB_DATABASE_ROOT_NAME, DatabaseFiled.DB_DATABASE_ROOT_PASS);
		statement = connection.createStatement();
	}

	public boolean tryCreateAdminUser() throws SQLException, LenException, ClassNotFoundException, FileNotFoundException, IOException {
		if (isAdminUser(false)) {
			return false;
		}
		int adminNameLength = adminName.length();
		int adminPassLength = adminPass.length();
		if (adminNameLength == 0 || adminPassLength == 0 || adminNameLength > 12 || adminPassLength > 18) {
			throw new LenException("Illegal length of name or password!");
		}
		// COMMAND:
		// INSERT INTO user_admin
		// (user_admin_name, user_admin_pass, user_admin_create_time)
		// VALUES
		// ();
		String sql = "INSERT INTO " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN + " ("
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME + ","
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_PASS + ","
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_CREATE_TIME + ") VALUES ( \""
				+ adminName + "\", \"" + adminPass + "\"," + "NOW())";
		System.out.println(sql);
		getConnection();
		statement.executeUpdate(sql);
		connection.close();
		return true;
	}

	public boolean tryCreateGenerUser() throws LenException, ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		if (isAdminUser(false)) {
			return false;
		}
		int adminNameLength = adminName.length();
		int adminPassLength = adminPass.length();
		if (adminNameLength == 0 || adminPassLength == 0 || adminNameLength > 12 || adminPassLength > 18) {
			throw new LenException("Illegal length of name or password!");
		}
		// COMMAND:
		// INSERT INTO user_gener
		// (user_gener_name, user_gener_pass, user_gener_create_time)
		// VALUES
		// ();
		String sql = "INSERT INTO " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER + " ("
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_NAME + ","
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_PASS + ","
				+ DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_CREATE_TIME + ") VALUES ( \""
				+ adminName + "\", \"" + adminPass + "\"," + "NOW())";
		getConnection();
		statement.executeUpdate(sql);
		connection.close();
		return true;
	}

	public boolean tryDeleteAdminUser() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		getConnection();
		String sql = "DELETE FROM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN
				+ " WHERE user_admin_name = \"" + adminName + "\"";
		statement.executeUpdate(sql);
		connection.close();
		return true;
	}

	public boolean tryDeleteGenerUser() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		getConnection();
		String sql = "DELETE FORM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER + " WHERE user_admin_name = "
				+ adminName;
		statement.executeUpdate(sql);
		connection.close();
		return true;
	}

	public String[][] getAllAdminUser() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {

		getConnection();
		String sql = "SELECT * FROM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN;
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.last();
		int row = resultSet.getRow();
		resultSet.beforeFirst();
		String[][] result = new String[row][3];
		int loop = 0;
		while (resultSet.next()) {
			result[loop][0] = Integer.toString(resultSet.getRow());
			result[loop][1] = resultSet
					.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME);
			result[loop][2] = resultSet
					.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_CREATE_TIME);
			loop++;
		}
		statement.close();
		return result;
	}

	public String[][] getAllGenerUser() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		getConnection();
		String sql = "SELECT * FROM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER;
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.last();
		int row = resultSet.getRow();
		resultSet.beforeFirst();
		String[][] result = new String[row][3];
		int loop = 0;
		while (resultSet.next()) {
			result[loop][0] = Integer.toString(resultSet.getRow());
			result[loop][1] = resultSet.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_NAME);
			result[loop][2] = resultSet
					.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_CREATE_TIME);
			loop++;
		}
		statement.close();
		return result;
	}

	public boolean isUser(boolean checkPassFlag) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		if (!isAdminUser(checkPassFlag) && !isGenerUser(checkPassFlag)) {
			return false;
		}
		return true;
	}
	public boolean isAdminUser(boolean checkPassFlag) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		getConnection();
		String sql = "SELECT * FROM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN;
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			if (checkPassFlag) {
				if (adminName.equals(
						resultSet.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME))
						&& adminPass.equals(resultSet
								.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_PASS))) {
					connection.close();
					return true;
				}
			} else {
				if (adminName.equals(
						resultSet.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_ADMIN_USER_ADDMIN_NAME))) {
					connection.close();
					return true;
				}
			}
		}
		connection.close();
		return false;
	}

	public boolean isGenerUser(boolean checkPassFlag) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		getConnection();
		String sqlCheckString = "SELECT * FROM " + DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER;
		ResultSet resultSet = statement.executeQuery(sqlCheckString);
		while (resultSet.next()) {
			if (checkPassFlag) {
				if (adminName.equals(
						resultSet.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_NAME))
						&& adminPass.equals(resultSet
								.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_PASS))) {
					connection.close();
					return true;
				}
			} else {
				if (adminName.equals(
						resultSet.getString(DatabaseFiled.DB_DATABASE_USER_INFO_TABLE_USER_GENER_USER_GENER_NAME))) {
					connection.close();
					return true;
				}
			}
		}
		return false;
	}

	

	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * @return the adminPass
	 */
	public String getAdminPass() {
		return adminPass;
	}

	/**
	 * @param adminPass the adminPass to set
	 */
	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

}
