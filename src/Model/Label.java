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
   private static Color mainBackgroundColor;
  
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
   static Color getMainBackgroundColor() {
      if (mainBackgroundColor == null)
    	  mainBackgroundColor = new Color(226, 115, 111);

      return mainBackgroundColor;
   }
   
   
   // 상태변경 , 반환 메소드로 나눌지 여쭤보기
   public JLabel setMainLabelWithPosition(int widthPosition,int heightPosition, int widthLength, int heightLength){
      label = new JLabel(getLabelName(), getLabelIcon(), SwingUtilities.RIGHT);
      label.setFont(MyFont.getMainPanelFont());
      label.setForeground(getMainBackgroundColor());
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      return label;
   }
   public JLabel setLabelWithColorNPosition(Color backgroundColor,Color fontColor,int widthPosition,int heightPosition, int widthLength, int heightLength) {
      label= new JLabel(getLabelIcon(), SwingConstants.CENTER);
      label.setOpaque(true);
      label.setBackground(backgroundColor);
      label.setFont(MyFont.getPlayPanelFont());
      label.setForeground(fontColor);
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      label.setHorizontalTextPosition(SwingConstants.CENTER);
      return label;
   }
   public JLabel setPlayLabelWithPosition(int widthPosition,int heightPosition, int widthLength, int heightLength) {
      label= new JLabel(getLabelIcon());
      label.setOpaque(true);
      label.setBackground(Color.white);
      label.setBounds(widthPosition, heightPosition, widthLength, heightLength);
      return label;
   }
   public JLabel setRankLabelWithFontNPosition(Font font,int widthPosition,int heightPosition, int widthLength, int heightLength) {
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
