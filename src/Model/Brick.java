package Model;
import javax.swing.ImageIcon;

// 벽돌 클래스 
public class Brick extends GameObject{

	public Brick(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = BRICK;
	}

	
}