package Model;
import java.awt.Font;

public class GameFont {
	private static Font mainPanelFont,playPanelFont,rankPanelSmallFont,rankPanelBigFont;
	public static Font getMainPanelFont() {
	      if (mainPanelFont == null)
	    	  mainPanelFont = new Font("a타임머신", Font.BOLD, 30);

	      return mainPanelFont;
	   }
	
	public static Font getPlayPanelFont() {
	      if (playPanelFont == null)
	    	  playPanelFont = new Font("Verdana", Font.BOLD, 20);

	      return playPanelFont;
	   }
	public static Font getRankSmallFont() {
	      if (rankPanelSmallFont == null)
	    	  rankPanelSmallFont = new Font("Verdana", Font.BOLD, 35);

	      return rankPanelSmallFont;
	   }
	public static Font getRankBIgFont() {
	      if (rankPanelBigFont == null)
	    	  rankPanelBigFont = new Font("Verdana", Font.BOLD, 50);

	      return rankPanelBigFont;
	   }
	   
}
