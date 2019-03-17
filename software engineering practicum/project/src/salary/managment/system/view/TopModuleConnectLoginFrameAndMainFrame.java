/**
 * 
 */
package salary.managment.system.view;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Tortoise
 *
 */
public class TopModuleConnectLoginFrameAndMainFrame {
	public static MainFrame mainFrame = null;
	public static LoginFrame loginFrame = null;

	public TopModuleConnectLoginFrameAndMainFrame() {
		try {
			loginFrame = new LoginFrame();
			mainFrame = new MainFrame();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IOException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TopModuleConnectLoginFrameAndMainFrame main = new TopModuleConnectLoginFrameAndMainFrame();
		
	}
}
