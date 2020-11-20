package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.PlayPanel;

public class MyListener {

	private static MyListener s_instance;
	
	public static MyListener getInstance() {
		if(s_instance == null)
			s_instance = new MyListener();
		return s_instance;
	}
	public void addPlayPanelKeyListner(PlayPanel panel) {
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyEvent = e.getKeyCode();
				panel.move(keyEvent);
				panel.getLblMove().setText(Integer.toString(panel.getBarObject().getMoveCount()));
				panel.view(keyEvent);
				if (panel.isGameClear()) {
					if (panel.getBarObject().getLevel() <= 8)
						panel.getGame().nextStage();
					else
						panel.getGame().gameClear();
				}
				if (panel.isGameOver())
					panel.getGame().gameOver();

			}

		});
	}

}