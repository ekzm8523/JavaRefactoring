package Controller;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import View.Game;
import View.RankPanel;
import View.RankState;

public class Rank {

	private static Rank rank;
	private Connection con;

	private String url = "jdbc:mysql://localhost:3306/HungryDog?&serverTimezone=Asia/Seoul&useSSL=false";
	private String userid = "HungryDog";
	private String pwd = "HungryDog";

	public static Rank getInstance() {
		if (rank == null) {
			rank = new Rank();
		}
		return rank;
	}

	public Connection getCon() {
		return this.con;
	}

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

	public void setNewRank(int score, String name) {

		String query = "INSERT INTO Score_Board VALUES(";

		try {
			Statement stmt = con.createStatement();

			String input = query + "'" + name + "', " + score + ");";
			stmt.executeUpdate(input);

			Game game = GameManager.getInstance().getGame();
			game.setState(RankState.getInstance());
			RankPanel.getInstance().isRankIn();

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "존재하는 닉네임 입니다.");
		}
	}

}