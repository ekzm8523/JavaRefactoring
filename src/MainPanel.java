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

public class MainPanel extends JPanel {
	private static MainPanel mainPanel;
	private JButton btnStart, btnRank, btnBGM; // 게임스타트, 랭킹페이지, bgm 관리를 위한 버튼들
	private JLabel labTitle, labdog1, labdog2, labdog3, labdog4; // 제목과 중간에 조작법 설명을 위한 강아지들
	private ImageIcon title; // 제목에 씌우기 위한 이미지아이콘
	private ImageIcon start1, start2, ranking1, ranking2, dog1, dog2, dog3, dog4, bgmOn, bgmOff; // 각 버튼들과 라벨들에 씌워주기 위한
	// 이미지 아이콘

	public static MainPanel getInstance() {
		Game game = GameManager.getInstance().getGame();
		if (mainPanel == null)
			mainPanel = new MainPanel();

		return mainPanel;
	}

	private MainPanel() { // 시작하면 뜨는 게임의 시작 패널

		Game game = GameManager.getInstance().getGame();

		setPreferredSize(new Dimension(600, 700)); // 전체 창 크기 조절
		Color backColor = new Color(195, 224, 166); // 컬러값 생성
		setBackground(backColor); // 백그라운드 컬러
		setLayout(null); // setbounds 로 지정해주기 위해

		// ------------------- bgm 버튼을 위한 이미지 아이콘 불러오며, 크기 조정하여 셋팅해줍니다.
		// ------------------------
//
//		

		

		bgmOn = new Icon("bgmOn.png").getIcon(60, 50);
		bgmOff = new Icon("bgmOff.png").getIcon(60, 50);
		btnBGM = new JButton("ON", bgmOn);
		btnBGM.setSelectedIcon(bgmOff);

		btnBGM.setOpaque(false);
		btnBGM.setFocusPainted(false);
		btnBGM.setBorderPainted(false);

		btnBGM.setBackground(backColor);
		btnBGM.setForeground(backColor);
		btnBGM.setBounds(500, 20, 75, 50);
		btnBGM.setContentAreaFilled(false);

		btnBGM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if (button.getText().equals("ON")) {
					changeBgmIcon();
					button.setText("OFF");
					BackgroundMusic.getInstance().stopMusic();// 노래 재생, 멈추기
				} else {
					changeBgmIcon();
					button.setText("ON");
					BackgroundMusic.getInstance().start();// 노래 재생, 멈추기
				}
			}
		});
		add(btnBGM);

		// --------------------------- 게임스타트, 랭킹페이지로 가기 위한 버튼 이미지 아이콘 셋팅
		// ------------------
		start1 = new Icon("start1.png").getIcon(300, 150);
		start2 = new Icon("start2.png").getIcon(300, 150);

		ranking1 = new Icon("ranking1.png").getIcon(150, 150);
		ranking2 = new Icon("ranking2.png").getIcon(150, 150);

		// ------------------------ 중간에 조작법을 위한 강아지들 이미지 셋팅 -------------------
		dog1 = new Icon("cute_front.png").getIcon(47, 45);
		dog2 = new Icon("cute_left.png").getIcon(45, 43);
		dog3 = new Icon("cute_right.png").getIcon(45, 45);
		dog4 = new Icon("cute_back.png").getIcon(45, 45);

		// ---------------------- 제목 이미지 셋팅 ------------------------
		title = new Icon("Title.png").getIcon(500, 200);

		labTitle = new JLabel(title); // 제목 라벨 생성
		labTitle.setBounds(50, 75, 500, 200);
		labTitle.setVisible(true);
		labTitle.setHorizontalAlignment(SwingConstants.CENTER); // 중앙 정렬
		add(labTitle);

		// ------------ 중간에 상하좌우 조작법 설명하는 강아지들 라벨 생성 및 이미지 씌우기 -------------------
		labdog1 = new Label("↓", dog1).getMainLabel(90, 350, 100, 50);
		add(labdog1);

		labdog2 = new Label(" ←", dog2).getMainLabel(205, 350, 100, 50);
		add(labdog2);

		labdog3 = new Label(" →", dog3).getMainLabel(320, 350, 100, 50);
		add(labdog3);

		labdog4 = new Label("↑", dog4).getMainLabel(420, 350, 100, 50);
		add(labdog4);

		btnStart = new Button("GO!", start1).getButton(backColor, 50, 500, 310, 150);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.startButton();
			}
		});
		add(btnStart);

		btnRank = new Button("RANKING", ranking1).getButton(backColor, 400, 500, 150, 150);
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.rankButton();
			}
		});
		add(btnRank);

		btnRank = new JButton("RANKING", ranking1);
		btnRank.setBounds(400, 500, 150, 150);
		btnRank.setBorderPainted(false);
		btnRank.setContentAreaFilled(false);
		btnRank.setForeground(backColor);
		btnRank.setRolloverIcon(ranking2);
		btnRank.setPressedIcon(ranking2);
		btnRank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.rankButton();
			}
		});
		add(btnRank);
	}

	// ---------- bgm 이미지 아이콘 변경을 위한 메소드 ----------- //
	public void changeBgmIcon() {
		if (btnBGM.getText() == "ON") {
			btnBGM.setIcon(bgmOff);
		} else {
			btnBGM.setIcon(bgmOn);
		}
	}

	public void removeAllButtonsFocus() {
		btnStart.setFocusable(false);
		btnRank.setFocusable(false);
	}
}
