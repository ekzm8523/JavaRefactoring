package View;

import Controller.GameController;
import Controller.GameManager;
import Controller.SoundManager;
import Model.BackgroundMusic;

public class MainState implements State {

	private static MainState mainState;

	private MainState() {
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
	public void rankState() {
		Game game = GameManager.getInstance().getGame();
		game.setState(RankState.getInstance());
	}

	@Override
	public void playingState() {
		Game game = GameManager.getInstance().getGame();
		game.setState(PlayingState.getInstance());
		SoundManager.getInstance().getBGM().stopMusic();
		GameController.getInstance().setIsGameOverTrue();
	}

	@Override
	public void mainState() {
	}

	@Override
	public void gameOver() {
	}

	@Override
	public void gameClear() {
	}

	@Override
	public void nextStage() {
	}
}