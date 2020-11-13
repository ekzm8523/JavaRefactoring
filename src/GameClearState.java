
public class GameClearState implements State {

	private static GameClearState gameClearState;

	private GameClearState() {
		System.out.println("GameClear Page 출력");
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
	public void gameClear() {
	}

	@Override
	public void mainButton() {
		Game game = GameManager.getInstance().getGame();
		game.setState(MainState.getInstance());
		System.out.println("GameOver -> Main page 진입");
	}

	@Override
	public void rankButton() {
		// TODO Auto-generated method stub
	}

	@Override
	public void startButton() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputButton() {
		// TODO Auto-generated method stub
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
	}

	@Override
	public void nextStage() {
		// TODO Auto-generated method stub

	}

}
