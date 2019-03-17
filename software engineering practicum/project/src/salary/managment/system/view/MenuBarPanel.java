/**
 * 
 */
package salary.managment.system.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import salary.managment.system.tools.ImageTool;
import salary.managment.system.view.panel.PanelInputAndModification;

/**
 * @author Tortoise
 *
 */
public class MenuBarPanel extends JPanel {
	// 输入与修改按钮
	private static MenuButton buttonInputAndModification;
	// 输入与修改按钮tip
	private String tipInputAndModification;
	// 输入与修改按钮enable icon
	private ImageIcon iconInputAndModificationEnable;
	// 输入与修改按钮disable icon
	private ImageIcon iconInputAndModificationDisable;
	// 计算打印工资
	private static MenuButton buttonComputeAndPrintSalary;
	// 计算打印工资tip
	private String tipComputeAndPrintSalary;
	// 计算打印工资按钮enable icon
	private ImageIcon iconComputeAndPrintSalaryEnable;
	// 计算打印工资按钮disable icon
	private ImageIcon iconComputeAndPrintSalaryDisable;
	// 查询工资
	private static MenuButton buttonInquirySalary;
	// 查询工资tip
	private String tipInquirySalary;
	// 查询工资按钮enable icon
	private ImageIcon iconInquirySalaryEnable;
	// 查询工资按钮disable icon
	private ImageIcon iconInquirySalaryDisable;
	// 系统维护
	private static MenuButton buttonSystemMaintenance;
	// 系统维护tip
	private String tipSystemMaintenance;
	// 系统维护按钮enable icon
	private ImageIcon iconSystemMaintenanceEnable;
	// 系统维护按钮disable icon
	private ImageIcon iconSystemMaintenanceDisable;
	// 系统设置
	private static MenuButton buttonSystemSettings;
	// 系统设置tip
	private String tipSystemSettings;
	// 系统设置按钮enable icon
	private ImageIcon iconSystemSettingsEnable;
	// 系统设置按钮disable icon
	private ImageIcon iconSystemSettingsDisable;
	// 关于
	private static MenuButton buttonAbout;
	// 关于tip
	private String tipAbout;
	// 关于按钮enable icon
	private ImageIcon iconAboutEnable;
	// 关于按钮disable icon
	private ImageIcon iconAboutDisable;

	public MenuBarPanel() throws FileNotFoundException, IOException {
		getButtonTip();
		getButtonIcon();
		initialize();
		addButton();
		addListener();
	}

