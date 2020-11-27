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
	public void move(int direction) {
		switch(direction) {
			case 0: 
				boundPoint.y--;
				break;
			case 1:
				boundPoint.x++;
				break;
			case 2:
				boundPoint.y++;
				break;
			case 3:
				boundPoint.x--;
				break;
			default:
				System.out.println("인덱싱 오류 ");
				break;
		}
	
	}
}