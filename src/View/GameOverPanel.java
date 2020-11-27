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
import Model.BarObject;
import Model.Button;
import Model.GameIcon;
import Model.Rank;

public class GameOverPanel extends JPanel {
	private static GameOverPanel imgGameOverPanel;

	private JLabel lblGameOver;
	private JTextField scoreInputTextField;
	private JButton btnScoreInput, btnMain;
	private Button buttonScoreInput,buttonMain;
	private ImageIcon imgBeforeHoveringMain, imgAfterHoveringMain, imgBeforeHoveringInput, imgAfterHoveringInput,
			imgGameOver;

	public static GameOverPanel getInstance() {
		if (imgGameOverPanel == null)
			imgGameOverPanel = new GameOverPanel();

		return imgGameOverPanel;
	}

	private GameOverPanel() {
		Game game = GameManager.getInstance().getGame();
		Color backColor = new Color(195, 224, 166);

		setPreferredSize(new Dimension(600, 700));
		setLayout(null);

		imgGameOver = new GameIcon("GameOver.png").getIcon(400, 200);
		lblGameOver = new JLabel(imgGameOver, lblGameOver.CENTER);
		lblGameOver.setOpaque(false);
		lblGameOver.setBounds(55, 100, 500, 350);
		add(lblGameOver);

		scoreInputTextField = new JTextField(3);
		scoreInputTextField.setBounds(75, 503, 150, 65);
		scoreInputTextField.setFont(new Font("Verdana", Font.BOLD, 30));
		scoreInputTextField.setAlignmentX(CENTER_ALIGNMENT);
		scoreInputTextField.setDocument(new JTextFieldLimit(3));
		add(scoreInputTextField);

		imgBeforeHoveringInput = new GameIcon("input1.png").getIcon(150, 70);
		imgAfterHoveringInput = new GameIcon("input2.png").getIcon(150, 70);
		
		buttonScoreInput = new Button("imgBeforeHoveringInput", imgBeforeHoveringInput, imgAfterHoveringInput);
		buttonScoreInput.setButton(backColor, 240, 500, 160, 75);
		btnScoreInput=buttonScoreInput.getButton();
		btnScoreInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BarObject barObject = GameManager.getInstance().getBarObject();
				String playingName = scoreInputTextField.getText();
				scoreInputTextField.setText("");

				if (playingName.length() != 3) {
					scoreInputTextField.setText("");
					JOptionPane.showMessageDialog(null, "닉네임을 3글자로 입력해주세요.");
				} else {
					playingName = playingName.toUpperCase();
					Rank.getInstance().setNewRank(barObject.getScore(), playingName);
					barObject.initBarObjects();
				}
			}
		});
		add(btnScoreInput);

		imgBeforeHoveringMain = new GameIcon("main1.png").getIcon(150, 75);
		imgAfterHoveringMain = new GameIcon("main2.png").getIcon(150, 75);
		
		buttonMain = new Button("main1", imgBeforeHoveringMain, imgAfterHoveringMain);
		buttonMain.setButton(backColor, 410, 500, 160,75);
		btnMain=buttonMain.getButton();
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.mainState();
			}
		});
		add(btnMain);
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