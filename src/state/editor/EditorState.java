package state.editor;

import core.Size;
import entity.Player;
import game.settings.GameSettings;
import input.Input;
import map.GameMap;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(16, 16), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
    }

	@Override
	public Player getPlayer() {
		return null;
	}
}
