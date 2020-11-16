
public class Point {

private int x, y;
   
   Point() {
      this.x = -1;
      this.y = -1;
   }
   
   public void goLeft() {
      this.x = this.x - 1;
   }
   
   public void goRight() {
      this.x = this.x + 1;
   }
   
   public void goUp() {
      this.x = this.y - 1;
   }
   
   public void goDown() {
      this.x = this.y + 1;
   }
   
   public void setX(int x) {
      this.x = x;
   }
   
   public void setY(int y) {
      this.y = y;
   }
   
   public int getX() {
      return this.x;
   }
   
   public int getY() {
      return this.y;
   }
}