	private void initialize() {
		Dimension preferredSize = new Dimension(AppConstantsField.MAIN_WINDOW_MENUBAR_WIDTH,
				AppConstantsField.MAIN_WINDOW_MENUBAR_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		this.setLayout(new GridLayout(2, 1));
		// this.setVisible(true);
	}

	public void getButtonIcon() {

		// 输入与修改按钮enable icon
		iconInputAndModificationEnable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_MAIN_INPUT_MODIFICATION_ENABLE));
		// 输入与修改按钮disable icon
		iconInputAndModificationDisable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_MAIN_INPUT_MODIFICATION_DISABLE));

		// 计算打印工资按钮enable icon
		iconComputeAndPrintSalaryEnable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_COMPUTE_PRINT_ENABLE));

		// 计算打印工资按钮disable icon
		iconComputeAndPrintSalaryDisable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_COMPUTE_PRINT_DISABLE));

		// 查询工资按钮enable icon
		iconInquirySalaryEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_INQUIRY_ENABLE));

		// 查询工资按钮disable icon
		iconInquirySalaryDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_INQUIRY_DISABLE));

		// 系统维护按钮enable icon
		iconSystemMaintenanceEnable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SYSTEM_MAINTENANCE_ENABLE));
		// 系统维护按钮disable icon
		iconSystemMaintenanceDisable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SYSTEM_MAINTENANCE_DISABLE));

		// 系统设置按钮enable icon
		iconSystemSettingsEnable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SYSTEM_SETTINGS_ENABLE));

		// 系统设置按钮disable icon
		iconSystemSettingsDisable = ImageTool
				.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SYSTEM_SETTINGS_DISABLE));

		// 关于按钮enable icon
		iconAboutEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_ABOUT_ENABLE));
		// 关于按钮disable icon
		iconAboutDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_ABOUT_DISABLE));
	}

	private void addListener() {
		buttonInputAndModification.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationEnable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryDisable);
			buttonInquirySalary.setIcon(iconInquirySalaryDisable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceDisable);
			buttonSystemSettings.setIcon(iconSystemSettingsDisable);
			buttonAbout.setIcon(iconAboutDisable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelInputAndModification, BorderLayout.CENTER);
			MainFrame.panelMain.updateUI();
		});

		buttonComputeAndPrintSalary.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationDisable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryEnable);
			buttonInquirySalary.setIcon(iconInquirySalaryDisable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceDisable);
			buttonSystemSettings.setIcon(iconSystemSettingsDisable);
			buttonAbout.setIcon(iconAboutDisable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelComputeAndPrintSalary, BorderLayout.CENTER);
			MainFrame.panelMain.updateUI();
		});

		buttonInquirySalary.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationDisable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryDisable);
			buttonInquirySalary.setIcon(iconInquirySalaryEnable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceDisable);
			buttonSystemSettings.setIcon(iconSystemSettingsDisable);
			buttonAbout.setIcon(iconAboutDisable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelInauirySalary);
			MainFrame.panelMain.updateUI();
		});

		buttonSystemMaintenance.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationDisable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryDisable);
			buttonInquirySalary.setIcon(iconInquirySalaryDisable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceEnable);
			buttonSystemSettings.setIcon(iconSystemSettingsDisable);
			buttonAbout.setIcon(iconAboutDisable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelSystemMaintenance);
			MainFrame.panelMain.updateUI();
		});

		buttonSystemSettings.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationDisable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryDisable);
			buttonInquirySalary.setIcon(iconInquirySalaryDisable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceDisable);
			buttonSystemSettings.setIcon(iconSystemSettingsEnable);
			buttonAbout.setIcon(iconAboutDisable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelSystemSettings, BorderLayout.CENTER);
			MainFrame.panelMain.updateUI();
		});

		buttonAbout.addActionListener(e -> {
			buttonInputAndModification.setIcon(iconInputAndModificationDisable);
			buttonComputeAndPrintSalary.setIcon(iconComputeAndPrintSalaryDisable);
			buttonInquirySalary.setIcon(iconInquirySalaryDisable);
			buttonSystemMaintenance.setIcon(iconSystemMaintenanceDisable);
			buttonSystemSettings.setIcon(iconSystemSettingsDisable);
			buttonAbout.setIcon(iconAboutEnable);

			MainFrame.panelMain.removeAll();
			MainFrame.panelMain.add(MainFrame.panelAbout, BorderLayout.CENTER);
			MainFrame.panelMain.updateUI();
		});

	}

	private void addButton() {
		JPanel panelUp = new JPanel();
		panelUp.setSize(new Dimension(AppConstantsField.MAIN_WINDOW_MENUBAR_WIDTH / 2,
				AppConstantsField.MAIN_WINDOW_MENUBAR_HEIGHT / 2));
		panelUp.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelUp.setLayout(new FlowLayout());

		buttonInputAndModification = new MenuButton(iconInputAndModificationDisable, iconInputAndModificationEnable,
				iconInputAndModificationDisable, tipInputAndModification);
		buttonComputeAndPrintSalary = new MenuButton(iconComputeAndPrintSalaryDisable, iconComputeAndPrintSalaryEnable,
				iconComputeAndPrintSalaryDisable, tipComputeAndPrintSalary);
		buttonInquirySalary = new MenuButton(iconInquirySalaryDisable, iconInquirySalaryEnable,
				iconInquirySalaryDisable, tipInquirySalary);
		buttonSystemMaintenance = new MenuButton(iconSystemMaintenanceDisable, iconSystemMaintenanceEnable,
				iconSystemMaintenanceDisable, tipSystemMaintenance);
		buttonSystemSettings = new MenuButton(iconSystemSettingsDisable, iconSystemSettingsEnable,
				iconSystemSettingsDisable, tipSystemSettings);
		buttonAbout = new MenuButton(iconAboutDisable, iconAboutEnable, iconAboutDisable, tipAbout);

		panelUp.add(buttonInputAndModification);
		panelUp.add(buttonComputeAndPrintSalary);
		panelUp.add(buttonInquirySalary);
		panelUp.add(buttonSystemMaintenance);
		panelUp.add(buttonSystemSettings);

		JPanel panelDown = new JPanel();
		panelDown.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelDown.setLayout(new BorderLayout(0, 0));
		panelDown.add(buttonAbout, BorderLayout.SOUTH);
		this.add(panelUp);
		this.add(panelDown);
	}

	private void getButtonTip() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		// 獲取系统语言
		properties.load(new FileInputStream(PathManager.PATH_MAIN_SYSTEM_PROPERTIES));
		String language = properties.getProperty(AppConstantsField.KEY_LANGUAGE);

		if (language.equals(AppConstantsField.KEY_EN_US)) {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES));
		} else {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES));

		}
		tipInputAndModification = properties.getProperty(AppConstantsField.KEY_TIP_INPUT_AND_MODIFICATION,
				"input and modification");
		tipComputeAndPrintSalary = properties.getProperty(AppConstantsField.KEY_TIP_COMPUTE_AND_PRINT_SALARY,
				"compute and print salary");
		tipInquirySalary = properties.getProperty(AppConstantsField.KEY_TIP_INQUIRY_SALARY, "tip inquiry salary");
		tipSystemMaintenance = properties.getProperty(AppConstantsField.KEY_TIP_SYSTEM_MAINTENANCE,
				"tip system maintenance");
		tipSystemSettings = properties.getProperty(AppConstantsField.KEY_TIP_SYSTEM_SETTINGS, "tip system settings");
		tipAbout = properties.getProperty(AppConstantsField.KEY_TIP_ABOUT, "tip about");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MenuBarPanel pamel;
			try {
				pamel = new MenuBarPanel();
				pamel.setVisible(true);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

}
