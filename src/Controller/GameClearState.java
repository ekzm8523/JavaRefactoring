package Controller;

import Model.BarObject;
import View.Game;
import View.GameClearPanel;

public class GameClearState implements State {

	private static GameClearState gameClearState;

	private GameClearState() {
	}

	public static GameClearState getInstance() {
		Game game = GameManager.getInstance().getGame();

		game.getContentPane().removeAll();
		game.getContentPane().add(GameClearPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (gameClearState == null)
			gameClearState = new GameClearState();

		return gameClearState;
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