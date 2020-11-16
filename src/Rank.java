import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Rank {
	
	private static Rank rank;
	private Connection con;

	private String Driver = "";
	private String url = "jdbc:mysql://localhost:3306/HungryDog?&serverTimezone=Asia/Seoul&useSSL=false";
	private String userid = "HungryDog";
	private String pwd = "HungryDog";
	private int idx;
	private JLabel lblName[] = new JLabel[5];
	private JLabel lblScore[] = new JLabel[5];
	private int[] lastScore = new int[2];
	
	public static Rank getInstance() {
		if (rank == null) {
			rank = new Rank();
		}
		return rank;
	}

	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("디비연결성공");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, userid, pwd);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void setNewRank(int score, String name) {

		String query = "INSERT INTO Score_Board VALUES(";

		try {
			Statement stmt = con.createStatement();

			String input = query + "'" + name + "', " + score + ");";
			stmt.executeUpdate(input);
			
			Game game = GameManager.getInstance().getGame();
			game.setState(RankState.getInstance());
			Rank.getInstance().isRankIn();
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "존재하는 닉네임 입니다.");
		}
	}

	public void printRanking(RankPanel rankPanel) {
		idx = 0;

		// 랭킹을 5등까지 출력하는 메소드

		Statement stmt = null;
		ResultSet rs = null;

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
				rankPanel.add(lblName[idx]);

				// SCORE 랭킹 라벨
				lblScore[idx] = new JLabel();
				lblScore[idx].setBounds(340, 225 + 60 * (idx), 500, 60);
				lblScore[idx].setFont(new Font("Verdana", Font.BOLD, 35));
				lblScore[idx].setText(score + "\r\n");
				rankPanel.add(lblScore[idx]);

				lastScore[0] = idx;
				lastScore[1] = score;
				idx += 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void hideBeforeRankLabel() {
		for (int i = 0; i < idx; i++) {
			lblName[i].setVisible(false);
			lblScore[i].setVisible(false);
		}
	}
	
	public void isRankIn() {
//		if (rank.getLastScore()[0] < 4 || rank.getLastScore()[1] < nScore)
		System.out.println(lastScore[0] + "   " + lastScore[1]);
		if (lastScore[0] < 4 || lastScore[1] < 1600)  // 이 1600점이 nScore 가 되어야 한다.
			JOptionPane.showMessageDialog(null, "랭킹에 진입했습니다.");
		else
			JOptionPane.showMessageDialog(null, "랭킹에 진입하지 못했습니다.");
	}
}
