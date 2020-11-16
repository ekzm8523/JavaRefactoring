import javax.swing.JOptionPane;

public class RankState implements State {

	private static RankState rankState;

	private RankState() {
		System.out.println("Ranking Page 출력");
	}

	public static RankState getInstance() {
		Game game = GameManager.getInstance().getGame();
		game.getContentPane().removeAll();
		
		Rank.getInstance().hideBeforeRankLabel();
		Rank.getInstance().printRanking(RankPanel.getInstance());
		
		game.getContentPane().add(RankPanel.getInstance());
		game.repaint();
		game.setVisible(true);

		if (rankState == null)
			rankState = new RankState();

		return rankState;
	}

	@Override
	public void mainButton() {
		Game game = GameManager.getInstance().getGame();
		game.setState(MainState.getInstance());
		System.out.println("Ranking -> Main page 진입");
	}

	@Override
	public void rankButton() {
		Game game = GameManager.getInstance().getGame();
		// TODO Auto-generated method stub
		System.out.println("Ranking Page 에서 랭킹 버튼을 눌렀음");
	}

	@Override
	public void startButton() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameOver() {
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