package View;
import Controller.GameManager;
import Model.BackgroundMusic;
import Rank.RankState;

public class MainState implements State {

	private static MainState mainState;

	private MainState() {
		System.out.println("Main Page 출력");
	}

	public static MainState getInstance() {
		Game game = GameManager.getInstance().getGame();
		game.getContentPane().removeAll();
		game.getContentPane().add(MainPanel.getInstance());
		game.repaint();
		game.setVisible(true);
		if (mainState == null)
			mainState = new MainState();
		
		return mainState;
	}

	@Override
	public void rankButton() {
		Game game = GameManager.getInstance().getGame();
		game.setState(RankState.getInstance());
		
		System.out.println("Main -> Ranking Page 진입");
	}

	@Override
	public void startButton() {
		Game game = GameManager.getInstance().getGame();
		game.setState(PlayingState.getInstance());
		BackgroundMusic.getInstance().stopMusic();
		game.getController().setIsGameOverTrue(); 
		System.out.println("Main -> Game Playing Page 진입");
	}

	@Override
	public void mainButton() {
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameClear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextStage() {
		// TODO Auto-generated method stub

	}

}