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

import Controller.BackgroundMusic;
import Controller.GameManager;
import Controller.SoundManager;
import Model.Button;
import Model.GameIcon;
import Model.AllLabel;

public class MainPanel extends JPanel {
	private static MainPanel mainPanel;
	private JButton btnStart, btnRank;
	public static JButton btnBGMOnOff;
	private Button buttonStart,buttonRank;
	private JLabel lblTitle, lblDogDown, lblDogLeft, lblDogRight, lblDogUp;
	private AllLabel labelDogDown,labelDogLeft,labelDogRight,labelDogUp;
	private ImageIcon imgTitle;
	private ImageIcon imgBeforeHoveringStart, imgAfterHoveringStart, imgBeforeHoveringRank, imgAfterHoveringRank,
			imgDogDown, imgDogLeft, imgDogRight, imgDogUp;
	private ImageIcon[] arrowImageArray = new ImageIcon[4];
	public static ImageIcon bgmOn, bgmOff;
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

		bgmOn = new GameIcon("bgmOn.png").getIcon(60, 50);
		bgmOff = new GameIcon("bgmOff.png").getIcon(60, 50);
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
					SoundManager.getInstance().changeBgmIcon();
					button.setText("OFF");
					SoundManager.getInstance().getBGM().stopMusic();
				} else {
					SoundManager.getInstance().changeBgmIcon();
					button.setText("ON");
					SoundManager.getInstance().getBGM().restartMusic();
				}
			}
		});
		add(btnBGMOnOff);

		imgBeforeHoveringStart = new GameIcon("start1.png").getIcon(300, 150);
		imgAfterHoveringStart = new GameIcon("start2.png").getIcon(300, 150);

		imgBeforeHoveringRank = new GameIcon("ranking1.png").getIcon(150, 150);
		imgAfterHoveringRank = new GameIcon("ranking2.png").getIcon(150, 150);

		imgDogDown = new GameIcon("cute_front.png").getIcon(47, 45);
		imgDogLeft = new GameIcon("cute_left.png").getIcon(45, 43);
		imgDogRight = new GameIcon("cute_right.png").getIcon(45, 45);
		imgDogUp = new GameIcon("cute_back.png").getIcon(45, 45);
	
		arrowImageArray[0]=imgDogDown;
		arrowImageArray[1]=imgDogLeft;
		arrowImageArray[2]=imgDogRight;
		arrowImageArray[3]=imgDogUp;
		
		imgTitle = new GameIcon("Title.png").getIcon(500, 200);

		lblTitle = new JLabel(imgTitle);
		lblTitle.setBounds(50, 75, 500, 200);
		lblTitle.setVisible(true);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		labelDogDown = new AllLabel("↓", arrowImageArray[0]);
		labelDogDown.setMainLabelWithPosition(90, 350, 100, 50);
		lblDogDown=labelDogDown.getLabel();
		add(lblDogDown);

		labelDogLeft= new AllLabel(" ←", arrowImageArray[1]);
		labelDogLeft.setMainLabelWithPosition(205, 350, 100, 50);
		lblDogLeft=labelDogLeft.getLabel();
		add(lblDogLeft);

		
		labelDogRight = new AllLabel(" →", arrowImageArray[2]);
		labelDogRight.setMainLabelWithPosition(320, 350, 100, 50);
		lblDogRight=labelDogRight.getLabel();
		add(lblDogRight);

		labelDogUp = new AllLabel("↑", arrowImageArray[3]);
		labelDogUp.setMainLabelWithPosition(420, 350, 100, 50);
		lblDogUp=labelDogUp.getLabel();
		add(lblDogUp);

		buttonStart = new Button("GO!", imgBeforeHoveringStart, imgAfterHoveringStart);
		buttonStart.setButton(backColor, 50, 500, 310,150);
		btnStart=buttonStart.getButton();
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.playingState();
			}
		});
		add(btnStart);
		buttonRank = new Button("RANKING", imgBeforeHoveringRank, imgAfterHoveringRank);
		buttonRank.setButton(backColor, 400, 500, 150,150);
		btnRank=buttonRank.getButton();
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.rankState();
			}
		});
		add(btnRank);
	}
}