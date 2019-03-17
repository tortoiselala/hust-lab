/**
 * 
 */
package salary.managment.system.submenubar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.sun.beans.editors.ColorEditor;

import salary.managment.system.tools.ImageTool;
import salary.managment.system.view.AppConstantsField;
import salary.managment.system.view.MenuButton;
import salary.managment.system.view.PathManager;
import salary.managment.system.view.panel.PanelInquirySalary;
import salary.managment.system.view.panel.func.PanelFuncInquirySalaryDetails;
import salary.managment.system.view.panel.func.PanelFuncSalaryDepartment;

/**
 * @author Tortoise
 *
 */
public class SubMenuBarInquirySalary extends JPanel {
	// 查询工资明细按钮
	private MenuButton buttonInquirySalaryDetails;
	// 查询工资明细 tip
	private String tipInquirySalaryDetails;
	// 查询部门工资按钮
	private MenuButton buttonSalaryDepartment;
	// 查询部门工资 tip
	private String tipSalaryDepartment;

	// 按钮disable图标
	private ImageIcon buttonIconEnable;
	// 按钮disable图标
	private ImageIcon buttonIconDisable;

	public SubMenuBarInquirySalary() throws FileNotFoundException, IOException {
		Dimension preferredSize = new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH / 2,
				AppConstantsField.SUB_MENUBAR_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		this.setLayout(new GridLayout(2, 1));
		setButtonTip();
		addButton();
		addButtonListener();
	}

	public void setButtonTip() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		// 獲取系统语言
		properties.load(new FileInputStream(PathManager.PATH_MAIN_SYSTEM_PROPERTIES));
		String language = properties.getProperty(AppConstantsField.KEY_LANGUAGE);

		if (language.equals(AppConstantsField.KEY_EN_US)) {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES));
		} else {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES));

		}

		tipInquirySalaryDetails = properties.getProperty(AppConstantsField.KEY_TIP_INQUIRT_SALARY_DETAILS,
				"inquiry salary details");
		tipSalaryDepartment = properties.getProperty(AppConstantsField.KEY_TIP_SALARY_DEPARTMENT, "salary deparment");

		buttonIconEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_ENABLE));
		buttonIconDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_DISABLE));

	}

	public void addButton() {
		JPanel panelUp = new JPanel();
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));
		panelUp.setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonInquirySalaryDetails = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipInquirySalaryDetails);
		buttonInquirySalaryDetails.setText(tipInquirySalaryDetails);
		buttonSalaryDepartment = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipSalaryDepartment);
		buttonSalaryDepartment.setText(tipSalaryDepartment);
		panelUp.add(buttonInquirySalaryDetails);
		panelUp.add(buttonSalaryDepartment);
		panelUp.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);

		JPanel panelDown = new JPanel();
		panelDown.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelDown.setLayout(new BorderLayout(0, 0));
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));

		this.add(panelUp);
		this.add(panelDown);
	}

	private void addButtonListener() {
		buttonInquirySalaryDetails.addActionListener(e -> {
			buttonInquirySalaryDetails.setIcon(buttonIconEnable);
			buttonSalaryDepartment.setIcon(buttonIconDisable);

			PanelInquirySalary.mainPanel.removeAll();
			PanelInquirySalary.mainPanel.add(new PanelFuncInquirySalaryDetails());
			PanelInquirySalary.mainPanel.updateUI();
		});

		buttonSalaryDepartment.addActionListener(e -> {
			buttonInquirySalaryDetails.setIcon(buttonIconDisable);
			buttonSalaryDepartment.setIcon(buttonIconEnable);

			PanelInquirySalary.mainPanel.removeAll();
			PanelInquirySalary.mainPanel.add(new PanelFuncSalaryDepartment());
			PanelInquirySalary.mainPanel.updateUI();
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
