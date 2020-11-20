package Model;
public class BarObject {
	private int totalScore;
	private int cntMove;
	private int stageLevel;

	public void calculateScore(int minute, int second) {
		totalScore = totalScore + ((minute * 60 + second) * stageLevel - cntMove);
	}

	public void moveCountUp() {
		cntMove++;
	}

	public void moveCountDown() {
		cntMove--;
	}

	public void initMove() {
		cntMove = 0;
	}

	public void levelUp() {
		stageLevel++;
	}

	public void initBarObjects() {
		totalScore = 0;
		cntMove = 0;
		stageLevel = 1;
	}

	public int getScore() {
		return totalScore;
	}

	public int getMoveCount() {
		return cntMove;
	}

	public int getLevel() {
		return stageLevel;
	}

	public BarObject() {
		totalScore = 0;
		cntMove = 0;
		stageLevel = 1;
	}

}