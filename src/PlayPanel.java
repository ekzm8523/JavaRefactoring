
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayPanel extends JPanel {
	private int mapArray[][]; // 화면에 보여질 맵 정보
	private Map map;
	private Undo undo;
	private boolean isMovable = true; // 움직였는지, 게임 오버됐는지 반환
	
	private TimeThread lblTime;
	
	private ImageIcon stageIcon,scoreIcon,moveIcon;
	private JLabel lblStage, lblScore, lblMove;
	
	Game game = GameManager.getInstance().getGame();
	Model model = GameManager.getInstance().getModel();
	
// *******
	
	Player player = new Player();
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

// *******
	public PlayPanel(int mapArray[][]) {
		
		map = new Map(mapArray);
		undo = new Undo();
		
		initPanelSetting();
		
		game.listener.addPlayPanelKeyListner(this);

		map.DrawObject(this, player, boneList, riceBowlList);
		map.DrawMap(this);

		// ---------------------------------맵 그리기------------------------------------
		
	}
	public void initPanelSetting() {
		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);
		
	    stageIcon= new Icon("stage" + model.getLevel() + ".png").getIcon(100,100);
	    lblStage = new Label(stageIcon).getPlayLabel(0, 0, 100, 100);
	         
	    scoreIcon= new Icon("ScoreBoard.png").getIcon(200,100);
	    lblScore = new Label(scoreIcon, SwingConstants.CENTER).getPlayLabel(Color.blue,Color.black,100, 0, 200, 100);
	         
	    moveIcon= new Icon("MoveBoard.png").getIcon(150,100);
	    lblMove = new Label(moveIcon, SwingConstants.CENTER).getPlayLabel(Color.red,Color.black,300, 0, 150, 100);

		lblTime = new TimeThread();

		add(lblStage);
		add(lblScore);
		add(lblMove);
		add(lblTime);
	}
	public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드

		switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
		case 38: // UP-------------------------------------------------------------------------------------------
			game.getController().moveUp(player, undo, map, boneList, riceBowlList);
			break; // 아래, 왼쪽, 오른쪽도 같은 방법으로 바꿔준다.
		case 40: // DOWN-------------------------------------------------------------------------------
			game.getController().moveDown(player, undo, map, boneList, riceBowlList);
			break;
		case 37: // LEFT----------------------------------------------------------------------------------
			game.getController().moveLeft(player, undo, map, boneList, riceBowlList);
			break;
		case 39: // RIGHT--------------------------------------------------------------------------------------
			game.getController().moveRight(player, undo, map, boneList, riceBowlList);
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
		return game.getGame().getController().isGameOver(player, undo, map, boneList, riceBowlList);
	}

	public boolean getIsMovable() {
		return isMovable;
	} // 움직였는지 반환

	public void setIsMovable(boolean init) {
		isMovable = init;
	} // 움직임 여부 초기화
	
}