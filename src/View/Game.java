package View;
import javax.swing.JFrame;

import Controller.GameController;
import Controller.GameManager;
import Controller.MyListener;
import Model.BackgroundMusic;
import Model.Model;
import Rank.Rank;

public class Game extends JFrame {
	
	public View view;
	public MyListener listener;
	
	private State state;
	private GameController controller;
	
	public Game() {
		GameManager.getInstance().setGame(this);
		GameManager.getInstance().setModel(new Model());
	    BackgroundMusic.getInstance().startMusic(); // 배경음악 받아서 재생

		controller = new GameController();
		view = new View();
		listener = new MyListener();
		setTitle("배고픈 댕댕이");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setVisible(true);
		
		Rank.getInstance().connectDB();
		state = MainState.getInstance();
		pack();
		
	}
	public GameController getController() {
		return controller;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public void mainButton() {
		this.state.mainButton();
	}
	
	public void rankButton() {
		this.state.rankButton();
	}
	
	public void startButton() {
		this.state.startButton();
	}
	
	public void gameClear() {
		this.state.gameClear();
	}
	
	public void gameOver() {
		this.state.gameOver();
	}
	
	public void nextStage() {
		this.state.nextStage();
	}
	
	public Game getGame() {
		return this;
	}
}