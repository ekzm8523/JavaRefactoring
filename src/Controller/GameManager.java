package Controller;

import Model.BarObject;
import View.Game;

public class GameManager {
	private Game mGame;
	private BarObject barObject;
	public void setGame(Game _game) {
		mGame = _game;
	}

	public Game getGame() {
		return mGame;
	}

	private static GameManager s_Instance;

	public static GameManager getInstance() {
		if (s_Instance == null)
			s_Instance = new GameManager();
		return s_Instance;
	}

	public void setBarObject(BarObject _barObject) {
		barObject = _barObject;
	}

	public BarObject getBarObject() {
		return barObject;
	}
	
}