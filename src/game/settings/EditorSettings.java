package game.settings;

public class EditorSettings {
	private Setting<Boolean> autoTile;

	public EditorSettings() {
		this.autoTile = new Setting<>(false);
	}

	public Setting<Boolean> getAutotile() {
		return autoTile;
	}


	
}
