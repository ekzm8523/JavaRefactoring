import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class GameOverPanel extends JPanel {
	
	private static GameOverPanel gameOverPanel;
	
	private JLabel lblGameOver;
	private JTextField txtInput;
	private JButton btnInput, btnGoMain;
	private ImageIcon goMain1, goMain2, input1, input2, gameOver;
	
	public static GameOverPanel getInstance(Game game) {
		if (gameOverPanel == null)
			gameOverPanel = new GameOverPanel(game);

		return gameOverPanel;
	}
	
	private GameOverPanel(Game game) {
		setPreferredSize(new Dimension(600, 700));
		setLayout(null);


		ImageIcon originIcon = new ImageIcon("images/GameOver.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
		gameOver = new ImageIcon(changedImg);
		lblGameOver = new JLabel(gameOver, lblGameOver.CENTER);
		lblGameOver.setOpaque(false);
		lblGameOver.setBounds(55, 100, 500, 350);
		
		add(lblGameOver); // 게임오버 라벨 아이콘 붙여서 패널에 붙이기
		
		txtInput = new JTextField(3); // 크기 3만큼
		txtInput.setBounds(75, 503, 150, 65);
		txtInput.setFont(new Font("Verdana", Font.BOLD, 30));
		txtInput.setAlignmentX(CENTER_ALIGNMENT);
		txtInput.setDocument(new JTextFieldLimit(3)); // 입력 가능 글자수 최대 3글자
		add(txtInput); // 텍스트필드 붙이기
		
		originIcon = new ImageIcon("images/input1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		input1 = new ImageIcon(changedImg); // 인풋 버튼 이미지
		originIcon = new ImageIcon("images/input2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 70, Image.SCALE_SMOOTH);
		input2 = new ImageIcon(changedImg); // 인풋 버튼 호버링 이미지
		
		btnInput = new JButton(input1);
		btnInput.setBounds(240, 500, 160, 75);
		btnInput.setBorderPainted(false);
		btnInput.setContentAreaFilled(false);
		btnInput.setRolloverIcon(input2);
		btnInput.setPressedIcon(input2);
		btnInput.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                // 랭킹 등록 메소드 위치
	            }
		});
		add(btnInput);
		
		originIcon = new ImageIcon("images/main1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
		goMain1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/main2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
		goMain2 = new ImageIcon(changedImg);
		
		btnGoMain = new JButton(goMain1);
		btnGoMain.setBounds(410, 500, 160, 75);
		btnGoMain.setBorderPainted(false);
		btnGoMain.setContentAreaFilled(false);
		btnGoMain.setRolloverIcon(goMain2);
		btnGoMain.setPressedIcon(goMain2);
		btnGoMain.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                game.mainButton();
	            }
		});
		add(btnGoMain);
	}

	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		public JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;
			if (getLength() + str.length() <= limit)
				super.insertString(offset, str, attr);
		}
	}

}