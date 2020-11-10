import javax.swing.JFrame;

public class Game extends JFrame {
	
	private State stateManager;
	
	public Game() {
		setTitle("배고픈 댕댕이");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setVisible(true);
	
		stateManager = MainState.getInstance(this);
		pack();
	}
		
	public void setState(State stateManager) {
		this.stateManager = stateManager;
	}
	
	public void mainButton() {
		this.stateManager.mainButton(this);
	}
	
	public void rankButton() {
		this.stateManager.rankButton(this);
	}
	
	public void startButton() {
		this.stateManager.startButton(this);
	}
	
	public void gameClaer() {
		this.stateManager.gameClear(this);
	}
	
	public void gameOver() {
		this.stateManager.gameOver(this);
	}
	
	public void nextStage() {
		this.stateManager.nextStage(this);
	}
	
	public Game getGame() {
		return this;
	}
}
