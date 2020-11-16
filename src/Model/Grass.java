package Model;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

// 잔디 클래스 
public class Grass extends GameObject{

	public Grass(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = GRASS;
		
	}

}