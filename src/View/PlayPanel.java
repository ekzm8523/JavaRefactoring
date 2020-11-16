package View;
import javax.swing.*;

import Controller.GameManager;
import Controller.TimeThread;
import Model.BarkSound;
import Model.Bone;
import Model.Icon;
import Model.Label;
import Model.Model;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;

import java.awt.*;
import java.util.ArrayList;

public class PlayPanel extends JPanel {
	private int mapArray[][]; // 화면에 보여질 맵 정보
	private Map map;
	private Undo undo;
	private boolean isMovable = true; // 움직였는지, 게임 오버됐는지 반환
	private ImageIcon stageIcon,scoreIcon,moveIcon;
	JLabel lblStage, lblScore, lblMove;
	boolean isGameOver;
	Game game = GameManager.getInstance().getGame();
	Model barObject = GameManager.getInstance().getModel();
	
// *******
	Player player = new Player();
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

// *******
   public PlayPanel(int mapArray[][]) {
      
      //mapArray = a; // 맵 배열 전달 받기
      map = new Map(mapArray);
      undo = new Undo();
      
      // 패널 기본 설정
      setBounds(0, 100, 600, 600);
      setBackground(Color.red);
      setLayout(null);
      game.listener.addPlayPanelKeyListner(this);


          stageIcon= new Icon("stage" + barObject.getLevel() + ".png").getIcon(100,100);
          lblStage = new Label(stageIcon).getPlayLabel(0, 0, 100, 100);
            
          scoreIcon= new Icon("ScoreBoard.png").getIcon(200,100);
          lblScore = new Label(scoreIcon, SwingConstants.CENTER).getPlayLabel(Color.blue,Color.black,100, 0, 200, 100);
          lblScore.setText(Integer.toString(barObject.getScore()));
            
          moveIcon= new Icon("MoveBoard.png").getIcon(150,100);
          lblMove = new Label(moveIcon, SwingConstants.CENTER).getPlayLabel(Color.red,Color.black,300, 0, 150, 100);
          lblMove.setText(Integer.toString(barObject.getMove()));
          

      TimeThread lblTime = TimeThread.getInstance();

      add(lblStage);
      add(lblScore);
      add(lblMove);
      add(lblTime);

      map.DrawObject(this, player, boneList, riceBowlList);
      map.DrawMap(this);

      // ---------------------------------맵 그리기------------------------------------
      
   }



   public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드
      Model barObject = GameManager.getInstance().getModel();
      

      switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
      case 38: // UP-------------------------------------------------------------------------------------------
    	  game.getController().moveUp(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
    	  break; // 아래, 왼쪽, 오른쪽도 같은 방법으로 바꿔준다.
      case 40: // DOWN-------------------------------------------------------------------------------
    	  game.getController().moveDown(player, undo, map, boneList, riceBowlList);
     	 
         BarkSound.getInstance().startMusic();
         break;
      case 37: // LEFT----------------------------------------------------------------------------------
    	  game.getController().moveLeft(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
         break;
      case 39: // RIGHT--------------------------------------------------------------------------------------
    	  game.getController().moveRight(player, undo, map, boneList, riceBowlList);
    	  BarkSound.getInstance().startMusic();
          
         break;
      }
      
   } // move

   public void undo() {
	   game.getController().undo(player, undo, map, boneList, riceBowlList);
   } // undo

   public void view(int key) { // 화면에 보이게 하기
      game.view.inputKeyValueView(key, player, undo, map, boneList, riceBowlList);

   } // view()

   public boolean isGameClear() { // 라운드 클리어 했는지 반환
      return game.getController().isGameClear(player, undo, map, boneList, riceBowlList);
   }

   public boolean isGameOver() { // 움직일 수 없는 상황에 도달했는지(박스가 ㄱ자 벽에 붙으면 게임 오버)
	   return game.getController().isGameOver(player, undo, map, boneList, riceBowlList);

   }
}