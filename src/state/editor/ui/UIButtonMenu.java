package state.editor.ui;

import core.Size;
import io.MapIO;
import state.State;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

public class UIButtonMenu extends HorizontalContainer {
    public UIButtonMenu(Size windowSize) {
        super(windowSize);

        addUIComponent(new UIButton("MAIN MENU", state -> state.setNextState(new MenuState(state.getCamera().getWindowSize(), state.getInput(), state.getGameSettings()))));
        //Refactored to use a method reference
        addUIComponent(new UIButton("SAVE", State::saveGameMap));
        addUIComponent(new UIButton("LOAD", State::loadGameMap));
    }
}
