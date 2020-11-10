public class PlayingState implements State {

	private static PlayingState playingState;

	private PlayingState() {
		System.out.println("Game Stage Page 출력");
	}

	public static PlayingState getInstance(Game game) {
		game.getContentPane().removeAll();
		game.getContentPane().add(new PlayPanel(new MapArray(1).getArray()));
		game.getContentPane().getComponent(0).requestFocus();
		game.repaint();
		game.setVisible(true);
		
		if (playingState == null) {
			playingState = new PlayingState();
		}
		
		return playingState;
	}

	@Override
	public void gameOver(Game game) {
		game.setState(GameOverState.getInstance(game));
		System.out.println("Playing -> GameOver Page 진입");
	}

	@Override
	public void gameClear(Game game) {
		game.setState(GameClearState.getInstance());
		System.out.println("Playing -> GameClear Page 진입");
	}

	@Override
	public void nextStage(Game game) {
		System.out.println("state는 그대로 PlayingState 이고 보여주는 Stage를 바꿔야 함");
	}

	@Override
	public void mainButton(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rankButton(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startButton(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputButton(Game game) {
		// TODO Auto-generated method stub

	}

}