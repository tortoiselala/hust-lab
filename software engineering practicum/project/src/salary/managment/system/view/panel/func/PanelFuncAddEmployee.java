/**
 * 
 */
package salary.managment.system.view.panel.func;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TypeHost;

import salary.managment.system.base.BasicInformationOfEmployeeManagment;

/**
 * @author Tortoise
 *
 */
public class PanelFuncAddEmployee extends JPanel {

	private JLabel labelTitle;
	private String tipTitle;

	private JLabel labelEmployeeID;
	private String tipEmployeeID;
	private JTextField textFieldEmployeeID;

	private JLabel labelEmployeeName;
	private String tipEmployeeName;
	private JTextField textFieldEmployeeName;

	private JLabel labelDepartmentID;
	private String tipDepartmentID;
	private JTextField textFieldDepartmentID;

	private JLabel labelSex;
	private String tipSex;
	private JComboBox<String> textFieldSex;

	private JLabel labelAge;
	private String tipAge;
	private JTextField textFieldAge;

	private JLabel labelBaseSalary;
	private String tipBaseSalary;
	private JTextField textFieldBaseSalary;

	private JLabel labelCategory;
	private String tipCategory;
	private JTextField textFieldCategory;

	private JButton confirmButton;

	public PanelFuncAddEmployee() {
		set();
		addListener();
	}

