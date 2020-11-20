package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Controller.GameManager;
import Controller.SoundManager;
import Model.BackgroundMusic;
import Model.Button;
import Model.MyIcon;
import Model.Label;

public class MainPanel extends JPanel {
	private static MainPanel mainPanel;
	private JButton btnStart, btnRank, btnBGMOnOff;
	private JLabel lblTitle, lblDogDown, lblDogLeft, lblDogRight, lblDogUp;
	private ImageIcon imgTitle;
	private ImageIcon imgBeforeHoveringStart, imgAfterHoveringStart, imgBeforeHoveringRank, imgAfterHoveringRank,
			imgDogDown, imgDogLeft, imgDogRight, imgDogUp, bgmOn, bgmOff;

	public static MainPanel getInstance() {
		Game game = GameManager.getInstance().getGame();
		if (mainPanel == null)
			mainPanel = new MainPanel();

		return mainPanel;
	}

	private MainPanel() {
		Game game = GameManager.getInstance().getGame();

		setPreferredSize(new Dimension(600, 700));
		Color backColor = new Color(195, 224, 166);
		setBackground(backColor);
		setLayout(null);

		bgmOn = new MyIcon("bgmOn.png").getIcon(60, 50);
		bgmOff = new MyIcon("bgmOff.png").getIcon(60, 50);
		btnBGMOnOff = new JButton("ON", bgmOn);
		btnBGMOnOff.setSelectedIcon(bgmOff);
		btnBGMOnOff.setOpaque(false);
		btnBGMOnOff.setFocusPainted(false);
		btnBGMOnOff.setBorderPainted(false);
		btnBGMOnOff.setBackground(backColor);
		btnBGMOnOff.setForeground(backColor);
		btnBGMOnOff.setBounds(500, 20, 75, 50);
		btnBGMOnOff.setContentAreaFilled(false);
		btnBGMOnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if (button.getText().equals("ON")) {
					changeBgmIcon();
					button.setText("OFF");
					SoundManager.getInstance().getBGM().stopMusic();
				} else {
					changeBgmIcon();
					button.setText("ON");
					SoundManager.getInstance().getBGM().restartMusic();
				}
			}
		});
		add(btnBGMOnOff);

		imgBeforeHoveringStart = new MyIcon("start1.png").getIcon(300, 150);
		imgAfterHoveringStart = new MyIcon("start2.png").getIcon(300, 150);

		imgBeforeHoveringRank = new MyIcon("ranking1.png").getIcon(150, 150);
		imgAfterHoveringRank = new MyIcon("ranking2.png").getIcon(150, 150);

		imgDogDown = new MyIcon("cute_front.png").getIcon(47, 45);
		imgDogLeft = new MyIcon("cute_left.png").getIcon(45, 43);
		imgDogRight = new MyIcon("cute_right.png").getIcon(45, 45);
		imgDogUp = new MyIcon("cute_back.png").getIcon(45, 45);

		imgTitle = new MyIcon("Title.png").getIcon(500, 200);

		lblTitle = new JLabel(imgTitle);
		lblTitle.setBounds(50, 75, 500, 200);
		lblTitle.setVisible(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		lblDogDown = new Label("↓", imgDogDown).setMainLabelWithPosition(90, 350, 100, 50);
		add(lblDogDown);

		lblDogLeft = new Label(" ←", imgDogLeft).setMainLabelWithPosition(205, 350, 100, 50);
		add(lblDogLeft);

		lblDogRight = new Label(" →", imgDogRight).setMainLabelWithPosition(320, 350, 100, 50);
		add(lblDogRight);

		lblDogUp = new Label("↑", imgDogUp).setMainLabelWithPosition(420, 350, 100, 50);
		add(lblDogUp);

		btnStart = new Button("GO!", imgBeforeHoveringStart, imgAfterHoveringStart).setButton(backColor, 50, 500, 310,
				150);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.playingState();
			}
		});
		add(btnStart);

		btnRank = new Button("RANKING", imgBeforeHoveringRank, imgAfterHoveringRank).setButton(backColor, 400, 500, 150,
				150);
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.rankState();
			}
		});
		add(btnRank);
	}

	public void changeBgmIcon() {
		if (btnBGMOnOff.getText() == "ON") {
			btnBGMOnOff.setIcon(bgmOff);
		} else {
			btnBGMOnOff.setIcon(bgmOn);
		}
	}
}