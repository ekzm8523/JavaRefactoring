import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 벽돌 클래스 
public class Brick extends GameObject{

	public Brick(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = BRICK;
	}

	
}