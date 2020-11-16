import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	public static GameOverPanel getInstance() {
		if (gameOverPanel == null)
			gameOverPanel = new GameOverPanel();

		return gameOverPanel;
	}

	private GameOverPanel() {
		Game game = GameManager.getInstance().getGame();
		
		setPreferredSize(new Dimension(600, 700));
		setLayout(null);

		gameOver = new Icon("GameOver.png").getIcon(400, 200);
		lblGameOver = new JLabel(gameOver, lblGameOver.CENTER);
		lblGameOver.setOpaque(false);
		lblGameOver.setBounds(55, 100, 500, 350);
		add(lblGameOver);

		txtInput = new JTextField(3);
		txtInput.setBounds(75, 503, 150, 65);
		txtInput.setFont(new Font("Verdana", Font.BOLD, 30));
		txtInput.setAlignmentX(CENTER_ALIGNMENT);
		txtInput.setDocument(new JTextFieldLimit(3));
		add(txtInput);

		input1 = new Icon("input1.png").getIcon(150, 70);
		input2 = new Icon("input2.png").getIcon(150, 70);

		btnInput = new JButton(input1);
		btnInput.setBounds(240, 500, 160, 75);
		btnInput.setBorderPainted(false);
		btnInput.setContentAreaFilled(false);
		btnInput.setRolloverIcon(input2);
		btnInput.setPressedIcon(input2);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String intxt = txtInput.getText();

				if (intxt.length() != 3) { // 3글자가 아니면 빈 칸으로 만들고 경고창 띄우기
					txtInput.setText("");
					JOptionPane.showMessageDialog(null, "닉네임을 3글자로 입력해주세요.");
				}
				else {
					// 점수는 임시로 1600점
					intxt = intxt.toUpperCase();
					Rank.getInstance().setNewRank(1800, intxt);
				}
			}
		});
		add(btnInput);

		goMain1 = new Icon("main1.png").getIcon(150, 75);
		goMain2 = new Icon("main2.png").getIcon(150, 75);

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