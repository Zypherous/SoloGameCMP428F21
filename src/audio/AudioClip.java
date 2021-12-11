package audio;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import game.settings.GameSettings;

public abstract class AudioClip {
	
	private final Clip clip;

	public AudioClip(Clip clip) {
		this.clip = clip;
		clip.start();
	}
	
	
	public void update(GameSettings gameSettings) {
		final FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		control.setValue(getSoundVolume(gameSettings));
	}
	
	protected abstract float getSoundVolume(GameSettings gameSettings);
}
