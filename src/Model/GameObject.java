package Model;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameObject {
	protected Point boundPoint = new Point();
	public JLabel label;
	protected int myObject;
	JPanel panel;
	public static final int GRASS = 0;
	public static final int BRICK = 1;
	public static final int BONE = 2;
	public static final int RICEBOWL = 3;
	public static final int PLAYER = 4;
	public static final int MAPOUTSIDE = 5;
	public void setImage(ImageIcon icon) {
		label = new JLabel(icon);
	}
	public GameObject() {
		boundPoint.x = -1;
		boundPoint.y = -1;
		label = null;
	}
	public GameObject(int x, int y, ImageIcon imageIcon) {
		boundPoint.x = x;
		boundPoint.y = y;
		label = new JLabel(imageIcon);
	}
	public void moveLeft() {
		boundPoint.x--;
	}
	public void moveRight() {
		boundPoint.x++;
	}
	public void moveUp() {
		boundPoint.y--;
	}
	public void moveDown() {
		boundPoint.y++;
	}
	public void setX(int x) {
		this.boundPoint.x = x;
	}
	public int getX() {
		return boundPoint.x;
	}
	public void setY(int y) {
		this.boundPoint.y = y;
	}
	public int getY() {
		return boundPoint.y;
	}
	public void setPoint(int x,int y) {
		this.boundPoint.x = x;
		this.boundPoint.y = y;
	}
	public Point getPoint() {
		return boundPoint;
	}
	
	public void addImageIcon() {
		label.setBounds(boundPoint.x * 50, boundPoint.y * 50+100, 50, 50);
		label.setVisible(true);
	}
	
	
}