public class GameOverState implements State {
	
	private static GameOverState gameOverState;
	
	private GameOverState() {
		System.out.println("GameOver Page 출력");
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
	public void gameOver() {
		
	}
	
	@Override
	public void mainButton() {
		Game game = GameManager.getInstance().getGame();
		game.setState(MainState.getInstance());
		System.out.println("GameOver -> Main page 진입");
	}

	@Override
	public void rankButton() {
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
	public void gameClear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextStage() {
		// TODO Auto-generated method stub
		
	}
	
}