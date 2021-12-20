package game.settings;

public class RenderSettings {

    private final Setting<Boolean> grid;
    private final Setting<Boolean> collisionBox;
    private final Setting<Boolean> tileWalkability;

    public RenderSettings() {
        grid = new Setting<>(false);
        collisionBox = new Setting<>(false);
        tileWalkability = new Setting<>(false);
    }

    public Setting<Boolean> getShouldRenderGrid() {
        return grid;
    }

	public Setting<Boolean> getGrid() {
		return grid;
	}

	public Setting<Boolean> getCollisionBox() {
		return collisionBox;
	}
	public Setting<Boolean> getTileWalkability() {
        return tileWalkability;
    }
}
