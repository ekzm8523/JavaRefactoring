
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class RankPanel extends JPanel {
	
	private static RankPanel rankPanel;
	
	static Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	private int[] lastScore = new int[2];
	private JButton btnGoMain, btnExit;
	private JLabel lblTitle, lblSubTitle, lblName[] = new JLabel[5], lblScore[] = new JLabel[5];
	
	int idx; // 랭킹 Label을 DB로 부터 받아오고 랭킹 재조회 시 기존 랭킹을 지우는 index
	private ImageIcon restart1, restart2, exit1, exit2; // 재시작, 종료 버튼에 이미지를 씌워주기 위한 이미지아이콘 각각 2개씩

	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/HungryDog?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "HungryDog";
	String pwd = "HungryDog";
	
	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, userid, pwd);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public static RankPanel getInstance(Game game) {
		if (rankPanel == null)
			rankPanel = new RankPanel(game);

		return rankPanel;
	}
		
	private RankPanel(Game game) {
		connectDB();
		printRanking();
		
		Color backcolor = new Color(246, 223, 170);
		setPreferredSize(new Dimension(600, 700));
		setBackground(backcolor);
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

		ImageIcon originIcon = new ImageIcon("images/main1.png");
		Image originImg = originIcon.getImage();
		Image changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/main2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		restart2 = new ImageIcon(changedImg);

		originIcon = new ImageIcon("images/exit1.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit1 = new ImageIcon(changedImg);
		originIcon = new ImageIcon("images/exit2.png");
		originImg = originIcon.getImage();
		changedImg = originImg.getScaledInstance(225, 100, Image.SCALE_SMOOTH);
		exit2 = new ImageIcon(changedImg);

		btnGoMain = new JButton("□ MAIN", restart1);
		btnGoMain.setBounds(50, 550, 235, 100);
		btnGoMain.setBorderPainted(false);
		btnGoMain.setContentAreaFilled(false);
		btnGoMain.setForeground(backcolor);
		btnGoMain.setRolloverIcon(restart2);
		btnGoMain.setPressedIcon(restart2);
		btnGoMain.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                game.mainButton();
	            }
		});
		add(btnGoMain);

		btnExit = new JButton("Exit", exit1);
		btnExit.setBounds(325, 550, 235, 100);
		btnExit.setBorderPainted(false);
		btnExit.setContentAreaFilled(false);
		btnExit.setForeground(backcolor);
		btnExit.setRolloverIcon(exit2);
		btnExit.setPressedIcon(exit2);
		btnExit.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	            }
		});
		add(btnExit);
	}
	
	public void printRanking() {
			
		// 랭킹을 5등까지 출력하는 메소드
			
		idx = 0;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			rs = stmt.executeQuery("SELECT * FROM Score_Board ORDER BY score DESC");
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		try {
			while (rs.next() && idx < 5) {
				String name = rs.getString(1);
				int score = rs.getInt(2);
				
				// NAME 랭킹 라벨
				lblName[idx] = new JLabel();
				lblName[idx].setBounds(50, 225 + 60 * (idx), 500, 60);
				lblName[idx].setFont(new Font("Verdana", Font.BOLD, 35));
				lblName[idx].setText(idx + 1 + ".  " + name + "\r\n");
				add(lblName[idx]);
				
				// SCORE 랭킹 라벨
				lblScore[idx] = new JLabel();
				lblScore[idx].setBounds(340, 225 + 60 * (idx), 500, 60);
				lblScore[idx].setFont(new Font("Verdana", Font.BOLD, 35));
				lblScore[idx].setText(score + "\r\n");
				add(lblScore[idx]);
				
				lastScore[0] = idx;
				lastScore[1] = score;
				
				idx += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}