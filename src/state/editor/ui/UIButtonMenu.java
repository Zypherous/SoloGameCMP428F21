package state.editor.ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import core.Size;
import state.State;
import state.menu.MenuState;
import ui.HorizontalContainer;
import ui.clickable.UIButton;

public class UIButtonMenu extends HorizontalContainer {
	
	private JFileChooser fileChooser;
    public UIButtonMenu(Size windowSize) {
        super(windowSize);

        // Instantiation of file chooser, setting the extension to be used and the directory it starts in (which is where this class is held)
         fileChooser = new JFileChooser();
         fileChooser.setAcceptAllFileFilterUsed(false);
         fileChooser.setFileFilter(new FileNameExtensionFilter("MollysWorld Map", "wrl"));
         fileChooser.setCurrentDirectory(new File(getClass().getResource("/maps").getFile()));
        addUIComponent(new UIButton("MAIN MENU", state -> state.setNextState(new MenuState(state.getCamera().getWindowSize(), state.getInput(), state.getGameSettings()))));
        //Refactored to use a method reference
        addUIComponent(new UIButton("SAVE", this::saveMap));
        addUIComponent(new UIButton("LOAD", this::loadMap));
    }
    
    private void saveMap(State state) {
    	final int fileChosen = fileChooser.showSaveDialog(new JFrame());
    	if(fileChosen == JFileChooser.APPROVE_OPTION) {
    		state.saveGameMap(fileChooser.getSelectedFile().toString());
    	}
    }
    private void loadMap(State state) {
    	final int fileChosen = fileChooser.showOpenDialog(new JFrame());
    	if(fileChosen == JFileChooser.APPROVE_OPTION) {
    		state.loadGameMap(fileChooser.getSelectedFile().getPath());
    	}
    }
}
