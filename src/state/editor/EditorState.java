package state.editor;


import core.Size;
import entity.Player;
import game.settings.GameSettings;
import input.Input;
import input.mouse.action.CameraMovement;
import input.mouse.action.ClearAction;
import input.mouse.action.SceneryTool;
import map.GameMap;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;
import state.editor.ui.UISceneryMenu;
import state.editor.ui.UITileMenu;
import ui.Alignment;
import ui.UITabContainer;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(20,15), spriteLibrary);
        gameSettings.getRenderSettings().getShouldRenderGrid().setValue(true);
        setUpMouseHandler();
        
        setUpUI(windowSize, gameSettings);
//        audioPlayer.playMusic("Menu.wav");
    }

	private void setUpMouseHandler() {
		mouseHandler.switchPrimaryButtonAction(new SceneryTool());
        mouseHandler.setRightMouseButtonAction(new ClearAction());
        mouseHandler.setWheelMouseButtonAction(new CameraMovement());
	}

	private void setUpUI(Size windowSize, GameSettings gameSettings) {
		uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSettings.getRenderSettings(), gameMap));
        
        UITabContainer toolsContainer = new UITabContainer(windowSize);
        toolsContainer.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));
        toolsContainer.addTab("TILES", new UITileMenu(windowSize, spriteLibrary, gameSettings));
        toolsContainer.addTab("SCENERY", new UISceneryMenu(windowSize, spriteLibrary));
        uiContainers.add(toolsContainer);
	}

	@Override
	public Player getPlayer() {
		return null;
	}
}
