package game.settings;

public class GameSettings {

	private boolean debugMode;
	private double gameSpeedMultiplier;
	private final AudioSettings audioSettings;
	private final RenderSettings renderSettings;
	private final EditorSettings editorSettings;

	public GameSettings(boolean debugMode) {
		this.debugMode = debugMode;
		this.gameSpeedMultiplier = 1;
		this.audioSettings = new AudioSettings();
		this.renderSettings = new RenderSettings();
		this.editorSettings = new EditorSettings();
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

	public AudioSettings getAudioSettings() {
		return audioSettings;
	}

	public RenderSettings getRenderSettings() {
		return renderSettings;
	}

	public EditorSettings getEditorSettings() {
		return editorSettings;
	}
	
	

	
}
