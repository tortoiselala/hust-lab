/**
 * 
 */
package salary.managment.system.view.panel.func;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import salary.managment.system.base.DatabaseFiled;
import salary.managment.system.view.AppConstantsField;

/**
 * @author Tortoise
 *
 */
public class PanelFuncSalaryDepartment extends JPanel {
	private JLabel labelTitle;
	private String tipTitle;

	private JLabel labelDepartmentID;
	private String tipDepartmentID;
	private JTextField textFieldDepartmentID;

	private JLabel labelBaseSalary;
	private String tipBaseSalary;
	private JLabel textFieldBaseSalary;

	private JLabel labelBonus;
	private String tipBonus;
	private JLabel textFieldBonus;

	private JLabel labelSickFee;
	private String tipSickFee;
	private JLabel textFieldSickFee;

	private JLabel labelPayrollSubtotal;
	private String tipPayrollSubtotal;
	private JLabel textFieldPayrollSubtotal;

	private JLabel labelChildCareFee;
	private String tipChildCareFee;
	private JLabel textFieldChildCareFee;

	private JLabel labelRentFee;
	private String tipRentFee;
	private JLabel textFieldRentFee;

	private JLabel labelWaterEleFee;
	private String tipWaterEleFee;
	private JLabel textFieldWaterEleFee;

	private JLabel labelRealWage;
	private String tipRealWage;
	private JLabel textFieldRealWage;

	private String tableName;

	private String date;

	private String departmentID;

	private JButton buttonInquiry;

	public PanelFuncSalaryDepartment() {

		setTip();
		Calendar calendar = Calendar.getInstance();
		setTableName(calendar.get(Calendar.YEAR), calendar.get(calendar.MONTH));
		addLabelAndTextField();
		addListener();
	}

	private void setTip() {
		tipTitle = "     部门工资查询";
		tipDepartmentID = "部门编号：";
		tipBaseSalary = "基本工资：";
		tipBonus = "奖金：";
		tipSickFee = "病事扣款：";
		tipPayrollSubtotal = "应发小计：";
		tipChildCareFee = "托儿费：";
		tipRentFee = "房费：";
		tipWaterEleFee = "水电费：";
		tipRealWage = "实发：";
	}

	private void setTableName(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		date = simpleDateFormat.format(calendar.getTime());
		tableName = "table_" + date;
	}

