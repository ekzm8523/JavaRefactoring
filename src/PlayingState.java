public class PlayingState implements State {

	private static PlayingState playingState;

	private PlayingState() {
		System.out.println("Game Stage Page 출력");
	}

	public static PlayingState getInstance() {
		Game game = GameManager.getInstance().getGame();
		Model barObject = GameManager.getInstance().getModel();

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
		game.setState(GameOverState.getInstance());
		System.out.println("Playing -> GameOver Page 진입");
	}

	@Override
	public void gameClear() {
		Game game = GameManager.getInstance().getGame();
		game.setState(GameClearState.getInstance());
		System.out.println("Playing -> GameClear Page 진입");
	}

	@Override
	public void nextStage() {
		Game game = GameManager.getInstance().getGame();
		Model barObject = GameManager.getInstance().getModel();

		barObject.setScore();
		barObject.levelUp();

		game.setState(PlayingState.getInstance());
		System.out.println("state는 그대로 PlayingState 이고 보여주는 Stage를 바꿔야 함");
	}

	@Override
	public void mainButton() {
		// TODO Auto-generated method stub

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

}