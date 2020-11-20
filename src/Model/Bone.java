package Model;
import javax.swing.ImageIcon;

// 뼈 클래스 
public class Bone extends GameObject{

	public Bone(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = BONE;
	}
}