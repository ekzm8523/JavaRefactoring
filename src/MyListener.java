import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	            panel.move(keyEvent);
	            panel.lblMove.setText(Integer.toString(panel.barObject.getMove()));
	            panel.view(keyEvent);
	            if(panel.isGameClear()) {
	            	if (panel.barObject.getLevel() <= 8)
	            		panel.game.nextStage();
	            	else
	            		panel.game.gameClear();
	            }
	            if(panel.isGameOver())
	            	panel.game.gameOver();

	         }
	         
	      });
	}
	
}