	private void set() {
		tipTitle = new String("增加职工基本信息：");
		tipEmployeeID = new String("身份证号：");
		tipEmployeeName = new String("职工姓名：");
		tipDepartmentID = new String("部门编号：");
		tipSex = new String("性别(男/女)：");
		tipAge = new String("年龄：");
		tipBaseSalary = new String("基本工资：");
		tipCategory = new String("职工类别：");

		labelTitle = new JLabel(tipTitle);
		labelEmployeeID = new JLabel(tipEmployeeID);
		labelEmployeeName = new JLabel(tipEmployeeName);
		labelDepartmentID = new JLabel(tipDepartmentID);
		labelSex = new JLabel(tipSex);
		labelAge = new JLabel(tipAge);
		labelBaseSalary = new JLabel(tipBaseSalary);
		labelCategory = new JLabel(tipCategory);

		textFieldEmployeeID = new JTextField();
		textFieldEmployeeName = new JTextField();
		textFieldDepartmentID = new JTextField();
		textFieldSex = new JComboBox<>();
		textFieldSex.addItem("男");
		textFieldSex.addItem("女");
		textFieldAge = new JTextField();
		textFieldBaseSalary = new JTextField();
		textFieldCategory = new JTextField();

		confirmButton = new JButton("确定");
		JLabel spacePlace = new JLabel("");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int targetPanelWidth = screenSize.width / 2;
		int targetPanelHeight = screenSize.height / 2;

		labelEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelEmployeeName.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelDepartmentID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelSex.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelAge.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelBaseSalary.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelCategory.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		textFieldEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldEmployeeName.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldDepartmentID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldSex.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldAge.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldBaseSalary.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		textFieldCategory.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));

		this.setLayout(new GridBagLayout());
		this.add(labelTitle,
				getComponentConstraints(100, 100, 0, 0, 10, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));

		this.add(labelEmployeeID,
				getComponentConstraints(100, 100, 1, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldEmployeeID,
				getComponentConstraints(100, 100, 3, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelEmployeeName,
				getComponentConstraints(100, 100, 5, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldEmployeeName,
				getComponentConstraints(100, 100, 7, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		this.add(spacePlace,
				getComponentConstraints(100, 100, 9, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));

		this.add(labelDepartmentID,
				getComponentConstraints(100, 100, 1, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldDepartmentID,
				getComponentConstraints(100, 100, 3, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelSex,
				getComponentConstraints(100, 100, 5, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldSex,
				getComponentConstraints(100, 100, 7, 4, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelAge,
				getComponentConstraints(100, 100, 1, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldAge,
				getComponentConstraints(100, 100, 3, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelBaseSalary,
				getComponentConstraints(100, 100, 5, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldBaseSalary,
				getComponentConstraints(100, 100, 7, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelCategory,
				getComponentConstraints(100, 100, 1, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldCategory,
				getComponentConstraints(100, 100, 3, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(confirmButton,
				getComponentConstraints(100, 100, 5, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(confirmButton,
				getComponentConstraints(100, 100, 7, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

	}

	public GridBagConstraints getComponentConstraints(int weightx, int weighty, int gridx, int gridy, int gridwidth,
			int gridheight, int fill, int ancher) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.fill = fill;
		constraints.anchor = ancher;

		return constraints;
	}

	private void addListener() {
		textFieldEmployeeID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String inputAllowType = "0123456789Xx";
				String textFieldContent = textFieldEmployeeID.getText();

				int maxLengthOfIDInput = 18;
				int inputAllowTypeLength = inputAllowType.length();
				int textFieldContentLength = textFieldContent.length();

				char[] inputAllowTyprToCharArray = inputAllowType.toCharArray();

				char ch = e.getKeyChar();
				if ((ch == inputAllowTyprToCharArray[inputAllowTypeLength - 2]
						|| ch == inputAllowTyprToCharArray[inputAllowTypeLength - 1])) {
					if (textFieldContentLength == maxLengthOfIDInput - 1) {
						;
					} else {
						e.consume();
					}
				} else if (inputAllowType.indexOf(e.getKeyChar()) < 0) {
					e.consume();
				}

				if (textFieldEmployeeID.getText().length() >= maxLengthOfIDInput) {
					e.consume();
				}
			}
		});
		textFieldDepartmentID.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				String inputAllowType = "0123456789";

				int maxLengthOfIDInput = 2;
				int textFieldContentLength = textFieldDepartmentID.getText().length();

				char ch = e.getKeyChar();
				if (inputAllowType.indexOf(ch) < 0) {
					e.consume();
				}

				if (textFieldContentLength >= maxLengthOfIDInput) {
					e.consume();
				}

			}
		});

		textFieldAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String inputAllowType = "0123456789";

				int maxLengthOfIDInput = 2;
				int textFieldContentLength = textFieldAge.getText().length();

				char ch = e.getKeyChar();
				if (inputAllowType.indexOf(ch) < 0) {
					e.consume();
				}

				if (textFieldContentLength == 0 && ch == '0') {
					e.consume();
				}
				if (textFieldContentLength >= maxLengthOfIDInput) {
					e.consume();
				}

			}
		});
		textFieldBaseSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String inputAllowType = "0123456789.";
				int maxLengthOfIDInput = 20;
				int textFieldContentLength = textFieldBaseSalary.getText().length();

				char ch = e.getKeyChar();
				if (inputAllowType.indexOf(e.getKeyChar()) < 0) {
					e.consume();
				} else if (textFieldContentLength == 0 && ch == '0') {
					e.consume();
				} else if (textFieldEmployeeID.getText().length() >= maxLengthOfIDInput) {
					e.consume();
				}
			}
		});
		confirmButton.addActionListener(e -> {
			String employeeID = textFieldEmployeeID.getText();
			if (employeeID.length() < 18) {
				JOptionPane.showMessageDialog(null, "不合法的身份证号", "错误", JOptionPane.OK_CANCEL_OPTION);
			}
			String employeeName = textFieldEmployeeName.getText();
			String departmentID = textFieldDepartmentID.getText();
			String sex = textFieldSex.getActionCommand();
			if (sex.equals("男")) {
				sex = "1";
			} else {
				sex = "0";
			}
			String age = textFieldAge.getText();
			String baseSalary = textFieldBaseSalary.getText();
			String category = textFieldCategory.getText();

			try {
				BasicInformationOfEmployeeManagment manager = new BasicInformationOfEmployeeManagment(departmentID,
						employeeID, employeeName, Short.parseShort(sex), Short.parseShort(age),
						Double.parseDouble(baseSalary), category);
				if (manager.isEmployeeInformationExits()) {
					JOptionPane.showMessageDialog(null, "该职工信息已经存在", "错误", JOptionPane.OK_CANCEL_OPTION);
				} else {
					manager.addOrUpdateEmployee();
					JOptionPane.showMessageDialog(null, "成功添加职工信息", "成功", JOptionPane.OK_CANCEL_OPTION);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.OK_CANCEL_OPTION);
				e1.printStackTrace();
			}

		});

	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
		jFrame.setLocation(screenSize.width / 2 - screenSize.width / 4, screenSize.height / 2 - screenSize.height / 4);
		jFrame.add(new PanelFuncAddEmployee());

		jFrame.setVisible(true);
	}

}
