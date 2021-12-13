package state.editor.ui;

import core.Size;
import map.MapIO;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

public class UIButtonMenu extends HorizontalContainer {
    public UIButtonMenu(Size windowSize) {
        super(windowSize);

        addUIComponent(new UIButton("MAIN MENU", state -> state.setNextState(new MenuState(state.getCamera().getWindowSize(), state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("SAVE", state -> MapIO.save(state.getGameMap())));
        addUIComponent(new UIButton("LOAD", state -> state.loadGameMap()));
    }
}
