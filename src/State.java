public interface State {
	
	public void mainButton(Game game);
	public void rankButton(Game game);
	public void startButton(Game game);
	public void inputButton(Game game);
	public void gameOver(Game game);
	public void gameClear(Game game);
	public void nextStage(Game game);
}
