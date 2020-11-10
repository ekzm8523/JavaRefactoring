
public class GameOverState implements State {
	
	private static GameOverState gameOverState;
	
	private GameOverState() {
		System.out.println("GameOver Page 출력");
	}
	
	public static GameOverState getInstance(Game game) {

		game.getContentPane().removeAll();
		game.getContentPane().add(GameOverPanel.getInstance(game));
		game.repaint();
		game.setVisible(true);
		if (gameOverState == null)
			gameOverState = new GameOverState();

		return gameOverState;
	}

	@Override
	public void gameOver(Game game) {
	}
	
	@Override
	public void mainButton(Game game) {
		game.setState(MainState.getInstance(game));
		System.out.println("GameOver -> Main page 진입");
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

	@Override
	public void gameClear(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextStage(Game game) {
		// TODO Auto-generated method stub
		
	}
	
}
