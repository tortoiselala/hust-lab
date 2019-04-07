package com.tortoiselala.course.java.lab2.view;

import javax.swing.*;
import java.awt.*;

public class DoctorFrame extends JFrame implements Runnable{
    private String windowName;
    private JTabbedPane tabPane;

    public DoctorFrame(){
        this.windowName = "医生界面";

    }
    private void setAttributes(){
        // 设置窗口监听器
//        addWindowListener(new mainFrameWindowListener());
        // 系统语言以及字符串
//        getPanelText();
        // 设置窗口标题
        this.setTitle(this.windowName);
        // 设置窗口主色,JFrame与Frame不同
        // JFrame需要使用getContenrPane.setBackground()
        // Frame需要使用setBackground();
        this.getContentPane().setBackground(Color.WHITE);
        // 设置窗口使用默认关闭方式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置系统默认位置
        this.setLocation(AppViewConstantsField.MAIN_WINDOW_X_POSITION, AppViewConstantsField.MAIN_WINDOW_Y_POSITION);
        // 设置窗口默认大小
        this.setSize(AppViewConstantsField.MAIN_WINDOW_WIDTH, AppViewConstantsField.MAIN_WINDOW_HEIGHT);
        // 设置窗口大小不能改变
        this.setResizable(false);
    }
    private void setComponent(){
        // 病人列表界面的table初始化
        String[] patientTitles = {"挂号名称", "病人名称", "挂号日期事件", "号种类别"};
        String[][] patientDatas = getPatientDatas();

        // 收入列表界面的table初始化
        String[] incodeTitles = {"科室名称", "医生编号", "医生名称", "号种类别", "挂号人次","收入合计"};
        String[][] incodeDatas = getIncodeDatas();

        this.tabPane = new JTabbedPane();

        this.tabPane.addTab("病人列表", getTabPanel(false, patientTitles, patientDatas));
        this.tabPane.addTab("收入列表", getTabPanel(true, incodeTitles, incodeDatas));
        this.tabPane.addChangeListener(e -> {
            int index =  tabPane.getSelectedIndex();
            tabPane.setSelectedIndex(index);
        });

        this.add(tabPane);
    }

    private JPanel getTabPanel(boolean index, String[] incodeTitles, String[][] incodeDatas) {

        JTable incodeList = new JTable(incodeDatas, incodeTitles);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        if(index){
            DateChooser dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
            JLabel showDate2 = new JLabel("单击选择日期");
            dateChooser2.register(showDate2);
            panel.add(showDate2, BorderLayout.NORTH);
        }
        panel.add(new JScrollPane(incodeList), BorderLayout.CENTER);
        return panel;
    }

    private String[][] getIncodeDatas() {
        return new String[][]{
                {"感染科", "000001", "李时珍", "专家号", "24", "48"},
                {"感染科", "000001", "李时珍", "普通号", "10", "10"},
                {"内一科", "000002", "扁鹊", "普通号", "23", "23"},
                {"保健科", "000003", "华佗", "专家号", "10", "20"}
        };
    }

    private String[][] getPatientDatas() {
        return new String[][]{
                {"000001", "章紫衣", "2018-12-30  11:52:26", "专家号"},
                {"000003", "范冰冰", "2018-12-30  11:53:26",	"普通号"},
                {"000004", "刘德华", "2018-12-30  11:54:28",	"普通号"}
        };
    }

    private void updateTabPatient(){
        JPanel component = (JPanel) this.tabPane.getComponentAt(0);
        JTable table = (JTable) component.getComponent(1);

    }
    private void updateTabIncome(){
        JPanel component = (JPanel) this.tabPane.getComponentAt(1);
        JTable table = (JTable) component.getComponent(1);
    }

    public void begin(){
        this.setComponent();
        this.setAttributes();

        this.setVisible(true);
    }

    @Override
    public void run() {
        this.begin();
    }
}
