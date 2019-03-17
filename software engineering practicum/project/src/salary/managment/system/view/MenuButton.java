/**
 * 
 */
package salary.managment.system.view;

import java.awt.Insets;
import java.awt.Image;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.sun.javafx.binding.StringFormatter;

/**
 * @author Tortoise
 *
 */
public class MenuButton extends JButton {

	/**
	 * 
	 * @param iconNormal
	 * @param iconEnable
	 * @param iconDisable
	 * @param tip
	 */
	public MenuButton(ImageIcon iconNormal, ImageIcon iconEnable, ImageIcon iconDisable, String tip) {

		super(iconNormal);
		// 设置鼠标滑过的图标样式
		this.setRolloverIcon(iconEnable);
		// 设置鼠标按压的图标样式
		this.setPressedIcon(iconEnable);
		// 设置按钮不可用时的图标样式
		this.setDisabledIcon(iconDisable);
		// 设置鼠标悬浮文本提示
		this.setToolTipText(tip);
		// 设置按钮无边框
		this.setBorderPainted(false);
		// 设置按钮无焦点渲染
		this.setFocusPainted(false);
		// 设置按钮无内容区
		this.setContentAreaFilled(false);

		this.setFocusable(true);
		// 设置无边距
		this.setMargin(new Insets(0, 0, 0, 0));
	}
}
