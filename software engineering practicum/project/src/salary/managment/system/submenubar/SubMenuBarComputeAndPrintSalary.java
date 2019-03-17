/**
 * 
 */
package salary.managment.system.submenubar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore.PrivateKeyEntry;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import salary.managment.system.tools.ImageTool;
import salary.managment.system.view.AppConstantsField;
import salary.managment.system.view.MenuButton;
import salary.managment.system.view.PathManager;
import salary.managment.system.view.panel.PanelComputeAndPrintSalary;
import salary.managment.system.view.panel.func.PanelFuncPayRoll;
import salary.managment.system.view.panel.func.PanelFuncPaySchedule;
import salary.managment.system.view.panel.func.PanelFuncSalarySummary;

/**
 * @author Tortoise
 *
 */
public class SubMenuBarComputeAndPrintSalary extends JPanel {
	// 计算个人工资按钮
	private MenuButton buttonPersonalSalary;
	// 计算个人工资 tip
	private String tipPersonalSalary;
	// 工资条按钮
	private MenuButton buttonPayroll;
	// 工资条 tip
	private String tipPayroll;
	// 工资明细表
	private MenuButton buttonPaySchedule;
	// 工资明细表 tip
	private String tipPaySchedule;
	// 工资汇总表
	private MenuButton buttonSalarySummary;
	// 工资汇总表 tip
	private String tipSalarySummary;
	// 工资分配表
	private MenuButton buttonSalaryDistribution;
	// 工资分配表 tip
	private String tipSalaryDistribution;

	// 按钮disable图标
	private ImageIcon buttonIconEnable;
	// 按钮disable图标
	private ImageIcon buttonIconDisable;

	public SubMenuBarComputeAndPrintSalary() throws FileNotFoundException, IOException {
		Dimension preferredSize = new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH / 2,
				AppConstantsField.SUB_MENUBAR_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		this.setLayout(new GridLayout(2, 1));
		setButtonTip();
		addButton();
		addButtonListner();

	}

	private void setButtonTip() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		// 獲取系统语言
		properties.load(new FileInputStream(PathManager.PATH_MAIN_SYSTEM_PROPERTIES));
		String language = properties.getProperty(AppConstantsField.KEY_LANGUAGE);

		if (language.equals(AppConstantsField.KEY_EN_US)) {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES));
		} else {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES));

		}

		tipPersonalSalary = properties.getProperty(AppConstantsField.KEY_TIP_PERSONAL_SALARY, "personal salary");
		tipPayroll = properties.getProperty(AppConstantsField.KEY_TIP_PAYROLL, "payroll");
		tipPaySchedule = properties.getProperty(AppConstantsField.KEY_TIP_PAY_SCHEDULE, "pay schedule");
		tipSalarySummary = properties.getProperty(AppConstantsField.KEY_TIP_SALARY_SUMMARY, "salary summary");
		tipSalaryDistribution = properties.getProperty(AppConstantsField.KEY_TIP_SALARY_DISTRIBUTION,
				"salary distribution");
	}

	private void addButton() {
		buttonIconEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_ENABLE));
		buttonIconDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_DISABLE));

		JPanel panelUp = new JPanel();
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));
		panelUp.setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonPersonalSalary = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipPersonalSalary);
		buttonPersonalSalary.setText(tipPersonalSalary);
		buttonPayroll = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable, tipPayroll);
		buttonPayroll.setText(tipPayroll);
		buttonPaySchedule = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable, tipPaySchedule);
		buttonPaySchedule.setText(tipPaySchedule);
		buttonSalarySummary = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable, tipSalarySummary);
		buttonSalarySummary.setText(tipSalarySummary);
		buttonSalaryDistribution = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipSalaryDistribution);
		buttonSalaryDistribution.setText(tipSalaryDistribution);
		// panelUp.add(buttonPersonalSalary);
		panelUp.add(buttonPayroll);
		panelUp.add(buttonPaySchedule);
		panelUp.add(buttonSalarySummary);
		// panelUp.add(buttonSalaryDistribution);
		panelUp.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);

		JPanel panelDown = new JPanel();
		panelDown.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelDown.setLayout(new BorderLayout(0, 0));
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));

		this.add(panelUp);
		this.add(panelDown);
	}

	private void addButtonListner() {
		buttonPersonalSalary.addActionListener(e -> {
			buttonPersonalSalary.setIcon(buttonIconEnable);
			buttonPayroll.setIcon(buttonIconDisable);
			buttonPaySchedule.setIcon(buttonIconDisable);
			buttonSalarySummary.setIcon(buttonIconDisable);
			buttonSalaryDistribution.setIcon(buttonIconDisable);

		});
		buttonPayroll.addActionListener(e -> {
			buttonPersonalSalary.setIcon(buttonIconDisable);
			buttonPayroll.setIcon(buttonIconEnable);
			buttonPaySchedule.setIcon(buttonIconDisable);
			buttonSalarySummary.setIcon(buttonIconDisable);
			buttonSalaryDistribution.setIcon(buttonIconDisable);

			PanelComputeAndPrintSalary.mainPanel.removeAll();
			try {
				PanelComputeAndPrintSalary.mainPanel.add(new PanelFuncPayRoll());
			} catch (ClassNotFoundException | SQLException | IOException e1) {

				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.OK_CANCEL_OPTION);
			}

			PanelComputeAndPrintSalary.mainPanel.updateUI();
		});
		buttonPaySchedule.addActionListener(e -> {
			buttonPersonalSalary.setIcon(buttonIconDisable);
			buttonPayroll.setIcon(buttonIconDisable);
			buttonPaySchedule.setIcon(buttonIconEnable);
			buttonSalarySummary.setIcon(buttonIconDisable);
			buttonSalaryDistribution.setIcon(buttonIconDisable);

			PanelComputeAndPrintSalary.mainPanel.removeAll();
			try {
				PanelComputeAndPrintSalary.mainPanel.add(new PanelFuncPaySchedule());
			} catch (ClassNotFoundException | SQLException | IOException e1) {

				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.OK_CANCEL_OPTION);
			}

			PanelComputeAndPrintSalary.mainPanel.updateUI();
		});
		buttonSalarySummary.addActionListener(e -> {
			buttonPersonalSalary.setIcon(buttonIconDisable);
			buttonPayroll.setIcon(buttonIconDisable);
			buttonPaySchedule.setIcon(buttonIconDisable);
			buttonSalarySummary.setIcon(buttonIconEnable);
			buttonSalaryDistribution.setIcon(buttonIconDisable);

			PanelComputeAndPrintSalary.mainPanel.removeAll();
			try {
				PanelComputeAndPrintSalary.mainPanel.add(new PanelFuncSalarySummary());
			} catch (ClassNotFoundException | SQLException | IOException e1) {

				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.OK_CANCEL_OPTION);
			}

			PanelComputeAndPrintSalary.mainPanel.updateUI();
		});
		buttonSalaryDistribution.addActionListener(e -> {
			buttonPersonalSalary.setIcon(buttonIconDisable);
			buttonPayroll.setIcon(buttonIconDisable);
			buttonPaySchedule.setIcon(buttonIconDisable);
			buttonSalarySummary.setIcon(buttonIconDisable);
			buttonSalaryDistribution.setIcon(buttonIconEnable);
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
