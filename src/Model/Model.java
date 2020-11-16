package Model;
public class Model {
	private int nScore;
	private int nMove;
	private int nLevel;

	void setScore(int minute, int second) {
		nScore = nScore + ((minute * 60 + second) * nLevel - nMove); // 점수계산
	}

	void moveUp() {
		nMove = nMove + 1;
	}

	void moveDown() {
		nMove = nMove - 1;
	}

	void initMove() {
		nMove = 0;
	}

	void levelUp() {
		nLevel = nLevel + 1;
	}

	void initAll() {
		nScore = 0;
		nMove = 0;
		nLevel = 1;
	}

	int getScore() {
		return nScore;
	}

	int getMove() {
		return nMove;
	}

	int getLevel() {
		return nLevel;
	}

	Model() {
		nScore = 0;
		nMove = 0;
		nLevel = 1;
	}

}