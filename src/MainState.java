
public class MainState implements State {

	private static MainState mainState;

	private MainState() {
		System.out.println("Main Page 출력");
	}

	public static MainState getInstance(Game game) {
		game.getContentPane().removeAll();
		game.getContentPane().add(MainPanel.getInstance(game));
		game.repaint();
		game.setVisible(true);
		if (mainState == null)
			mainState = new MainState();

		return mainState;
	}

	@Override
	public void rankButton(Game game) {
		game.setState(RankState.getInstance(game));
		System.out.println("Main -> Ranking Page 진입");
	}

	@Override
	public void startButton(Game game) {
		game.setState(PlayingState.getInstance(game));
		System.out.println("Main -> Game Playing Page 진입");
	}

	@Override
	public void inputButton(Game game) {
	}

	@Override
	public void mainButton(Game game) {
	}

	@Override
	public void gameOver(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameClear(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextStage(Game game) {
		// TODO Auto-generated method stub

	}

}
