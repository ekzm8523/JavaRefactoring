package Model;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Label {
   private String labelName;
   private ImageIcon labelIcon;
   private JLabel label;
   private static Color mainColor;
  
   public Label(String labelName,ImageIcon labelIcon) {
      this.labelName=labelName;
      this.labelIcon=labelIcon;
   }
   public Label(ImageIcon labelIcon, int CENTER) {
      this.labelIcon=labelIcon;
   }
   public Label(ImageIcon labelIcon) {
      this.labelIcon=labelIcon;
   }
   public Label(String labelName) {
	   this.labelName=labelName;
   }
   static Color getmainColor() {
      if (mainColor == null)
         mainColor = new Color(226, 115, 111);

      return mainColor;
   }
   
   public JLabel getMainLabel(int widthPosition,int heightPosition, int widthLength, int heightLength){
      label = new JLabel(getLabelName(), getLabelIcon(), SwingUtilities.RIGHT);
      label.setFont(MyFont.getMainFont());
      label.setForeground(getmainColor());
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      return label;
   }
   public JLabel getPlayLabel(Color backColor,Color frontColor,int widthPosition,int heightPosition, int widthLength, int heightLength) {
      label= new JLabel(getLabelIcon(), SwingConstants.CENTER);
      label.setOpaque(true);
      label.setBackground(backColor);
      label.setFont(MyFont.getPlayFont());
      label.setForeground(frontColor);
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      label.setHorizontalTextPosition(SwingConstants.CENTER);
      return label;
   }
   public JLabel getPlayLabel(int widthPosition,int heightPosition, int widthLength, int heightLength) {
      label= new JLabel(getLabelIcon());
      label.setOpaque(true);
      label.setBackground(Color.white);
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      return label;
   }
   public JLabel getRankLabel(Font font,int widthPosition,int heightPosition, int widthLength, int heightLength) {
	   label = new JLabel(getLabelName());
	   label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
	   label.setFont(font);
	   return label;
   }
   private String getLabelName() {
      return labelName;
   }
   private ImageIcon getLabelIcon() {
      return labelIcon;
   }
}
