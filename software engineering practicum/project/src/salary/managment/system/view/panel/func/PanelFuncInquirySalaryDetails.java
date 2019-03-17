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
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import salary.managment.system.base.CurrentMonthEmployeeSalaryInformationManager;
import salary.managment.system.base.DatabaseFiled;

/**
 * @author Tortoise
 *
 */
public class PanelFuncInquirySalaryDetails extends JPanel {
	private JLabel labelTitle;
	private String tipTitle;

	private JLabel labelEmployeeID;
	private String tipEmployeeID;
	private JTextField textFieldEmployeeID;

	private JButton buttonInquiry;

	private JLabel labelDepartmentID;
	private String tipDepartmentID;
	private JLabel textFieldDepartmentID;

	private JLabel labelEmployeeName;
	private String tipEmpolyeeName;
	private JLabel textFieldEmployeeName;

	private JLabel labelCategory;
	private String tipCategory;
	private JLabel textFieldCategory;

	private JLabel labelBaseSalary;
	private String tipBaseSalary;
	private JLabel textFieldBaseSalary;

	private JLabel labelSickFee;
	private String tipSickFee;
	private JLabel textFieldSickFee;

	private JLabel labelChildCareFee;
	private String tipChildCareFee;
	private JLabel textFieldChildCareFee;

	private JLabel labelRentFee;
	private String tipRentFee;
	private JLabel textFieldRentFee;

	private JLabel labelWaterElectricityFee;
	private String tipWaterElectricityFee;
	private JLabel textFieldWaterElectricityFee;

	private JLabel labelOtherFee;
	private String tipOtherFee;
	private JLabel textFieldOtherFee;

	public PanelFuncInquirySalaryDetails() {
		setTip();
		addLabelAndTextField();
		addListener();
	}

	private void setTip() {
		tipTitle = "职工工资明细查询";
		tipEmployeeID = "员工身份证号：";
		tipDepartmentID = "部门编号：";
		tipEmpolyeeName = "员工名称：";
		tipCategory = "员工类别：";
		tipBaseSalary = "基本工资：";
		tipSickFee = "病事假扣款：";
		tipChildCareFee = "托儿费：";
		tipRentFee = "房租：";
		tipWaterElectricityFee = "水电费：";
		tipOtherFee = "奖金：";
	}

