package Model;
import javax.sound.sampled.Clip;

public class BackgroundMusic extends Sound{
	

	public BackgroundMusic() {
		super("Music/playMusic");
	}

	public void loopClip() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}
