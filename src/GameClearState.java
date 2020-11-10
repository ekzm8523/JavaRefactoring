
public class GameClearState implements State {
	
	private static GameClearState gameClearState;
	
	private GameClearState() {
		System.out.println("GameClear Page 출력");
	}
	
	public static GameClearState getInstance() {
		if (gameClearState == null)
			gameClearState = new GameClearState();
		
		return gameClearState;
	}
	
	@Override
	public void gameClear(Game game) {	
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

	@Override
	public void gameOver(Game game) {
		// TODO Auto-generated method stub
	}

	@Override
	public void nextStage(Game game) {
		// TODO Auto-generated method stub
		
	}

}
