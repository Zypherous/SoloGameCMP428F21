package audio;

import javax.sound.sampled.Clip;

import game.settings.GameSettings;

public class SoundClip extends AudioClip{

	public SoundClip(Clip clip) {
		super(clip);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected float getSoundVolume(GameSettings gameSettings) {
		return gameSettings.getSoundVolume();
	}

}
