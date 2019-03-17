/**
 * 
 */
package salary.managment.system.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;

import salary.managment.system.submenubar.SubMenuBarInputModification;
import salary.managment.system.submenubar.SubMenuBarInquirySalary;
import salary.managment.system.view.AppConstantsField;

/**
 * @author Tortoise
 *
 */
public class PanelInquirySalary extends JPanel {

	public static SubMenuBarInquirySalary subMenuBarInquirySalary;
	public static JPanel mainPanel;

	public PanelInquirySalary() throws FileNotFoundException, IOException {
		Dimension preferredSize = new Dimension(AppConstantsField.MAIN_PANEL_WIDTH,
				AppConstantsField.MAIN_PANEL_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(Color.BLACK);
		this.setLayout(new BorderLayout());
		this.addSubMenuBar();
	}

	private void addSubMenuBar() throws FileNotFoundException, IOException {
		subMenuBarInquirySalary = new SubMenuBarInquirySalary();
		mainPanel = new JPanel();
		add(subMenuBarInquirySalary, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
