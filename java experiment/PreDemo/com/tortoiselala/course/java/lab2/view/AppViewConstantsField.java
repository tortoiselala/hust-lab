package com.tortoiselala.course.java.lab2.view;

import java.awt.*;

public class AppViewConstantsField {
    // 软件名称
    public final static String APP_NAME = "登录窗口测试标题";

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
}
