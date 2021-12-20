package input.mouse.action;

import core.Position;
import game.Game;
import game.settings.RenderSettings;
import map.Tile;
import state.State;
import ui.UIImage;

public class TileWalkability extends MouseAction {

    private RenderSettings renderSettings;
	public TileWalkability(RenderSettings renderSettings) {
        this.renderSettings = renderSettings;
    }

    @Override
    public void update(State state) {
        renderSettings.getTileWalkability().setValue(true);
    }

    @Override
    public UIImage getSprite() {
        return null;
    }

    @Override
    public void cleanUp() {
        renderSettings.getTileWalkability().setValue(false);
    }

    @Override
    public void onClick(State state) {
        Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
        mousePosition.add(state.getCamera().getPosition());

        int gridX = mousePosition.intX() / Game.SPRITE_SIZE;
        int gridY = mousePosition.intY() / Game.SPRITE_SIZE;

        if(state.getGameMap().gridWithinBounds(gridX, gridY)) {
            Tile tile = state.getGameMap().getTile(gridX, gridY);
            tile.setWalkable(!tile.isWalkable());
        }
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public void onRelease(State state) {

    }
}
