package View;

import javax.swing.JFrame;

import Controller.GameController;
import Controller.GameManager;
import Controller.MyListener;
import Controller.Rank;
import Controller.SoundManager;
import Model.BackgroundMusic;
import Model.BarObject;

public class Game extends JFrame {
	
	private State state;
	
	public Game() {
		GameManager.getInstance().setGame(this);
		GameManager.getInstance().setBarObject(new BarObject());
		SoundManager.getInstance().getBGM().startMusic();
		SoundManager.getInstance().getBGM().loopClip();
	    
		setTitle("배고픈 댕댕이");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setVisible(true);
		
		Rank.getInstance().connectDB();
		state = MainState.getInstance();
		pack();
		
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void mainState() {
		this.state.mainState();
	}
	
	public void rankState() {
		this.state.rankState();
	}
	
	public void playingState() {
		this.state.playingState();
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