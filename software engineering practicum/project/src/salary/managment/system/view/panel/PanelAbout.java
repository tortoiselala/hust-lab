/**
 * 
 */
package salary.managment.system.view.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import salary.managment.system.view.AppConstantsField;

/**
 * @author Tortoise
 *
 */
public class PanelAbout extends JPanel {

	public PanelAbout() {
		Dimension preferredSize = new Dimension(AppConstantsField.MAIN_PANEL_WIDTH,
				AppConstantsField.MAIN_PANEL_HEIGHT);
		this.setPreferredSize(preferredSize);
		this.setMaximumSize(preferredSize);
		this.setMinimumSize(preferredSize);
		this.setBackground(Color.BLACK);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