	private void addLabelAndTextField() {
		labelTitle = new JLabel(tipTitle);

		labelEmployeeID = new JLabel(tipEmployeeID);
		labelDepartmentID = new JLabel(tipDepartmentID);
		labelEmployeeName = new JLabel(tipEmpolyeeName);
		labelCategory = new JLabel(tipCategory);
		labelBaseSalary = new JLabel(tipBaseSalary);
		labelSickFee = new JLabel(tipSickFee);
		labelChildCareFee = new JLabel(tipChildCareFee);
		labelRentFee = new JLabel(tipRentFee);
		labelWaterElectricityFee = new JLabel(tipWaterElectricityFee);
		labelOtherFee = new JLabel(tipOtherFee);
		JLabel spacePlace = new JLabel("");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int targetPanelWidth = screenSize.width / 2;
		int targetPanelHeight = screenSize.height / 2;

		labelEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelDepartmentID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelEmployeeName.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelCategory.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelBaseSalary.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelSickFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelChildCareFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelRentFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelWaterElectricityFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelOtherFee.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		textFieldEmployeeID = new JTextField();
		textFieldEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));

		textFieldDepartmentID = new JLabel();
		textFieldDepartmentID.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldEmployeeName = new JLabel();
		textFieldEmployeeName.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldBaseSalary = new JLabel();
		textFieldBaseSalary.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldCategory = new JLabel();
		textFieldCategory.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldSickFee = new JLabel();
		textFieldSickFee.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldChildCareFee = new JLabel();
		textFieldChildCareFee.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldRentFee = new JLabel();
		textFieldRentFee.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldWaterElectricityFee = new JLabel();
		textFieldWaterElectricityFee.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		textFieldOtherFee = new JLabel();
		textFieldOtherFee.setPreferredSize(new Dimension(targetPanelWidth * 2 / 10, 30));

		buttonInquiry = new JButton("查询");

		this.setLayout(new GridBagLayout());
		this.add(labelTitle,
				getComponentConstraints(100, 100, 0, 0, 10, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));

		this.add(labelEmployeeID,
				getComponentConstraints(100, 100, 1, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldEmployeeID,
				getComponentConstraints(100, 100, 3, 2, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(buttonInquiry,
				getComponentConstraints(100, 100, 5, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));
		this.add(spacePlace,
				getComponentConstraints(100, 100, 9, 2, 1, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));

		this.add(labelDepartmentID,
				getComponentConstraints(100, 100, 1, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldDepartmentID,
				getComponentConstraints(100, 100, 3, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelEmployeeName,
				getComponentConstraints(100, 100, 5, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldEmployeeName,
				getComponentConstraints(100, 100, 7, 4, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelCategory,
				getComponentConstraints(100, 100, 1, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldCategory,
				getComponentConstraints(100, 100, 3, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelSickFee,
				getComponentConstraints(100, 100, 5, 6, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldSickFee,
				getComponentConstraints(100, 100, 7, 6, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelChildCareFee,
				getComponentConstraints(100, 100, 1, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldChildCareFee,
				getComponentConstraints(100, 100, 3, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelRentFee,
				getComponentConstraints(100, 100, 5, 8, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldRentFee,
				getComponentConstraints(100, 100, 7, 8, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelWaterElectricityFee,
				getComponentConstraints(100, 100, 1, 10, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldWaterElectricityFee,
				getComponentConstraints(100, 100, 3, 10, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelOtherFee,
				getComponentConstraints(100, 100, 5, 10, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldOtherFee,
				getComponentConstraints(100, 100, 7, 10, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(labelBaseSalary,
				getComponentConstraints(100, 100, 1, 12, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldBaseSalary,
				getComponentConstraints(100, 100, 3, 12, 2, 2, GridBagConstraints.NONE, GridBagConstraints.WEST));

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

	public void addListener() {
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
		buttonInquiry.addActionListener(e -> {
			String text = textFieldEmployeeID.getText();
			if (text.length() < 18) {
				JOptionPane.showMessageDialog(null, "不合法的身份证号", "错误", JOptionPane.OK_CANCEL_OPTION);
			} else {
				CurrentMonthEmployeeSalaryInformationManager currentMonthEmployeeSalaryInformationManager;
				try {
					currentMonthEmployeeSalaryInformationManager = new CurrentMonthEmployeeSalaryInformationManager();
					String[] result = currentMonthEmployeeSalaryInformationManager.quiryByID(text);
					if (result[0].equals("null")) {
						JOptionPane.showMessageDialog(null, "请检查身份证号码，查无此人", "错误", JOptionPane.OK_CANCEL_OPTION);
					} else {
						textFieldDepartmentID.setText(result[0]);
						textFieldEmployeeName.setText(result[1]);
						textFieldCategory.setText(result[2]);
						textFieldSickFee.setText(result[3]);
						textFieldChildCareFee.setText(result[4]);
						textFieldRentFee.setText(result[5]);
						textFieldWaterElectricityFee.setText(result[6]);
						textFieldOtherFee.setText(result[7]);
						textFieldBaseSalary.setText(result[8]);
					}
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.OK_CANCEL_OPTION);
				}
			}
		});
	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setSize(screenSize.width / 2, screenSize.height / 2);
		jFrame.setLocation(screenSize.width / 2 - screenSize.width / 4, screenSize.height / 2 - screenSize.height / 4);
		jFrame.add(new PanelFuncInquirySalaryDetails());

		jFrame.setVisible(true);
	}

}
