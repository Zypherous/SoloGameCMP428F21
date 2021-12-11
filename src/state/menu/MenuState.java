package state.menu;

import core.Size;
import entity.Player;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {
    public MenuState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);

        uiContainers.add(new UIMainMenu(windowSize));
    }

    public void enterMenu(UIContainer container) {
        uiContainers.clear();
        uiContainers.add(container);
        audioPlayer.playMusic("MollysWorld.wav");
    }

	@Override
	public Player getPlayer() {
		return null;
	}
}
