public class RankState implements State {

	private static RankState rankState;

	private RankState() {
		System.out.println("Ranking Page 출력");
	}

	public static RankState getInstance(Game game) {
		game.getContentPane().removeAll();
		game.getContentPane().add(RankPanel.getInstance(game));
		game.repaint();
		game.setVisible(true);

		if (rankState == null)
			rankState = new RankState();

		return rankState;
	}

	@Override
	public void mainButton(Game game) {
		game.setState(MainState.getInstance(game));
		System.out.println("Ranking -> Main page 진입");
	}

	@Override
	public void rankButton(Game game) {
		// TODO Auto-generated method stub
		System.out.println("Ranking Page 에서 랭킹 버튼을 눌렀음");
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
	public void gameClear(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nextStage(Game game) {
		// TODO Auto-generated method stub

	}

}
