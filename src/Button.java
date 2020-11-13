import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {
	private ImageIcon ButtonIconName;
	private String ButtonName;
	private JButton button;

	public Button(String ButtonName, ImageIcon ButtonIconName) {
		this.ButtonIconName = ButtonIconName;
		this.ButtonName = ButtonName;
	}

	public JButton getButton(Color backColor, int widthPosition, int heightPosition, int widthLengh, int heightLength) {
		button = new JButton(getButtonName(), getButtonIconName());
		button.setBounds(widthPosition, heightPosition, widthLengh, heightLength);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setForeground(backColor);
		button.setRolloverIcon(getButtonIconName());
		button.setPressedIcon(getButtonIconName());

		return button;
	}

	private String getButtonName() {
		return ButtonName;
	}

	private ImageIcon getButtonIconName() {
		return ButtonIconName;
	}
}