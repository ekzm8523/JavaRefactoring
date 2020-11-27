package Controller;

public interface State {
	
	public void mainState();
	public void rankState();
	public void playingState();
	public void gameOver();
	public void gameClear();
	public void nextStage();
}