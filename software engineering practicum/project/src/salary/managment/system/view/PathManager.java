/**
 * 
 */
package salary.managment.system.view;

import com.sun.org.glassfish.external.statistics.Statistic;

/**
 * @author Tortoise
 *
 */
public class PathManager {
	// 数据库配置文件名称
	public final static String NAME_DATABASE_PROPERTIES = "database.properties";
	// 系统配置文件名称
	public final static String NAME_SYSTEM_PROPERTIES = "system.properties";
	// 中文配置文件名称
	public final static String NAME_ZH_CN_PROPERTIES = "zh_CN.properties";
	// 英文配置文件名称
	public final static String NAME_EN_US_PROPERTIES = "en_US.properties";
	// 系统图标名称
	public final static String NAME_APP_ICON = "app_icon.png";
	// 输入与修改菜单enable图标名称
	public final static String NAME_INPUT_MODIFICATION_ENABLE = "icon_input_modification_enable.png";
	// 输入与修改菜单normal图标名称
	public final static String NAME_INPUT_MODIFICATION_DISABLE = "icon_input_modification_disable.png";
	// 计算打印工资菜单enable图标名称
	public final static String NAME_COMPUTE_PRINT_ENABLE = "icon_compute_print_enable.png";
	// 计算打印工资菜单disable图标名称
	public final static String NAME_COMPUTE_PRINT_DISABLE = "icon_compute_print_disable.png";
	// 查询工资菜单enable图标名称
	public final static String NAME_INQUIRY_ENABLE = "icon_inquiry_enable.png";
	// 查询工资菜单disable图标名称
	public final static String NAME_INQUIRY_DISABLE = "icon_inquiry_disable.png";
	// 系统维护enable图标名称
	public final static String NAME_SYSTEM_MAINTENANCE_ENABLE = "icon_system_maintenance_enable.png";
	// 系统维护disable图标名称
	public final static String NAME_SYSTEM_MAINTENANCE_DISABLE = "icon_system_maintenance_disable.png";
	// 系统设置enable图标名称
	public final static String NAME_SYSTEM_SETTINGS_ENABLE = "icon_system_settings_enable.png";
	// 系统设置disable图标设置
	public final static String NAME_SYSTEM_SETTINGS_DISABLE = "icon_system_settings_disable.png";
	// 关于enable图标名称
	public final static String NAME_ABOUT_ENABLE = "icon_about_enable.png";
	// 关于disable图标名称
	public final static String NAME_ABOUT_DISABLE = "icon_about_disable.png";
	// 二级菜单按钮enable图标名称
	public final static String NAME_SUB_MENU_ENABLE = "icon_sub_menu_disable.png";
	// 二级菜单按钮disable图标名称
	public final static String NAME_SUB_MENU_DISABLE = "icon_sub_menu_enable.png";
	// 系统主目录
	public final static String PATH_SYSTEM = System.getProperty("user.dir") + "\\";
	// 配置文件路径
	public final static String PATH_CONFIG = "src\\salary\\managment\\system\\config\\";
	// 图标文件路径
	public final static String PATH_ICON = "rec\\icon\\";

	// 数据库配置文件路径
	public final static String PATH_MAIN_DATABASE_PROPERTIES = PATH_SYSTEM + PATH_CONFIG + NAME_DATABASE_PROPERTIES;
	// 系统配置文件路径
	public final static String PATH_MAIN_SYSTEM_PROPERTIES = PATH_SYSTEM + PATH_CONFIG + NAME_SYSTEM_PROPERTIES;
	// 中文配置文件名称
	public final static String PATH_MAIN_ZH_CN_PROPERTIES = PATH_SYSTEM + PATH_CONFIG + NAME_ZH_CN_PROPERTIES;
	// 英文配置文件名称
	public final static String PATH_MAIN_EN_US_PROPERTIES = PATH_SYSTEM + PATH_CONFIG + NAME_EN_US_PROPERTIES;
	// 图标文件主路径
	public final static String PATH_MAIN_APP_ICON = PATH_SYSTEM + PATH_ICON + NAME_APP_ICON;
	// 输入与修改菜单enable图标路径
	public final static String PATH_MAIN_INPUT_MODIFICATION_ENABLE = PATH_SYSTEM + PATH_ICON
			+ NAME_INPUT_MODIFICATION_ENABLE;
	// 输入与修改菜单normal图标路径
	public final static String PATH_MAIN_INPUT_MODIFICATION_DISABLE = PATH_SYSTEM + PATH_ICON
			+ NAME_INPUT_MODIFICATION_DISABLE;
	// 计算打印工资菜单enable图标路径
	public final static String PATH_COMPUTE_PRINT_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_COMPUTE_PRINT_ENABLE;
	// 计算打印工资菜单disable图标路径
	public final static String PATH_COMPUTE_PRINT_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_COMPUTE_PRINT_DISABLE;
	// 查询工资菜单enable图标路径
	public final static String PATH_INQUIRY_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_INQUIRY_ENABLE;
	// 查询工资菜单disable图标路径
	public final static String PATH_INQUIRY_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_INQUIRY_DISABLE;
	// 系统维护enable图标路径
	public final static String PATH_SYSTEM_MAINTENANCE_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SYSTEM_MAINTENANCE_ENABLE;
	// 系统维护disable图标路径
	public final static String PATH_SYSTEM_MAINTENANCE_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SYSTEM_MAINTENANCE_DISABLE;
	// 系统设置enable图标路径
	public final static String PATH_SYSTEM_SETTINGS_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SYSTEM_SETTINGS_ENABLE;
	// 系统设置disable图标路径
	public final static String PATH_SYSTEM_SETTINGS_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SYSTEM_SETTINGS_DISABLE;
	// 关于enable图标路径
	public final static String PATH_ABOUT_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON + NAME_ABOUT_ENABLE;
	// 关于disable图标路径
	public final static String PATH_ABOUT_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_ABOUT_DISABLE;
	// 二级菜单按钮enable图标路径
	public final static String PATH_SUB_MENU_ENABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SUB_MENU_ENABLE;
	// 二级菜单按钮disable图标路径
	public final static String PATH_SUB_MENU_DISABLE = PathManager.PATH_SYSTEM + PathManager.PATH_ICON
			+ NAME_SUB_MENU_DISABLE;

	/**
	 * 
	 */
	private PathManager() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println();

	}

}
