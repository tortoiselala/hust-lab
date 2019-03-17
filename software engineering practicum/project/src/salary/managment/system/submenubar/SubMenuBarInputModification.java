/**
 * 
 */
package salary.managment.system.submenubar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import salary.managment.system.view.MenuButton;
import salary.managment.system.tools.ImageTool;
import salary.managment.system.view.AppConstantsField;
import salary.managment.system.view.PathManager;

/**
 * @author Tortoise
 *
 */
public class SubMenuBarInputModification extends JPanel {

	// 职工基本信息录入按钮
	private MenuButton buttonInputEmployeeBaseInformation;
	// 职工基本信息录入 tip
	private String tipInputEmployeeBaseInformation;
	// 职工基本信息修改按钮
	private MenuButton buttonModifyEmployeeBaseInformation;
	// 职工基本信息修改tip
	private String tipModifyEmployeeBaseInformation;
	// 职工奖金或扣款录入按钮
	private MenuButton buttonInputOtherFee;
	// 职工奖金或扣款录入tip
	private String tipInputOtherFee;
	// 按钮disable图标
	private ImageIcon buttonIconEnable;
	// 按钮disable图标
	private ImageIcon buttonIconDisable;

	/**
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */
	public SubMenuBarInputModification() throws FileNotFoundException, IOException {
		Dimension preferredSize = new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH,
				AppConstantsField.SUB_MENUBAR_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		setButtonTip();
		addButton();
		addButtonListener();
		this.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		this.setLayout(new GridLayout(2, 1));
	}

	private void addButton() {
		buttonIconEnable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_ENABLE));
		buttonIconDisable = ImageTool.getMenuImageScaleDefault(new ImageIcon(PathManager.PATH_SUB_MENU_DISABLE));

		JPanel panelUp = new JPanel();
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));
		panelUp.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonInputEmployeeBaseInformation = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipInputEmployeeBaseInformation);
		buttonInputEmployeeBaseInformation.setText(tipInputEmployeeBaseInformation);
		buttonModifyEmployeeBaseInformation = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable,
				tipModifyEmployeeBaseInformation);
		buttonModifyEmployeeBaseInformation.setText(tipModifyEmployeeBaseInformation);
		buttonInputOtherFee = new MenuButton(buttonIconDisable, buttonIconEnable, buttonIconDisable, tipInputOtherFee);
		buttonInputOtherFee.setText(tipInputOtherFee);
		//panelUp.add(buttonInputEmployeeBaseInformation);
		//panelUp.add(buttonModifyEmployeeBaseInformation);
		//panelUp.add(buttonInputOtherFee);
		panelUp.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);

		JPanel panelDown = new JPanel();
		panelDown.setBackground(AppConstantsField.TOOL_BAR_BACK_COLOR);
		panelDown.setLayout(new BorderLayout(0, 0));
		panelUp.setSize(new Dimension(AppConstantsField.SUB_MENUBAR_WIDTH, AppConstantsField.SUB_MENUBAR_HEIGHT / 2));

		this.add(panelUp);
		this.add(panelDown);

	}

	private void addButtonListener() {
		buttonModifyEmployeeBaseInformation.addActionListener(e -> {
			buttonModifyEmployeeBaseInformation.setIcon(buttonIconEnable);
			buttonInputEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonInputOtherFee.setIcon(buttonIconDisable);
		});
		buttonInputEmployeeBaseInformation.addActionListener(e -> {
			buttonModifyEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonInputEmployeeBaseInformation.setIcon(buttonIconEnable);
			buttonInputOtherFee.setIcon(buttonIconDisable);
		});
		buttonInputOtherFee.addActionListener(e -> {
			buttonModifyEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonInputEmployeeBaseInformation.setIcon(buttonIconDisable);
			buttonInputOtherFee.setIcon(buttonIconEnable);
		});
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

		tipInputEmployeeBaseInformation = properties.getProperty(
				AppConstantsField.KEY_TIP_INPUT_EMPLOYEE_BASE_INFORMATION, "tip_input_employee_base_information");

		tipModifyEmployeeBaseInformation = properties.getProperty(
				AppConstantsField.KEY_TIP_MODIFY_EMPLOYEE_BASE_INFORMATION, "tip_modify_employee_base_information");

		tipInputOtherFee = properties.getProperty(AppConstantsField.KEY_TIP_INPUT_OTHER_FEE, "tip_input_other_fee");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SubMenuBarInputModification subMenuBarInputModification;

			try {
				subMenuBarInputModification = new SubMenuBarInputModification();
				subMenuBarInputModification.setVisible(true);

				JFrame jFrame = new JFrame();
				jFrame.setLayout(new GridLayout(3, 1));
				jFrame.add(subMenuBarInputModification);
				jFrame.setSize(new Dimension(500, 500));
				jFrame.setVisible(true);
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
