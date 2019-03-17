/**
 * 
 */
package salary.managment.system.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import salary.managment.system.base.UserManagment;

/**
 * @author Tortoise
 *
 */
public class LoginFrame extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new LoginFrame();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	/**
	 * 
	 */

	/**
	 * 
	 */
	private JLabel systemNameLabel;
	private JLabel userNameLabel;
	private JLabel userPassLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JTextField closeSigner;

	private String systemName;
	private String userName;
	private String userPass;
	private String signin;
	private String loginSuccessful;
	private String usernameOrPasswordIsIncorrect;
	private String error;

	private static int panelWidth = 535;
	private static int panelHeight = 412;

	public LoginFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, FileNotFoundException, IOException {
		initFrame();
	}

	private void initFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException, FileNotFoundException, IOException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// 获取系统所需语言类型，并设置相关字符串
		getPanelText();

		addLoginButtonListerer();
		// 设置窗口标题
		setTitle(AppConstantsField.APP_NAME);
		// 设置窗口图标
		setIconImage(AppConstantsField.APP_ICON);
		// 设置窗口主色
		getContentPane().setBackground(AppConstantsField.LOGIN_WINDOW_BACK_COLOR);
		// 设置窗口使用默认关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口位置居中显示
		setLocation(AppConstantsField.LOGIN_WINDOW_X_POSITION, AppConstantsField.LOGIN_WINDOW_Y_POSITION);
		// 设置窗口默认大小
		setSize(panelWidth, panelHeight);
		// 设置窗口总显示在顶部
		// 注：这里不能设置总显示在顶部，如果一直显示在顶部，会导致窗口重叠（错误消息窗口与主窗口）导致卡死
		// setAlwaysOnTop(true);
		// 设置窗口不能改变大小
		setResizable(false);
		// 设置窗口显示
		setVisible(true);
	}

	public void closeTargetWindow() {
		this.dispose();
	}

	private void getPanelText() throws FileNotFoundException, IOException {

		Properties properties = new Properties();
		// 獲取系统语言
		properties.load(new FileInputStream(PathManager.PATH_MAIN_SYSTEM_PROPERTIES));
		String language = properties.getProperty(AppConstantsField.KEY_LANGUAGE);

		if (language.equals(AppConstantsField.KEY_EN_US)) {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_EN_US_PROPERTIES));
		} else {
			properties.load(new FileInputStream(PathManager.PATH_MAIN_ZH_CN_PROPERTIES));

		}
		systemName = properties.getProperty(AppConstantsField.KEY_SYSTEM_NAME, "工资管理系统");
		userName = properties.getProperty(AppConstantsField.KEY_USER_NAME, "用户名");
		userPass = properties.getProperty(AppConstantsField.KEY_USER_PASS, "密码");
		signin = properties.getProperty(AppConstantsField.KEY_SIGN_IN, "登录");
		loginSuccessful = properties.getProperty(AppConstantsField.KEY_LOGIN_SUCCESSFUL, "登录成功");
		usernameOrPasswordIsIncorrect = properties.getProperty(AppConstantsField.KEY_USERNAME_OR_PASSWORD_UNCORRECT,
				"密码或账户不正确");
		error = properties.getProperty(AppConstantsField.KEY_ERROR, "错误");

		systemNameLabel = new JLabel(systemName);
		userNameLabel = new JLabel(userName);
		usernameField = new JTextField();

		userPassLabel = new JLabel(userPass);
		usernameField.setPreferredSize(new Dimension(panelWidth / 2, panelHeight / 9));
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(panelWidth / 2, panelHeight / 9));
		passwordField.setVisible(true);
		loginButton = new JButton(signin);
		closeSigner = new JTextField(signin);

		setLayout(new GridBagLayout());
		add(systemNameLabel, getCommonentGridBagConstraints(100, 100, 0, 0, 8, 1, GridBagConstraints.NONE,
				GridBagConstraints.CENTER));
		add(userNameLabel,
				getCommonentGridBagConstraints(100, 100, 2, 3, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		add(usernameField,
				getCommonentGridBagConstraints(100, 100, 3, 3, 3, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		add(userPassLabel,
				getCommonentGridBagConstraints(100, 100, 2, 5, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		add(passwordField,
				getCommonentGridBagConstraints(100, 100, 3, 5, 3, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		add(loginButton, getCommonentGridBagConstraints(100, 100, 0, 7, 8, 1, GridBagConstraints.NONE,
				GridBagConstraints.CENTER));
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

	private void addLoginButtonListerer() {
		loginButton.addActionListener(new LoginButtonListener());
		closeSigner.getDocument().addDocumentListener(new CloseTargetWindowListener());
	}

	class CloseTargetWindowListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent e) {
			System.out.println("update");
			insertOrRemoveOrUpdate(e);
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			System.out.println("insert");
			insertOrRemoveOrUpdate(e);
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			System.out.println("remove");
		}

		private void insertOrRemoveOrUpdate(DocumentEvent e) {
			int documentLength = e.getLength();
			try {
				String documentText = e.getDocument().getText(0, documentLength);
				if (documentText.equals(loginSuccessful)) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), error, JOptionPane.OK_CANCEL_OPTION);

					}
					closeTargetWindow();
				}
			} catch (BadLocationException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(), error, JOptionPane.OK_CANCEL_OPTION);

			}
		}
	}

	class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(signin)) {
				UserManagment userManagment = new UserManagment(usernameField.getText(), passwordField.getText());
				try {
					if (userManagment.isUser(true)) {
						loginButton.setText(loginSuccessful);
						closeSigner.setText(loginSuccessful);
						MainFrame.isLogin = true;
						TopModuleConnectLoginFrameAndMainFrame.mainFrame.setVisible(true);
						if(userManagment.isAdminUser(true)) {
							MainFrame.isAdmin = true;
							MainFrame.isGener = false;
						}
						else {
							MainFrame.isAdmin = false;
							MainFrame.isGener = true;
						}

					} else {
						JOptionPane.showMessageDialog(null, usernameOrPasswordIsIncorrect, error,
								JOptionPane.OK_CANCEL_OPTION);
					}
				} catch (ClassNotFoundException | SQLException | IOException e1) {

					JOptionPane.showMessageDialog(null, e1.getMessage(), error, JOptionPane.OK_CANCEL_OPTION);
				}
			}
		}
	}
}
