package View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.BarkSound;
import Controller.GameController;
import Controller.GameManager;
import Controller.GameListener;
import Controller.SoundManager;
import Controller.TimeThread;
import Model.BarObject;
import Model.Bone;
import Model.GameIcon;
import Model.AllLabel;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;

public class PlayPanel extends JPanel {
	private Undo undo;
	private ImageIcon stageIcon, scoreIcon, moveIcon;
	private JLabel lblStage, lblScore,lblMove;
	private AllLabel labelMove,labelStage, labelScore;
	boolean isGameOver;
	private Game game = GameManager.getInstance().getGame();
	private BarObject barObject = GameManager.getInstance().getBarObject();

	private Player player = new Player();
	private ArrayList<Bone> boneList = new ArrayList<>();
	private ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

	public PlayPanel(int mapArray[][]) {

		MyView.getInstance().SettingMap(mapArray);
		undo = new Undo();

		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);
		GameListener.getInstance().addPlayPanelKeyListner(this);

		stageIcon = new GameIcon("stage" + getBarObject().getLevel() + ".png").getIcon(100, 100);
		labelStage = new AllLabel(stageIcon);
		labelStage.setPlayLabelWithPosition(0, 0, 100, 100);
		lblStage=labelStage.getLabel();
		
		scoreIcon = new GameIcon("ScoreBoard.png").getIcon(200, 100);
		labelScore = new AllLabel(scoreIcon, SwingConstants.CENTER);
		labelScore.setLabelWithColorNPosition(Color.blue, Color.black, 100,0, 200, 100);
		lblScore=labelScore.getLabel();
		lblScore.setText(Integer.toString(getBarObject().getScore()));

		moveIcon = new GameIcon("MoveBoard.png").getIcon(150, 100);
		labelMove=new AllLabel(moveIcon, SwingConstants.CENTER);
		labelMove.setLabelWithColorNPosition(Color.red, Color.black, 300, 0,150, 100);
		lblMove=labelMove.getLabel();
		setLblMove(lblMove);
		getLblMove().setText(Integer.toString(getBarObject().getMoveCount()));

		TimeThread lblTime = TimeThread.getInstance();

		add(lblStage);
		add(lblScore);
		add(getLblMove());
		add(lblTime);

		MyView.getInstance().DrawObject(this, player, boneList, riceBowlList);
		MyView.getInstance().DrawMap(this);
	}

	public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드
		BarObject barObject = GameManager.getInstance().getBarObject();

		switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
		case 38: // UP
			GameController.getInstance().moveUp(player, undo, boneList, riceBowlList);
			break;
			
		case 40: // DOWN
			GameController.getInstance().moveDown(player, undo, boneList, riceBowlList);
			break;
			
		case 37: // LEFT
			GameController.getInstance().moveLeft(player, undo, boneList, riceBowlList);
			break;
			
		case 39: // RIGHT
			GameController.getInstance().moveRight(player, undo, boneList, riceBowlList);
			break;
			
		case 90:
			GameController.getInstance().undo(player, undo, boneList, riceBowlList);
			break;
		}

	}

	public void undo() {
		GameController.getInstance().undo(player, undo, boneList, riceBowlList);
	}

	public void view(int key) {
		MyView.getInstance().inputKeyValueView(key, player, undo, boneList, riceBowlList);
	}

	public boolean isGameClear() {
		return GameController.getInstance().isGameClear(player, undo, boneList, riceBowlList);
	}

	public boolean isGameOver() {
		return GameController.getInstance().isGameOver(player, undo, boneList, riceBowlList);
	}

	public JLabel getLblMove() {
		return lblMove;
	}

	public void setLblMove(JLabel lblMove) {
		this.lblMove = lblMove;
	}

	public BarObject getBarObject() {
		return barObject;
	}

	public void setBarObject(BarObject barObject) {
		this.barObject = barObject;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}