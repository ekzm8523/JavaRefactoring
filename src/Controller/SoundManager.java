package Controller;

import View.MainPanel;

public class SoundManager {

	
	private BackgroundMusic bgm;
	private BarkSound bark;
	
	private static SoundManager s_Instance;

	public BarkSound getBarkSound() {
		if (bark == null)
			bark = new BarkSound();
		return bark;
	}
	public BackgroundMusic getBGM() {
		if(bgm == null)
			bgm = new BackgroundMusic();
		return bgm;
	}
	public static SoundManager getInstance() {
		if (s_Instance == null)
			s_Instance = new SoundManager();
		return s_Instance;
	}
	public void changeBgmIcon() {
		if (MainPanel.btnBGMOnOff.getText() == "ON") {
			MainPanel.btnBGMOnOff.setIcon(MainPanel.bgmOff);
		} else {
			MainPanel.btnBGMOnOff.setIcon(MainPanel.bgmOn);
		}
	}
}