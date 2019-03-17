/**
 * 
 */
package salary.managment.system.view;

import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * @author Tortoise
 *
 */
public class AppConstantsField {

	public static void main(String[] args) {
		AppConstantsField appConstantsField = new AppConstantsField();
		try {
			appConstantsField.loginInterfaceFiled();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 该类用于独立运行创建中英文字段界面配置文件
	 * </p>
	 * <p>
	 * 配置文件所属包salary.managment.system.properties;
	 * </p>
	 * <ul>
	 * <li>中文配置文件名称：en_US.properties</li>
	 * <li>英文配置文件名称：zh_CN.properties</li>
	 * </ul>
	 */

	/**
	 * 用来读取配置文件的关键字
	 */
	public final static String KEY_EN_US = "en_US";
	public final static String KEY_ZH_CN = "zh_CN";
	public final static String KEY_LANGUAGE = "language";
	public final static String KEY_SYSTEM_NAME = "system_name";
	public final static String KEY_USER_NAME = "user_name";
	public final static String KEY_USER_PASS = "user_pass";
	public final static String KEY_SIGN_IN = "sign_in";
	public final static String KEY_LOGIN_SUCCESSFUL = "login_successful";
	public final static String KEY_USERNAME_OR_PASSWORD_UNCORRECT = "account_or_password_is_incorrect";
	public final static String KEY_ERROR = "error";

	public final static String KEY_TIP_INPUT_AND_MODIFICATION = "tip_input_and_modification";
	public final static String KEY_TIP_COMPUTE_AND_PRINT_SALARY = "tip_compute_and_print_salary";
	public final static String KEY_TIP_INQUIRY_SALARY = "tip_inquiry_salary";
	public final static String KEY_TIP_SYSTEM_MAINTENANCE = "tip_system_maintenance";
	public final static String KEY_TIP_SYSTEM_SETTINGS = "tip_system_settings";
	public final static String KEY_TIP_ABOUT = "tip_about";

	public final static String KEY_TIP_INPUT_EMPLOYEE_BASE_INFORMATION = "tip_input_employee_base_information";
	public final static String KEY_TIP_MODIFY_EMPLOYEE_BASE_INFORMATION = "tip_modify_employee_base_information";
	public final static String KEY_TIP_INPUT_OTHER_FEE = "tip_input_other_fee";

	public final static String KEY_TIP_PERSONAL_SALARY = "tip_personal_salary";
	public final static String KEY_TIP_PAYROLL = "tip_payroll";
	public final static String KEY_TIP_PAY_SCHEDULE = "tip_pay_schedule";
	public final static String KEY_TIP_SALARY_SUMMARY = "tip_salary_summary";
	public final static String KEY_TIP_SALARY_DISTRIBUTION = "tip_salary_distribution";

	public final static String KEY_TIP_INQUIRT_SALARY_DETAILS = "tip_inquirt_salary_details";
	public final static String KEY_TIP_SALARY_DEPARTMENT = "tip_salary_department";

	public final static String KEY_TIP_DATABASE_BACKUP = "tip_database_backup";
	public final static String KEY_TIP_ADD_EMPLOYEE_BASE_INFORMATION = "tip_add_employee_base_information";
	public final static String KEY_TIP_DECREASE_EMPLOYEE_BASE_INFORMATION = "tip_decrease_employee_base_information";
	public final static String KEY_TIP_UPDATE_EMPLOYEE_BASE_INFORMATION = "tip_update_employee_base_information";

	// 软件名称
	public final static String APP_NAME = "Salary Manager";

	// 系统屏幕大小：系統屏幕寬度
	public final static int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	// 系统屏幕大小：系统屏幕高度
	public final static int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	// 登录窗口默认大小：窗口宽度
	public final static int LOGIN_WINDOW_WIDTH = 535;
	// 登录窗口默认大小：窗口高度
	public final static int LOGIN_WINDOW_HEIGHT = 412;

	// 登录窗口默认位置: 登录窗口横向坐标
	public final static int LOGIN_WINDOW_X_POSITION = SCREEN_WIDTH / 2 - LOGIN_WINDOW_WIDTH / 2;
	// 登录窗口默认位置: 登录窗口纵向坐标
	public final static int LOGIN_WINDOW_Y_POSITION = SCREEN_HEIGHT / 2 - LOGIN_WINDOW_HEIGHT / 2;

	// 系统图标
	// Thread.currentThread().getContextClassLoader()
	public final static Image APP_ICON = Toolkit.getDefaultToolkit().getImage(PathManager.PATH_MAIN_APP_ICON);

	// 登录窗口背景色
	public final static Color LOGIN_WINDOW_BACK_COLOR = Color.WHITE;
	// 工具栏背景色
	public final static Color TOOL_BAR_BACK_COLOR = new Color(37, 174, 96);
	// 表格线条背景色
	public final static Color TABLE_LINE_COLOR = new Color(229, 229, 229);

	// 主窗口默认大小：宽度
	public final static int MAIN_WINDOW_WIDTH = SCREEN_WIDTH * 3 / 4;
	// 主窗口默认大小：高度
	public final static int MAIN_WINDOW_HEIGHT = SCREEN_HEIGHT * 3 / 4;

	// 主窗口默认位置：主窗口默认横向坐标
	public final static int MAIN_WINDOW_X_POSITION = SCREEN_WIDTH / 2 - MAIN_WINDOW_WIDTH / 2;
	// 主窗口默认位置：主窗口默认纵向坐标
	public final static int MAIN_WINDOW_Y_POSITION = SCREEN_HEIGHT / 2 - MAIN_WINDOW_HEIGHT / 2;
	// 主窗口菜单栏默认大小：宽度
	public final static int MAIN_WINDOW_MENUBAR_WIDTH = 60;
	// 主窗口菜单栏默认大小：高度
	public final static int MAIN_WINDOW_MENUBAR_HEIGHT = MAIN_WINDOW_HEIGHT;

	// 二级菜单背景色
	public final static Color SUB_MENU_BACK_COLOR = Color.BLACK;
	// 二级菜单大小：宽度
	public final static int SUB_MENUBAR_WIDTH = MAIN_WINDOW_MENUBAR_WIDTH * 5;
	// 二级菜单大小：高度
	public final static int SUB_MENUBAR_HEIGHT = MAIN_WINDOW_MENUBAR_HEIGHT;

	// 主窗口大小：宽度
	public final static int MAIN_PANEL_WIDTH = MAIN_WINDOW_WIDTH - MAIN_WINDOW_MENUBAR_WIDTH;
	// 主窗口大小：高度
	public final static int MAIN_PANEL_HEIGHT = MAIN_WINDOW_HEIGHT - MAIN_WINDOW_MENUBAR_HEIGHT;

	public AppConstantsField() {

	}

	// login interface filed
	public void loginInterfaceFiled() throws FileNotFoundException, IOException {
		Properties properties_EN_US = new Properties();
		properties_EN_US.load(new FileInputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES));
		properties_EN_US.setProperty(KEY_LANGUAGE, "en_US");
		properties_EN_US.setProperty(KEY_SYSTEM_NAME, "Salary Managment System");
		properties_EN_US.setProperty(KEY_USER_NAME, "Username: ");
		properties_EN_US.setProperty(KEY_USER_PASS, "Password: ");
		properties_EN_US.setProperty(KEY_SIGN_IN, "Sign in");
		properties_EN_US.setProperty(KEY_LOGIN_SUCCESSFUL, "login successful");
		properties_EN_US.setProperty(KEY_USERNAME_OR_PASSWORD_UNCORRECT, "account or password is incorrect");
		properties_EN_US.setProperty(KEY_ERROR, "error");
		properties_EN_US.setProperty(KEY_TIP_INPUT_AND_MODIFICATION, "input and modification");
		properties_EN_US.setProperty(KEY_TIP_COMPUTE_AND_PRINT_SALARY, "compute and print salary");
		properties_EN_US.setProperty(KEY_TIP_INQUIRY_SALARY, "inquiry salary");
		properties_EN_US.setProperty(KEY_TIP_SYSTEM_MAINTENANCE, "system maintenance");
		properties_EN_US.setProperty(KEY_TIP_SYSTEM_SETTINGS, "system settings");
		properties_EN_US.setProperty(KEY_TIP_ABOUT, "about");

		properties_EN_US.setProperty(KEY_TIP_INPUT_EMPLOYEE_BASE_INFORMATION, "input employee change information");
		properties_EN_US.setProperty(KEY_TIP_MODIFY_EMPLOYEE_BASE_INFORMATION, "modify employee change information");
		properties_EN_US.setProperty(KEY_TIP_INPUT_OTHER_FEE, "input other fee");

		properties_EN_US.setProperty(KEY_TIP_PERSONAL_SALARY, "personal salary");
		properties_EN_US.setProperty(KEY_TIP_PAYROLL, "payroll");
		properties_EN_US.setProperty(KEY_TIP_PAY_SCHEDULE, "pay schedule");
		properties_EN_US.setProperty(KEY_TIP_SALARY_SUMMARY, "salary summary");
		properties_EN_US.setProperty(KEY_TIP_SALARY_DISTRIBUTION, "salary distribution");

		properties_EN_US.setProperty(KEY_TIP_INQUIRT_SALARY_DETAILS, "inquiry salary details");
		properties_EN_US.setProperty(KEY_TIP_SALARY_DEPARTMENT, "salary deparment");

		properties_EN_US.setProperty(KEY_TIP_DATABASE_BACKUP, "database backup");
		properties_EN_US.setProperty(KEY_TIP_ADD_EMPLOYEE_BASE_INFORMATION, "add employee base information");
		properties_EN_US.setProperty(KEY_TIP_DECREASE_EMPLOYEE_BASE_INFORMATION, "decrease employee base information");
		properties_EN_US.setProperty(KEY_TIP_UPDATE_EMPLOYEE_BASE_INFORMATION, "update employee base information");

		properties_EN_US.store(new FileOutputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES),
				"interface language properties:en_US");

		Properties properties_ZH_CN = new Properties();
		properties_ZH_CN.load(new FileInputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES));
		properties_ZH_CN.setProperty(KEY_LANGUAGE, "zh_CN");
		properties_ZH_CN.setProperty(KEY_SYSTEM_NAME, "工资管理系统");
		properties_ZH_CN.setProperty(KEY_USER_NAME, "用户名：");
		properties_ZH_CN.setProperty(KEY_USER_PASS, "密  码：");
		properties_ZH_CN.setProperty(KEY_SIGN_IN, "登录");
		properties_ZH_CN.setProperty(KEY_LOGIN_SUCCESSFUL, "登录成功");
		properties_ZH_CN.setProperty(KEY_USERNAME_OR_PASSWORD_UNCORRECT, "密码或账号不正确");
		properties_ZH_CN.setProperty(KEY_ERROR, "错误");
		properties_ZH_CN.setProperty(KEY_TIP_INPUT_AND_MODIFICATION, "输入与修改");
		properties_ZH_CN.setProperty(KEY_TIP_COMPUTE_AND_PRINT_SALARY, "计算打印工资");
		properties_ZH_CN.setProperty(KEY_TIP_INQUIRY_SALARY, "查询工资");
		properties_ZH_CN.setProperty(KEY_TIP_SYSTEM_MAINTENANCE, "系统维护");
		properties_ZH_CN.setProperty(KEY_TIP_SYSTEM_SETTINGS, "系统设置");
		properties_ZH_CN.setProperty(KEY_TIP_ABOUT, "关于");

