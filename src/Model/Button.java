package Model;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {
   private ImageIcon iconBeforeHovering;
   private ImageIcon iconAfterHovering;
   private String buttonIconName;
   private JButton button;

   public Button(String buttonIconName,ImageIcon iconBeforeHovering, ImageIcon iconAfterHovering) {
      this.iconBeforeHovering = iconBeforeHovering;
      this.iconAfterHovering=iconAfterHovering;
      this.buttonIconName=buttonIconName;
      }

   public JButton setButton(Color backColor, int widthPosition, int heightPosition, int widthLengh, int heightLength) {
      button = new JButton(getButtonIconName(), getIconBeforeHovering());
      button.setBounds(widthPosition, heightPosition, widthLengh, heightLength);
      button.setBorderPainted(false);
      button.setContentAreaFilled(false);
      button.setForeground(backColor);
      button.setRolloverIcon(getIconAfterHovering());
      button.setPressedIcon(getIconAfterHovering());

      return button;
   }

   private ImageIcon getIconAfterHovering() {
      return iconAfterHovering;
   }
   private ImageIcon getIconBeforeHovering() {
      return iconBeforeHovering;
   }

   private String getButtonIconName() {
      return buttonIconName;
   }
}