package Model;
import javax.swing.ImageIcon;

public class MapOutside extends GameObject{

	public MapOutside(int x, int y, ImageIcon imageIcon) {
		super(x, y, imageIcon);
		myObject = MAPOUTSIDE;
	}
}