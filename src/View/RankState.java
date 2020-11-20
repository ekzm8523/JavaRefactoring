package View;

import Controller.GameManager;

public class RankState implements State {

	private static RankState rankState;

	private RankState() {
	}

	public static RankState getInstance() {
		Game game = GameManager.getInstance().getGame();
		game.getContentPane().removeAll();

		RankPanel.getInstance().hideLabelBeforePrintRanking();
		RankPanel.getInstance().printRanking(RankPanel.getInstance());
		

		game.getContentPane().add(RankPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (rankState == null)
			rankState = new RankState();

		return rankState;
	}

	@Override
	public void mainState() {
		Game game = GameManager.getInstance().getGame();
		game.setState(MainState.getInstance());
	}

	@Override
	public void rankState() {
	}

	@Override
	public void playingState() {
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