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
import salary.managment.system.view.AppConstantsField;
import salary.managment.system.view.MainFrame;
import salary.managment.system.view.MenuBarPanel;
import salary.managment.system.view.panel.func.PanelFuncModifyEmployeeBasicInformation;

/**
 * @author Tortoise
 *
 */
public class PanelInputAndModification extends JPanel {

	public static SubMenuBarInputModification subMenuBarInputModification;
	public static PanelFuncModifyEmployeeBasicInformation panelFuncModifyEmployeeBasicInformation;

	public PanelInputAndModification() throws FileNotFoundException, IOException {
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
		subMenuBarInputModification = new SubMenuBarInputModification();
		panelFuncModifyEmployeeBasicInformation = new PanelFuncModifyEmployeeBasicInformation();
		//add(subMenuBarInputModification, BorderLayout.WEST);
		add(panelFuncModifyEmployeeBasicInformation, BorderLayout.CENTER);

	}

}
