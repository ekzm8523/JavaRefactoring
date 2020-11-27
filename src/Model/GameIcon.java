package Model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class GameIcon {
   private String objectName;
   private static Image originImg, changedImg;
   private static ImageIcon originIcon;
   private static GameIcon dogFront, dogBack, dogLeft, dogRight, wall, ground, bone, bowl, fullBowl, tree;

   public GameIcon(String objectName) {
      this.objectName = objectName;
   }

   static ImageIcon getDogFront(String dogFrontName) {
      if (dogFront == null)
         dogFront = new GameIcon(dogFrontName);

      return dogFront.getIcon();
   }

   static ImageIcon getDogBack(String dogBackName) {
      if (dogBack == null)
         dogBack = new GameIcon(dogBackName);

      return dogBack.getIcon();
   }

   static ImageIcon getDogLeft(String dogLeftName) {
      if (dogLeft == null)
         dogLeft = new GameIcon(dogLeftName);

      return dogLeft.getIcon();
   }

   static ImageIcon getDogRight(String dogRightName) {
      if (dogRight == null)
         dogRight = new GameIcon(dogRightName);

      return dogRight.getIcon();
   }

   static ImageIcon getWall(String wallName) {
      if (wall == null)
         wall = new GameIcon(wallName);

      return wall.getIcon();
   }

   static ImageIcon getGround(String groundName) {
      if (ground == null)
         ground = new GameIcon(groundName);

      return ground.getGround();
   }

   static ImageIcon getBone(String boneName) {
      if (bone == null)
         bone = new GameIcon(boneName);

      return bone.getIcon();
   }

   static ImageIcon getBowl(String bowlName) {
      if (bowl == null)
         bowl = new GameIcon(bowlName);

      return bowl.getIcon();
   }

   static ImageIcon getFullBowl(String fullBowlName) {
      if (fullBowl == null)
         fullBowl = new GameIcon(fullBowlName);

      return fullBowl.getIcon();
   }

   static ImageIcon getTree(String treeName) {
      if (tree == null)
         tree = new GameIcon(treeName);

      return tree.getIcon();
   }

   public ImageIcon getIcon() {
      originIcon = new ImageIcon(getIconName());
      originImg = originIcon.getImage();
      changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
      return new ImageIcon(changedImg);
   }
   public ImageIcon getIcon(int column, int row) {
       originIcon = new ImageIcon(getIconName());
       originImg = originIcon.getImage();
       changedImg = originImg.getScaledInstance(column, row, Image.SCALE_SMOOTH);
       return new ImageIcon(changedImg);
   }

   public ImageIcon getGround() {
      originIcon = new ImageIcon(getIconName());
      originImg = originIcon.getImage();
      return new ImageIcon(originImg);
   }

   private String getIconName() {
      return "images/" + objectName;
   }

}