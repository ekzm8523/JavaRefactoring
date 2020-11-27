package Controller;

import Model.BarObject;
import View.Game;
import View.GameOverPanel;

public class GameOverState implements State {

	private static GameOverState gameOverState;

	private GameOverState() {
	}

	public static GameOverState getInstance() {
		Game game = GameManager.getInstance().getGame();

		game.getContentPane().removeAll();
		game.getContentPane().add(GameOverPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (gameOverState == null)
			gameOverState = new GameOverState();

		return gameOverState;
	}

	@Override
	public void mainState() {
		Game game = GameManager.getInstance().getGame();
		BarObject barObject = GameManager.getInstance().getBarObject();
		game.setState(MainState.getInstance());
		barObject.initBarObjects();

	}

	@Override
	public void rankState() {
	}

	@Override
	public void playingState() {
	}

	@Override
	public void gameClear() {
	}

	@Override
	public void gameOver() {
	}

	@Override
	public void nextStage() {
	}
}