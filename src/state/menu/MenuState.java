package state.menu;

import core.Size;
import entity.Player;
import game.settings.GameSettings;
import input.Input;
import io.MapIO;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {
    public MenuState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        
        // Load map 
        gameMap = MapIO.load(spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(false);

        uiContainers.add(new UIMainMenu(windowSize));
//        audioPlayer.playMusic("MollysWorld.wav");
    }

    public void enterMenu(UIContainer container) {
        uiContainers.clear();
        uiContainers.add(container);
        
    }

	@Override
	public Player getPlayer() {
		return null;
	}
}
