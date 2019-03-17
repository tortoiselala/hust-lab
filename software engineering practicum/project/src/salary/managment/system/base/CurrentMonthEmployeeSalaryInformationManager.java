/**
 * 
 */
package salary.managment.system.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.javafx.collections.MappingChange.Map;
import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author Tortoise
 *
 */
public class CurrentMonthEmployeeSalaryInformationManager {
	private Connection connection;
	private Statement statement;
	private String tableName;

	public CurrentMonthEmployeeSalaryInformationManager() {
		Calendar calendar = Calendar.getInstance();
		setTableName(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH));
	}

	public CurrentMonthEmployeeSalaryInformationManager(int year, int month) {
		setTableName(year, month);
	}

	private void setTableName(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		tableName = "table_" + simpleDateFormat.format(calendar.getTime());

	}

	private void getConnection() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		// 注册JDBC驱动
		Class.forName(DatabaseFiled.JDBC_DRIVER);
		// 打开数据库
		connection = DriverManager.getConnection(DatabaseFiled.getDatabaseBaseInformationURL(),
				DatabaseFiled.DB_DATABASE_ROOT_NAME, DatabaseFiled.DB_DATABASE_ROOT_PASS);
		statement = connection.createStatement();
	}

	public String getTableName() {
		return tableName;
	}

	public String[] quiryByID(String ID)
			throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
		getConnection();
		int colNum = 9;
		String[] result = new String[colNum];
		String sql = "SELECT * FROM " + tableName + " WHERE employee_id = \"" + ID + "\"";
		ResultSet resultSet = statement.executeQuery(sql);
		if (!resultSet.next()) {
			result[0] = "null";
		} else {
			result[0] = resultSet.getString(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID);
			result[1] = resultSet.getString(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_EMPLOYEE_NAME);
			result[2] = resultSet.getString(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY);
			result[3] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE));
			result[4] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE));
			result[5] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE));
			result[6] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE));
			result[7] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE));
			result[8] = Double.toString(resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY));
		}
		System.out.println(Arrays.toString(result));
		connection.close();
		return result;
	}

	public void setInfo(String[] info) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		getConnection();
		String sql = "UPDATE " + tableName + " SET "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID + " = "
				+ "\"" + info[1] + "\", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_EMPLOYEE_NAME + " = "
				+ "\"" + info[2] + "\", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CATEGORY + " = "
				+ "\"" + info[3] + "\", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE + " = "
				+ info[4] + ", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE
				+ " = " + info[5] + ", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE + " = "
				+ info[6] + ", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE + " = "
				+ info[7] + ", "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE + " = "
				+ info[8];
		System.out.println(sql);
		statement.executeUpdate(sql);
		connection.close();
	}

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		CurrentMonthEmployeeSalaryInformationManager currentMonthEmployeeSalaryInformationManager;
		try {
			currentMonthEmployeeSalaryInformationManager = new CurrentMonthEmployeeSalaryInformationManager();
			String[] result = currentMonthEmployeeSalaryInformationManager.quiryByID("62042119990814569");
			for (int i = 0; i < 8; i++) {
				System.out.println(result[i]);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
