import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class MyListener {
	
	public MyListener() {
		
	}
	
	public void addPlayPanelKeyListner(PlayPanel panel) {
		panel.addKeyListener(new KeyListener() {
	         @Override
	         public void keyTyped(KeyEvent e) {
	            // TODO Auto-generated method stub
	         }
	         @Override
	         public void keyPressed(KeyEvent e) {
	            // TODO Auto-generated method stub

	         }
	         @Override
	         public void keyReleased(KeyEvent e) {
	            // TODO Auto-generated method stub
	            int keyEvent = e.getKeyCode();

	            System.out.println(keyEvent);
	            System.out.println("Good");
	            panel.move(keyEvent);
	            PlayMusic.getInstance().moveMusic();
	            panel.view(keyEvent);
	            if(panel.isGameClear())
	               panel.game.nextStage();

	         }
	      });
	}
	
}
