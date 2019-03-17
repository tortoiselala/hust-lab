/**
 * 
 */
package salary.managment.system.tools;

import javax.swing.ImageIcon;

import salary.managment.system.view.AppConstantsField;

import java.awt.Image;

/**
 * @author Tortoise
 *
 */
public class ImageTool {
	public static ImageIcon getMenuImageScaleDefault(ImageIcon icon) {
		ImageIcon result = new ImageIcon();
		result.setImage(icon.getImage().getScaledInstance(AppConstantsField.MAIN_WINDOW_MENUBAR_WIDTH / 2,
				AppConstantsField.MAIN_WINDOW_MENUBAR_WIDTH / 2, Image.SCALE_DEFAULT));
		return result;
	}
}
