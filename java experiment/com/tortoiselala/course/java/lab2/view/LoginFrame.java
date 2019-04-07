package com.tortoiselala.course.java.lab2.view;

import com.tortoiselala.course.java.lab2.base.DatabaseManager;
import com.tortoiselala.course.java.lab2.base.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

/*
 * LoginFrame:
 *  - username label
 *  - password label
 *  - login button
 */
public class LoginFrame extends JFrame implements Runnable {

    private JLabel labelSystemTitle;
    private JLabel labelUsername;
    private JLabel labelPassword;

    private JTextField fieldUsername;
    private JPasswordField fieldPassword;

    private JButton buttonLogin;

    private final int panelWidth = 535;
    private final int panelHeight = 412;

    private User user;

    public LoginFrame(){
        this.user = null;
        try {
            //set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            // set label content
            this.labelSystemTitle = new JLabel("系统测试");
            this.labelUsername = new JLabel("用户");
            this.labelPassword = new JLabel("密码");
            this.fieldUsername = new JTextField();
            this.fieldPassword = new JPasswordField();
            this.fieldUsername.setPreferredSize(new Dimension(this.panelWidth / 2, this.panelHeight / 9));
            this.fieldPassword.setPreferredSize(new Dimension(this.panelWidth / 2, this.panelHeight / 9));

            this.buttonLogin = new JButton("登录");

            setLayout(new GridBagLayout());
            add(labelSystemTitle, getCommonentGridBagConstraints(100, 100, 0, 0, 8, 1, GridBagConstraints.NONE,
                    GridBagConstraints.CENTER));
            add(labelUsername,
                    getCommonentGridBagConstraints(100, 100, 2, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
            add(fieldUsername,
                    getCommonentGridBagConstraints(100, 100, 3, 3, 3, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
            add(labelPassword,
                    getCommonentGridBagConstraints(100, 100, 2, 5, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
            add(fieldPassword,
                    getCommonentGridBagConstraints(100, 100, 3, 5, 3, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
            add(buttonLogin, getCommonentGridBagConstraints(100, 100, 0, 7, 8, 1, GridBagConstraints.NONE,
                    GridBagConstraints.CENTER));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.CANCEL_OPTION);
            e.printStackTrace();
            return;
        }
        // 获取系统所需语言类型，并设置相关字符串

        // 设置窗口标题
        setTitle(AppViewConstantsField.APP_NAME);
        // 设置窗口主色
        getContentPane().setBackground(AppViewConstantsField.LOGIN_WINDOW_BACK_COLOR);
        // 设置窗口使用默认关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口位置居中显示
        setLocation(AppViewConstantsField.LOGIN_WINDOW_X_POSITION, AppViewConstantsField.LOGIN_WINDOW_Y_POSITION);
        // 设置窗口默认大小
        setSize(this.panelWidth, this.panelHeight);
        // 设置不可更改大小
        setResizable(false);
        // 增加login按钮响应事件
        buttonLogin.addActionListener(new LoginButtonListener());
    }
    private GridBagConstraints getCommonentGridBagConstraints(int weightx, int weighty, int gridx, int gridy,
                                                              int gridwidth, int gridheight, int fill, int anchor) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = gridheight;
        constraints.fill = fill;
        constraints.anchor = anchor;
        return constraints;
    }

    @Override
    public void run() {
        this.setVisible(true);
    }


    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("登录")) {
                user = new User(fieldUsername.getText(), String.valueOf(fieldPassword.getPassword()));
                DatabaseManager.checkUserPermission(user);
                if(user.isPermission()){
                    JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", JOptionPane.CANCEL_OPTION);
                }else{
                    closeWindow();
                    Main.count.countDown();
                    Main.user = user;
                }

            }
        }
    }

    private void closeWindow(){
        this.dispose();
    }

}
