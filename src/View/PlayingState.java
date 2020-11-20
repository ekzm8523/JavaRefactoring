package View;

import Controller.GameManager;
import Controller.TimeThread;
import Model.BarObject;
import Model.MapArray;

public class PlayingState implements State {

	private static PlayingState playingState;

	private PlayingState() {
	}

	public static PlayingState getInstance() {
		Game game = GameManager.getInstance().getGame();
		BarObject barObject = GameManager.getInstance().getBarObject();

		game.getContentPane().removeAll();
		game.getContentPane().add(new PlayPanel(new MapArray(barObject.getLevel()).getArray()));
		game.getContentPane().getComponent(0).requestFocus();
		game.repaint();
		game.setVisible(true);

		if (playingState == null) {
			playingState = new PlayingState();
		}

		return playingState;
	}

	@Override
	public void gameOver() {
		Game game = GameManager.getInstance().getGame();
		TimeThread time = TimeThread.getInstance();
		game.setState(GameOverState.getInstance());
		time.stop(true);
		time.setNull();
	}

	@Override
	public void gameClear() {
		Game game = GameManager.getInstance().getGame();
		TimeThread time = TimeThread.getInstance();
		game.setState(GameClearState.getInstance());
		time.stop(true);
		time.setNull();
	}

	@Override
	public void nextStage() {
		Game game = GameManager.getInstance().getGame();
		BarObject barObject = GameManager.getInstance().getBarObject();
		TimeThread time = TimeThread.getInstance();

		barObject.calculateScore(time.getMinute(), time.getSecond());
		barObject.initMove();
		barObject.levelUp();

		time.stop(true);
		time.setNull();

		game.setState(PlayingState.getInstance());
	}

	@Override
	public void mainState() {
	}

	@Override
	public void rankState() {
	}

	@Override
	public void playingState() {
	}
}