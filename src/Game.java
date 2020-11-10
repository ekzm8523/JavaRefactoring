import javax.swing.JFrame;

public class Game extends JFrame {
	
	 
	private State state;
	public GameController controller;
	public Game() {
		GameManager.getInstance().setGame(this);
		controller = new GameController();
		setTitle("배고픈 댕댕이");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 700);
		setVisible(true);
		
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
	
	public void gameClaer() {
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
