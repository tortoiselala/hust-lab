/**
 * 
 */
package salary.managment.system.view.panel.func;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.bcel.internal.generic.INEG;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.rowset.internal.Row;

import salary.managment.system.base.DatabaseFiled;

/**
 * @author Tortoise
 *
 */
public class PanelFuncPayRoll extends JLabel {
	private String tipTitle;
	private String tipDate;
	private String tipName;
	private String tipBaseSalary;
	private String tipBonus;
	private String tipSickFee;
	private String tipChildCareFee;
	private String tipRentFee;
	private String tipWaterEleFee;

	private String tipPayrollSubtotal;
	private String tipSubtotal;
	private String tipRealWage;

	private String tableName;

	private String date;
	Connection connection;
	Statement statement;

	public PanelFuncPayRoll() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		setTip();
		Calendar calendar = Calendar.getInstance();
		setTableName(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH));
		createTable();
	}

	private void setTip() {
		tipTitle = "    个人工资条(单位:元)";
		tipDate = "日期";
		tipName = "姓名";
		tipBaseSalary = "基本工资";
		tipBonus = "奖金";
		tipSickFee = "病事扣款";
		tipChildCareFee = "托儿费";
		tipRentFee = "房费";
		tipWaterEleFee = "水电费";

		tipPayrollSubtotal = "应发小计";
		tipSubtotal = "小计";
		tipRealWage = "实发";
	}

	public void getConnect() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		// 注册JDBC驱动
		Class.forName(DatabaseFiled.JDBC_DRIVER);
		// 打开数据库
		connection = DriverManager.getConnection(DatabaseFiled.getDatabaseBaseInformationURL(),
				DatabaseFiled.DB_DATABASE_ROOT_NAME, DatabaseFiled.DB_DATABASE_ROOT_PASS);
		statement = connection.createStatement();

	}

	private void setTableName(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		date = simpleDateFormat.format(calendar.getTime());
		tableName = "table_" + date;

	}

	public String getTableName() {
		return tableName;
	}

	public void createTable() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		String[][] datas = getInfo();
		String[] titles = { tipDate, tipName, tipBaseSalary, tipBonus, tipSickFee, tipPayrollSubtotal, tipChildCareFee,
				tipRentFee, tipWaterEleFee, tipSubtotal, tipRealWage };
		JTable table = new JTable(datas, titles);
		JLabel title = new JLabel(tipTitle);
		this.setLayout(new BorderLayout());
		this.add(title, BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		// table.setFillsViewportHeight(true);
	}

	public String[][] getInfo() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		getConnect();
		ArrayList<String[]> result = new ArrayList<String[]>();
		String sql = "SELECT * FROM " + tableName;
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			String[] row = new String[11];
			double baseSalary;
			double bonus;
			double sickFee;
			double payrollSubtotal;
			double childCareFee;
			double rentFee;
			double waterEleFee;
			double subtotal;
			double realWage;
			row[0] = date;

			row[1] = resultSet.getString(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_EMPLOYEE_NAME);

			baseSalary = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY);
			row[2] = Double.toString(baseSalary);

			bonus = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE);
			row[3] = Double.toString(bonus);

			sickFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE);
			row[4] = Double.toString(sickFee);

			payrollSubtotal = baseSalary + bonus - sickFee;
			row[5] = Double.toString(payrollSubtotal);

			childCareFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE);
			row[6] = Double.toString(childCareFee);

			rentFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE);
			row[7] = Double.toString(rentFee);

			waterEleFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE);
			row[8] = Double.toString(waterEleFee);

			subtotal = childCareFee + rentFee + waterEleFee;
			row[9] = Double.toString(subtotal);

			realWage = payrollSubtotal - subtotal;
			row[10] = Double.toString(realWage);

			result.add(row);
		}
		int size = result.size();
		String[][] returnResult = new String[size][11];
		result.toArray(returnResult);
		return returnResult;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		JFrame jFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
		jFrame.setLocation(screenSize.width / 2 - screenSize.width / 4, screenSize.height / 2 - screenSize.height / 4);
		jFrame.add(new PanelFuncPayRoll());
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

	}

}
