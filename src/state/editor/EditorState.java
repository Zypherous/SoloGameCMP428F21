package state.editor;


import core.Size;
import entity.Player;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.TilePlacer;
import map.GameMap;
import map.Tile;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;
import state.editor.ui.UITileMenu;
import ui.Alignment;
import ui.UITabContainer;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(20,15), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);
        mouseHandler.setPrimaryButtonAction(new TilePlacer(new Tile(spriteLibrary, "grass")));

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
        
        UITabContainer toolsContainer = new UITabContainer(windowSize);
        toolsContainer.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));
        toolsContainer.addTab("TILES", new UITileMenu(windowSize, spriteLibrary, gameSettings.getEditorSettings()));
        toolsContainer.addTab("SCENERY", new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
        uiContainers.add(toolsContainer);
//        audioPlayer.playMusic("Menu.wav");
    }

	@Override
	public Player getPlayer() {
		return null;
	}
}
