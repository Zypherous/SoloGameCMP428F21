package game.settings;

public class GameSettings {

	private boolean debugMode;
	private double gameSpeedMultiplier;
	private float musicVolume;
	private float soundVolume;

	public GameSettings(boolean debugMode) {
		this.debugMode = debugMode;
		this.gameSpeedMultiplier = 1;
		musicVolume = 0;
		soundVolume = 0;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}


	public boolean isDebugMode() {
		return debugMode;
	}

	public void toggleDebugMode() {
		debugMode = !debugMode;
	}
	
	public void increaseGameSpeed() {
		this.gameSpeedMultiplier += .25;
	}
	
	public void decreaseGameSpeed() {
		this.gameSpeedMultiplier -= .25;
		if(this.gameSpeedMultiplier <= 0) {
			this.gameSpeedMultiplier = .1;
		}
	}

	public double getGameSpeedMultiplier() {
		return gameSpeedMultiplier;
	}

	public float getMusicVolume() {
		return musicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		this.musicVolume = musicVolume;
	}

	public float getSoundVolume() {
		return soundVolume;
	}

	public void setSoundVolume(float soundVolume) {
		this.soundVolume = soundVolume;
	}
	
}
