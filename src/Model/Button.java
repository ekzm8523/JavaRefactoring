package Model;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button {
   private ImageIcon buttonBeforeName;
   private ImageIcon buttonAfterName;
   private String buttonIconName;
   private JButton button;

   public Button(String buttonIconName,ImageIcon buttonBeforeName, ImageIcon buttonAfterName) {
      this.buttonBeforeName = buttonBeforeName;
      this.buttonAfterName=buttonAfterName;
      this.buttonIconName=buttonIconName;
      }

   public JButton getButton(Color backColor, int widthPosition, int heightPosition, int widthLengh, int heightLength) {
      button = new JButton(getButtonIconName(), getButtonBeforeName());
      button.setBounds(widthPosition, heightPosition, widthLengh, heightLength);
      button.setBorderPainted(false);
      button.setContentAreaFilled(false);
      button.setForeground(backColor);
      button.setRolloverIcon(getButtonAfterName());
      button.setPressedIcon(getButtonAfterName());

      return button;
   }

   private ImageIcon getButtonAfterName() {
      return buttonAfterName;
   }
   private ImageIcon getButtonBeforeName() {
      return buttonBeforeName;
   }

   private String getButtonIconName() {
      return buttonIconName;
   }
}