	private void addLabelAndTextField() {
		labelTitle = new JLabel(tipTitle);
		labelDepartmentID = new JLabel(tipDepartmentID);
		labelBaseSalary = new JLabel(tipBaseSalary);
		labelBonus = new JLabel(tipBonus);
		labelSickFee = new JLabel(tipSickFee);
		labelPayrollSubtotal = new JLabel(tipPayrollSubtotal);
		labelChildCareFee = new JLabel(tipChildCareFee);
		labelRentFee = new JLabel(tipRentFee);
		labelWaterEleFee = new JLabel(tipWaterEleFee);
		labelRealWage = new JLabel(tipRealWage);
		buttonInquiry = new JButton("查询");

		textFieldDepartmentID = new JTextField();
		textFieldBaseSalary = new JLabel();
		textFieldBonus = new JLabel();
		textFieldSickFee = new JLabel();
		textFieldPayrollSubtotal = new JLabel();
		textFieldChildCareFee = new JLabel();
		textFieldRentFee = new JLabel();
		textFieldWaterEleFee = new JLabel();
		textFieldRealWage = new JLabel();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int targetPanelWidth = screenSize.width / 2;
		int targetPanelHeight = screenSize.height / 2;

		this.setLayout(new GridBagLayout());
		this.add(labelTitle,
				getComponentConstraints(100, 100, 0, 0, 10, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));
		labelTitle.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelDepartmentID,
				getComponentConstraints(100, 100, 1, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldDepartmentID,
				getComponentConstraints(100, 100, 3, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldDepartmentID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));

		this.add(buttonInquiry,
				getComponentConstraints(100, 100, 5, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelBaseSalary,
				getComponentConstraints(100, 100, 1, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldBaseSalary,
				getComponentConstraints(100, 100, 3, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldBaseSalary.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelBonus,
				getComponentConstraints(100, 100, 5, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldBonus,
				getComponentConstraints(100, 100, 7, 4, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldBonus.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelSickFee,
				getComponentConstraints(100, 100, 1, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldSickFee,
				getComponentConstraints(100, 100, 3, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldSickFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelPayrollSubtotal,
				getComponentConstraints(100, 100, 5, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldPayrollSubtotal,
				getComponentConstraints(100, 100, 7, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldPayrollSubtotal.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelChildCareFee,
				getComponentConstraints(100, 100, 1, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldChildCareFee,
				getComponentConstraints(100, 100, 3, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldChildCareFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelRentFee,
				getComponentConstraints(100, 100, 5, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldRentFee,
				getComponentConstraints(100, 100, 7, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldRentFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelWaterEleFee,
				getComponentConstraints(100, 100, 1, 10, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldWaterEleFee,
				getComponentConstraints(100, 100, 3, 10, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldWaterEleFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		this.add(labelRealWage,
				getComponentConstraints(100, 100, 5, 10, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldRealWage,
				getComponentConstraints(100, 100, 7, 10, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));
		textFieldRealWage.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
	}

	public GridBagConstraints getComponentConstraints(int weightx, int weighty, int gridx, int gridy, int gridwidth,
			int gridheight, int fill, int ancher) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.fill = fill;
		constraints.anchor = ancher;

		return constraints;
	}

	public String[] getInfo() throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
		double sumBaseSalary = 0;
		double sumBonus = 0;
		double sumSickFee = 0;
		double sumPayrollSubtotal = 0;
		double sumChildCareFee = 0;
		double sumRentFee = 0;
		double sumWaterEleFee = 0;
		double sumRealWage = 0;
		int resultRow = 10;

		// 注册JDBC驱动
		Class.forName(DatabaseFiled.JDBC_DRIVER);
		// 打开数据库
		Connection connection = DriverManager.getConnection(DatabaseFiled.getDatabaseBaseInformationURL(),
				DatabaseFiled.DB_DATABASE_ROOT_NAME, DatabaseFiled.DB_DATABASE_ROOT_PASS);
		Statement statement = connection.createStatement();

		String sql = "SELECT * FROM " + tableName + " WHERE "
				+ DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_DEPARTMENT_ID + " = "
				+ this.departmentID;
		ResultSet resultSet = statement.executeQuery(sql);
		boolean status = false;
		if (!resultSet.next()) {
			status = true;
		} else {
			double baseSalary = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY);
			double bonus = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE);

			double sickFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE);

			double payrollSubtotal = baseSalary + bonus - sickFee;

			double childCareFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE);
			double rentFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE);
			double waterEleFee = resultSet.getDouble(
					DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE);

			double subtotal = childCareFee + rentFee + waterEleFee;
			double realWage = payrollSubtotal - subtotal;

			sumBaseSalary += baseSalary;
			sumBonus += bonus;
			sumSickFee += sickFee;
			sumPayrollSubtotal += payrollSubtotal;
			sumChildCareFee = childCareFee;
			sumRentFee += rentFee;
			sumWaterEleFee += waterEleFee;
			sumRealWage += realWage;

			while (resultSet.next()) {

				double baseSalary1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_BASESALARY);
				double bonus1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_OTHER_FEE);

				double sickFee1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_SICK_FEE);

				double payrollSubtotal1 = baseSalary1 + bonus1 - sickFee1;

				double childCareFee1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_CHILD_CARE_FEE);
				double rentFee1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_RENT_FEE);
				double waterEleFee1 = resultSet.getDouble(
						DatabaseFiled.DB_DATABASE_BASE_INFORMATION_TABLE_EMPLOYEE_BASIC_INFORMATION_FORM_WATER_ELE_FEE);

				double subtotal1 = childCareFee1 + rentFee1 + waterEleFee1;
				double realWage1 = payrollSubtotal1 - subtotal1;

				sumBaseSalary += baseSalary1;
				sumBonus += bonus1;
				sumSickFee += sickFee1;
				sumPayrollSubtotal += payrollSubtotal1;
				sumChildCareFee = childCareFee1;
				sumRentFee += rentFee1;
				sumWaterEleFee += waterEleFee1;
				sumRealWage += realWage1;
			}
		}
		if (status) {
			return new String[] { null };
		}
		return new String[] { this.departmentID, Double.toString(sumBaseSalary), Double.toString(sumBonus),
				Double.toString(sumSickFee), Double.toString(sumPayrollSubtotal), Double.toString(sumChildCareFee),
				Double.toString(sumRentFee), Double.toString(sumWaterEleFee), Double.toString(sumRealWage), };
	}

	public void addListener() {
		buttonInquiry.addActionListener(e -> {
			try {
				this.departmentID = textFieldDepartmentID.getText();
				String[] result = getInfo();
				if (result[0] == null) {
					JOptionPane.showMessageDialog(null, "不存在该部门编号", "错误", JOptionPane.OK_CANCEL_OPTION);
				} else {
					textFieldBaseSalary.setText(result[1]);
					textFieldBonus.setText(result[2]);
					textFieldSickFee.setText(result[3]);
					textFieldPayrollSubtotal.setText(result[4]);
					textFieldChildCareFee.setText(result[5]);
					textFieldRentFee.setText(result[6]);
					textFieldWaterEleFee.setText(result[7]);
					textFieldRealWage.setText(result[8]);
				}

			} catch (ClassNotFoundException | SQLException | IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
		jFrame.setLocation(screenSize.width / 2 - screenSize.width / 4, screenSize.height / 2 - screenSize.height / 4);
		jFrame.add(new PanelFuncSalaryDepartment());

		jFrame.setVisible(true);
	}

}
