package game.settings;

public class GameSettings {

	private boolean debugMode;
	private double gameSpeedMultiplier;

	public GameSettings(boolean debugMode) {
		this.debugMode = debugMode;
		this.gameSpeedMultiplier = 1;
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
}