		properties_ZH_CN.setProperty(KEY_TIP_INPUT_EMPLOYEE_BASE_INFORMATION, "员工变动信息录入");
		properties_ZH_CN.setProperty(KEY_TIP_MODIFY_EMPLOYEE_BASE_INFORMATION, "员工变动信息修改");
		properties_ZH_CN.setProperty(KEY_TIP_INPUT_OTHER_FEE, "奖金或扣款录入");

		properties_ZH_CN.setProperty(KEY_TIP_PERSONAL_SALARY, "个人工资");
		properties_ZH_CN.setProperty(KEY_TIP_PAYROLL, "工资条");
		properties_ZH_CN.setProperty(KEY_TIP_PAY_SCHEDULE, "工资明细表");
		properties_ZH_CN.setProperty(KEY_TIP_SALARY_SUMMARY, "工资汇总表");
		properties_ZH_CN.setProperty(KEY_TIP_SALARY_DISTRIBUTION, "工资分配表");

		properties_ZH_CN.setProperty(KEY_TIP_INQUIRT_SALARY_DETAILS, "查询工资明细");
		properties_ZH_CN.setProperty(KEY_TIP_SALARY_DEPARTMENT, "查询部门工资");

		properties_ZH_CN.setProperty(KEY_TIP_DATABASE_BACKUP, "数据库备份");
		properties_ZH_CN.setProperty(KEY_TIP_ADD_EMPLOYEE_BASE_INFORMATION, "增加职工");
		properties_ZH_CN.setProperty(KEY_TIP_DECREASE_EMPLOYEE_BASE_INFORMATION, "减少职工");
		properties_ZH_CN.setProperty(KEY_TIP_UPDATE_EMPLOYEE_BASE_INFORMATION, "更新数据");

		properties_ZH_CN.store(new FileOutputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES),
				"interface language properties:zh_CN");

	}
}
