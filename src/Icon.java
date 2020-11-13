import java.awt.Image;
import javax.swing.ImageIcon;

public class Icon {
   private String objectName;
   static Image originImg, changedImg;
   static ImageIcon originIcon;
   private static Icon dogFront, dogBack, dogLeft, dogRight, wall, ground, bone, bowl, fullBowl, tree;

   public Icon(String objectName) {
      this.objectName = objectName;
   }

   static ImageIcon getDogFront(String dogFrontName) {
      if (dogFront == null)
         dogFront = new Icon(dogFrontName);

      return dogFront.getIcon();
   }

   static ImageIcon getDogBack(String dogBackName) {
      if (dogBack == null)
         dogBack = new Icon(dogBackName);

      return dogBack.getIcon();
   }

   static ImageIcon getDogLeft(String dogLeftName) {
      if (dogLeft == null)
         dogLeft = new Icon(dogLeftName);

      return dogLeft.getIcon();
   }

   static ImageIcon getDogRight(String dogRightName) {
      if (dogRight == null)
         dogRight = new Icon(dogRightName);

      return dogRight.getIcon();
   }

   static ImageIcon getWall(String wallName) {
      if (wall == null)
         wall = new Icon(wallName);

      return wall.getIcon();
   }

   static ImageIcon getGround(String groundName) {
      if (ground == null)
         ground = new Icon(groundName);

      return ground.getGround();
   }

   static ImageIcon getBone(String boneName) {
      if (bone == null)
         bone = new Icon(boneName);

      return bone.getIcon();
   }

   static ImageIcon getBowl(String bowlName) {
      if (bowl == null)
         bowl = new Icon(bowlName);

      return bowl.getIcon();
   }

   static ImageIcon getFullBowl(String fullBowlName) {
      if (fullBowl == null)
         fullBowl = new Icon(fullBowlName);

      return fullBowl.getIcon();
   }

   static ImageIcon getTree(String treeName) {
      if (tree == null)
         tree = new Icon(treeName);

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