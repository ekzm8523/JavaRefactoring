package Model;
import javax.swing.ImageIcon;

// 강아지 위치 
public class Player extends GameObject{

	public Player() {
		
	}
	
	public Player(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = PLAYER;
	}
}