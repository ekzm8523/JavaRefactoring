public class GameManager {
	private Game mGame;
	private Model barObject;
	
	void setGame(Game _game) {
		mGame = _game; 
	}
	
	public Game getGame() {
		return mGame;
	}
	
	private static GameManager s_Instance;
	
	public static GameManager getInstance() {
		if(s_Instance == null) s_Instance = new GameManager();
		return s_Instance;
	}
	
	void setModel(Model _model) {
	      barObject = _model;
	   }
	   
	   public Model getModel() {
	      return barObject;
	   }
}