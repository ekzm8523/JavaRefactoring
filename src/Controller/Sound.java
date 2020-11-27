package Controller;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	protected File soundFile = null;
	protected AudioInputStream audioIn = null;
	protected Clip clip;

	public Sound(String fileName) {
		soundFile = new File(fileName + ".wav");
	}

	public void startMusic() { // 배경음악 받아와서 반복재생하기
		try {
			audioIn = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start(); // 음악 재생
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void stopMusic() { // 음악 멈추기
		clip.stop();
	}

	public void restartMusic() { // 음악 다시 재생하기
		clip.start();
	}
}