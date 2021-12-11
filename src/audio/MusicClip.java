package audio;

import javax.sound.sampled.Clip;

import game.settings.GameSettings;

public class MusicClip extends AudioClip{

	public MusicClip(Clip clip) {
		super(clip);
	}

	@Override
	protected float getSoundVolume(GameSettings gameSettings) {
		// TODO Auto-generated method stub
		return gameSettings.getMusicVolume();
	}

}
