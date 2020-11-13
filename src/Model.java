public class Model {
   private int nScore;
   private int nMove;
   private int nLevel;
   
   void setScore() {
      nScore = nScore + nMove; // 미완료
   }
   
   void moveUp() {
      nMove = nMove+1;
   }
   
   void levelUp() {
      nLevel = nLevel+1;
   }
   
   int getScore() {
      return nScore;
   }
   
   int getMove() {
      return nMove;
   }
   
   int getLevel() {
      return nLevel;
   }
   
   Model() {
      nScore = 0;
      nMove = 0;
      nLevel = 1;
   }
   
}