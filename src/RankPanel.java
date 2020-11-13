
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RankPanel extends JPanel {

	private static RankPanel rankPanel;
	private JButton btnGoMain, btnExit;
	private JLabel lblTitle, lblSubTitle;

	int idx; // 랭킹 Label을 DB로 부터 받아오고 랭킹 재조회 시 기존 랭킹을 지우는 index
	private ImageIcon restart1, restart2, exit1, exit2; // 재시작, 종료 버튼에 이미지를 씌워주기 위한 이미지아이콘 각각 2개씩

	public static RankPanel getInstance() {
		if (rankPanel == null)
			rankPanel = new RankPanel();

		return rankPanel;
	}

	private RankPanel() {
		Game game = GameManager.getInstance().getGame();

		Color backColor = new Color(246, 223, 170);
		setPreferredSize(new Dimension(600, 700));
		setBackground(backColor);
		setLayout(null);

		lblTitle = new JLabel("RANKING");
		lblTitle.setBounds(50, 50, 500, 90);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 50));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		lblSubTitle = new JLabel("     NAME          SCORE");
		lblSubTitle.setBounds(50, 165, 500, 60);
		lblSubTitle.setOpaque(true);
		lblSubTitle.setFont(new Font("Verdana", Font.BOLD, 35));
		lblSubTitle.setForeground(Color.red);
		add(lblSubTitle);

		restart1 = new Icon("main1.png").getIcon(225, 100);
		restart2 = new Icon("main2.png").getIcon(225, 100);

		exit1 = new Icon("exit1.png").getIcon(225, 100);
		exit2 = new Icon("exit2.png").getIcon(225, 100);

		btnGoMain = new Button("□ MAIN", restart1).getButton(backColor, 50, 550, 235, 100);
		btnGoMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainButton();
			}
		});
		add(btnGoMain);

		btnExit = new Button("Exit", exit1).getButton(backColor, 325, 550, 235, 100);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnExit);
	}
}