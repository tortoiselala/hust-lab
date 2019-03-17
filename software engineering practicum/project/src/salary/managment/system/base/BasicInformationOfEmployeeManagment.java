/**
 * 
 */
package salary.managment.system.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import java.util.ArrayList;

/**
 * @author Tortoise
 *
 */
public class BasicInformationOfEmployeeManagment extends BasicInformationOfEmployee {
	public static void main(String[] args) {
		BasicInformationOfEmployeeManagment test;
		try {
			test = new BasicInformationOfEmployeeManagment("01", "620421199908145140", "tort", man, (short) 10, 10000,
					"test");
			test.deleteEmployeeInformation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Connection connection;
	private Statement statement;

	/**
	 * @param id
	 * @throws Exception
	 */
	public BasicInformationOfEmployeeManagment(String id, String name) throws Exception {
		super(id, name);
	}

	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param age
	 * @param baseSalary
	 * @param category
	 * @throws Exception
	 */
	public BasicInformationOfEmployeeManagment(String departmentID, String id, String name, short sex, short age,
			double baseSalary, String category) throws Exception {
		super(id, name, sex, age, baseSalary, category);
		setDepartmentID(departmentID);
	}

	private void getConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		// 注册JDBC驱动
		Class.forName(DatabaseFiled.JDBC_DRIVER);
		// 打开数据库
		connection = DriverManager.getConnection(DatabaseFiled.getDatabaseBaseInformationURL(),
				DatabaseFiled.DB_DATABASE_ROOT_NAME, DatabaseFiled.DB_DATABASE_ROOT_PASS);
		statement = connection.createStatement();
	}

	public boolean isEmployeeInformationExits()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		getConnection();
		String sql = "SELECT * FROM "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM;
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			if (this.getId().equals(resultSet
					.getString(DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID))) {
				return true;
			}
		}
		connection.close();
		return false;
	}

	public boolean deleteEmployeeInformation()
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		getConnection();
		String sql = "DELETE FROM " + DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM
				+ " WHERE " + DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID
				+ " = \"" + getId() + "\"";
		statement.executeUpdate(sql);
		connection.close();
		return true;
	}

	public void addOrUpdateEmployee() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		String sql;

		if (isEmployeeInformationExits()) {
			sql = "UPDATE " + DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM + " SET "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_NAME + " = \""
					+ getName() + "\", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SEX + " = \""
					+ getSex() + "\", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_AGE + " = \""
					+ getAge() + "\", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY
					+ " = \"" + getBaseSalary() + "\", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY
					+ " = \"" + getCategory() + "\" " + " WHERE "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID + " = \""
					+ getId() + "\"";
			System.out.println("update");
			System.out.println(sql);
		} else {
			sql = "INSERT INTO " + DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM
					+ "(" + DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_NAME + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SEX + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_AGE + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY + ", "
					+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID
					+ ") VALUES  (" + " \"" + getId() + "\", " + " \"" + getName() + "\", " + getSex() + ", " + getAge()
					+ ", " + getBaseSalary() + ", " + " \"" + getCategory() + "\" , \"" + getDepartmentID() + "\" "
					+ ")";
			System.out.println("insert:" + sql);

		}
		getConnection();
		statement.executeUpdate(sql);
		connection.close();
	}

	public List<String[]> getAllEmployeeInformation() throws SQLException {
		List<String[]> result = new ArrayList<String[]>();
		String sql = "SELECT * FROM "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM;
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String[] tmp = new String[6];
			tmp[0] = resultSet
					.getString(DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_ID);
			tmp[1] = resultSet
					.getString(DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_NAME);
			tmp[2] = Short.toString(resultSet
					.getShort(DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SEX));
			tmp[3] = Short.toString(resultSet
					.getShort(DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_AGE));
			tmp[4] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY));
			tmp[5] = resultSet.getString(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY);
			result.add(tmp);
		}
		return result;
	}
}
