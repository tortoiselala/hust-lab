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
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import salary.managment.system.tools.ImageTool;
import salary.managment.system.view.AppConstantsField;
import salary.managment.system.view.MenuButton;
import salary.managment.system.view.PathManager;
import salary.managment.system.view.panel.PanelSystemMaintenance;
import salary.managment.system.view.panel.func.FuncBackupDatabase;
import salary.managment.system.view.panel.func.PanelFuncAddEmployee;
import salary.managment.system.view.panel.func.PanelFuncDeleteEmployee;

/**
 * @author Tortoise
 *
 */
public class SubMenuBarSystemMaintenance extends JPanel {
	// 数据备份按钮
	private MenuButton buttonDatabaseBackup;
	// 增加职工
	private MenuButton buttonAddEmployeeBaseInformation;
	// 减少职工
	private MenuButton buttonDecreaseEmployeeBaseInformation;
	// 更新固定数据
	private MenuButton buttonUpdateEmployeeBaseInformation;

	// 数据备份按钮
	private String tipDatabaseBackup;
	// 增加职工
	private String tipAddEmployeeBaseInformation;
	// 减少职工
	private String tipDecreaseEmployeeBaseInformation;
	// 更新固定数据
	private String tipUpdateEmployeeBaseInformation;

	// 按钮disable图标
	private ImageIcon buttonIconEnable;
	// 按钮disable图标
	private ImageIcon buttonIconDisable;

	public SubMenuBarSystemMaintenance() throws FileNotFoundException, IOException {
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

		tipDatabaseBackup = properties.getProperty(AppConstantsField.KEY_TIP_DATABASE_BACKUP, "database backup");
		tipAddEmployeeBaseInformation = properties.getProperty(AppConstantsField.KEY_TIP_ADD_EMPLOYEE_BASE_INFORMATION,
				"add employee base information");
		tipDecreaseEmployeeBaseInformation = properties.getProperty(
				AppConstantsField.KEY_TIP_DECREASE_EMPLOYEE_BASE_INFORMATION, "decrease employee base information");
		tipUpdateEmployeeBaseInformation = properties.getProperty(
				AppConstantsField.KEY_TIP_UPDATE_EMPLOYEE_BASE_INFORMATION, "update employee base information");
		buttonIconEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_ENABLE));
		buttonIconDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_DISABLE));
	}

	public void addButton() {
		JPanel panelUp = new JPanel();
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));
		panelUp.setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonDatabaseBackup = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipDatabaseBackup);
		buttonDatabaseBackup.setText(tipDatabaseBackup);
		buttonAddEmployeeBaseInformation = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipAddEmployeeBaseInformation);
		buttonAddEmployeeBaseInformation.setText(tipAddEmployeeBaseInformation);
		buttonDecreaseEmployeeBaseInformation = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipDecreaseEmployeeBaseInformation);
		buttonDecreaseEmployeeBaseInformation.setText(tipDecreaseEmployeeBaseInformation);
		buttonUpdateEmployeeBaseInformation = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipUpdateEmployeeBaseInformation);
		buttonUpdateEmployeeBaseInformation.setText(tipUpdateEmployeeBaseInformation);

		panelUp.add(buttonDatabaseBackup);
		panelUp.add(buttonAddEmployeeBaseInformation);
		panelUp.add(buttonDecreaseEmployeeBaseInformation);
		// panelUp.add(buttonUpdateEmployeeBaseInformation);
		panelUp.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);

		JPanel panelDown = new JPanel();
		panelDown.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelDown.setLayout(new BorderLayout(0, 0));
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));

		this.add(panelUp);
		this.add(panelDown);
	}

	public void addButtonListener() {
		buttonDatabaseBackup.addActionListener(e -> {
			buttonDatabaseBackup.setIcon(buttonIconEnable);
			buttonAddEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonDecreaseEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonUpdateEmployeeBaseInformation.setIcon(buttonIconDisable);

			FuncBackupDatabase funcBackupDatabase = new FuncBackupDatabase();
			funcBackupDatabase.backup();

		});
		buttonAddEmployeeBaseInformation.addActionListener(e -> {
			buttonDatabaseBackup.setIcon(buttonIconDisable);
			buttonAddEmployeeBaseInformation.setIcon(buttonIconEnable);
			buttonDecreaseEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonUpdateEmployeeBaseInformation.setIcon(buttonIconDisable);

			PanelSystemMaintenance.mainPanel.removeAll();
			PanelSystemMaintenance.mainPanel.add(PanelSystemMaintenance.panelFuncAddEmployee);
			PanelSystemMaintenance.mainPanel.updateUI();

		});
		buttonDecreaseEmployeeBaseInformation.addActionListener(e -> {
			buttonDatabaseBackup.setIcon(buttonIconDisable);
			buttonAddEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonDecreaseEmployeeBaseInformation.setIcon(buttonIconEnable);
			buttonUpdateEmployeeBaseInformation.setIcon(buttonIconDisable);

			PanelSystemMaintenance.mainPanel.removeAll();
			PanelSystemMaintenance.mainPanel.add(new PanelFuncDeleteEmployee());
			PanelSystemMaintenance.mainPanel.updateUI();
		});

		buttonUpdateEmployeeBaseInformation.addActionListener(e -> {
			buttonDatabaseBackup.setIcon(buttonIconDisable);
			buttonAddEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonDecreaseEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonUpdateEmployeeBaseInformation.setIcon(buttonIconEnable);

		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
