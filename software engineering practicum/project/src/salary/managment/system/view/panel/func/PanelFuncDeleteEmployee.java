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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import salary.managment.system.base.BasicInformationOfEmployeeManagment;

/**
 * @author Tortoise
 *
 */
public class PanelFuncDeleteEmployee extends JPanel {

	private JLabel labelTitle;

	private JLabel labelEmployeeID;
	private JTextField textFieldEmployeeID;

	private JButton buttonConfirm;

	public PanelFuncDeleteEmployee() {
		set();
		addListener();
	}

	public void set() {
		labelTitle = new JLabel("删除职工信息");
		labelEmployeeID = new JLabel("身份证号：");
		textFieldEmployeeID = new JTextField();
		buttonConfirm = new JButton("删除");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int targetPanelWidth = screenSize.width / 2;
		int targetPanelHeight = screenSize.height / 2;

		labelTitle.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));
		labelEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 100));

		textFieldEmployeeID.setPreferredSize(new Dimension(targetPanelWidth * 3 / 10, 30));
		this.setLayout(new GridBagLayout());

		this.add(labelTitle,
				getComponentConstraints(100, 100, 0, 0, 10, 1, GridBagConstraints.NONE, GridBagConstraints.CENTER));

		this.add(labelEmployeeID,
				getComponentConstraints(100, 100, 1, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));
		this.add(textFieldEmployeeID,
				getComponentConstraints(100, 100, 3, 4, 2, 1, GridBagConstraints.NONE, GridBagConstraints.WEST));

		this.add(buttonConfirm,
				getComponentConstraints(100, 100, 5, 4, 1, 1, GridBagConstraints.NONE, GridBagConstraints.EAST));

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
		buttonConfirm.addActionListener(e -> {
			String employeeID = textFieldEmployeeID.getText();
			if (employeeID.length() < 18) {
				JOptionPane.showMessageDialog(null, "不合法的身份证号", "错误", JOptionPane.OK_CANCEL_OPTION);
			}
			BasicInformationOfEmployeeManagment manager;

			try {
				manager = new BasicInformationOfEmployeeManagment(employeeID, "");
				if (manager.isEmployeeInformationExits()) {
					manager.deleteEmployeeInformation();
					JOptionPane.showMessageDialog(null, "删除成功", "成功", JOptionPane.OK_CANCEL_OPTION);

				} else {
					JOptionPane.showMessageDialog(null, "该职工信息不存在", "错误", JOptionPane.OK_CANCEL_OPTION);
				}
			} catch (ClassNotFoundException | SQLException | IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Info", JOptionPane.OK_CANCEL_OPTION);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Info", JOptionPane.OK_CANCEL_OPTION);
			}
		});

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
