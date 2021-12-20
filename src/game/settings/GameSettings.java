package game.settings;

public class GameSettings {

	// Holds the seetings for the game so different things can happen based on selected settings
	// debug mode lets game know if debug renderer should be working and it does this by checking
	// if a particular key is pressed in the game controller to toggle it on and off, as well as
	// a way to increase and decrease the speed modifier of the game 
	private boolean debugMode;
	private double gameSpeedMultiplier;
	private final AudioSettings audioSettings;
	private final RenderSettings renderSettings;
	private final EditorSettings editorSettings;
	
	// Takes in the debug boolean and intializes + instantiates the other settings
	public GameSettings(boolean debugMode) {
		this.debugMode = debugMode;
		this.gameSpeedMultiplier = 1;
		this.audioSettings = new AudioSettings();
		this.renderSettings = new RenderSettings();
		this.editorSettings = new EditorSettings();
	}

	//
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
