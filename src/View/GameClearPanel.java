package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Controller.GameManager;
import Model.Button;
import Model.Icon;
import Model.Model;
import Rank.Rank;

public class GameClearPanel extends JPanel {

	private static GameClearPanel gameClearPanel;

	private JLabel lblGameClear;
	private JTextField txtInput;
	private JButton btnInput, btnGoMain;
	private ImageIcon goMain1, goMain2, input1, input2, gameClear;

	public static GameClearPanel getInstance() {
		if (gameClearPanel == null)
			gameClearPanel = new GameClearPanel();

		return gameClearPanel;
	}

	private GameClearPanel() {
		Game game = GameManager.getInstance().getGame();
		Color backColor = new Color(195, 224, 166);

		setPreferredSize(new Dimension(600, 700));
		setLayout(null);

		gameClear = new Icon("GameClear.png").getIcon(400, 200);
		lblGameClear = new JLabel(gameClear, lblGameClear.CENTER);
		lblGameClear.setOpaque(false);
		lblGameClear.setBounds(55, 100, 500, 350);

		add(lblGameClear);
		
		txtInput = new JTextField(3);
		txtInput.setBounds(75, 503, 150, 65);
		txtInput.setFont(new Font("Verdana", Font.BOLD, 30));
		txtInput.setAlignmentX(CENTER_ALIGNMENT);
		txtInput.setDocument(new JTextFieldLimit(3));
		add(txtInput);

		input1 = new Icon("input1.png").getIcon(150, 70);
		input2 = new Icon("input2.png").getIcon(150, 70);


		
		btnInput = new Button("input1", input1, input2).getButton(backColor, 240, 500, 160, 75);
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model barObject = GameManager.getInstance().getModel();
				String intxt = txtInput.getText();
				txtInput.setText("");

				if (intxt.length() != 3) { // 3글자가 아니면 빈 칸으로 만들고 경고창 띄우기
					txtInput.setText("");
					JOptionPane.showMessageDialog(null, "닉네임을 3글자로 입력해주세요.");
				}
				else {
					intxt = intxt.toUpperCase();
					Rank.getInstance().setNewRank(barObject.getScore(), intxt);
					barObject.initAll();
				}
			}
		});
		add(btnInput);

		goMain1 = new Icon("main1.png").getIcon(150, 75);
		goMain2 = new Icon("main2.png").getIcon(150, 75);
		
		btnGoMain = new Button("main1", goMain1, goMain2).getButton(backColor, 410, 500, 160, 75);
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