package state.menu.ui;

import java.awt.Color;

import core.Size;
import game.settings.GameSettings;
import state.State;
import state.game.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

public class UIOptionMenu extends VerticalContainer {
	
	private UISlider musicVolSlider;
	private UIText musicVolLabel;
	private UISlider soundVolSlider;
    private UIText soundVolLabel;
    private String previousState;
    
    public UIOptionMenu(Size windowSize, GameSettings gameSettings, String prevState) {
    	 super(windowSize);
         alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER);
         this.previousState = prevState;
         
         musicVolSlider = new UISlider(0, 1);
         musicVolSlider.setValue(gameSettings.getAudioSettings().getMusicVolume());
         musicVolLabel = new UIText("");

         soundVolSlider = new UISlider(0, 1);
         soundVolSlider.setValue(gameSettings.getAudioSettings().getSoundVolume());
         soundVolLabel = new UIText("");

         UIContainer labelContainer = new VerticalContainer(windowSize);
         labelContainer.setMargin(new Spacing(0));
         labelContainer.setBackgroundColor(Color.DARK_GRAY);
         labelContainer.addUIComponent(new UIText("OPTIONS"));

         UIContainer contentContainer = new VerticalContainer(windowSize);
         contentContainer.setMargin(new Spacing(0));
         contentContainer.setPadding(new Spacing(10));
         contentContainer.setBackgroundColor(Color.DARK_GRAY);
         contentContainer.addUIComponent(musicVolLabel);
         contentContainer.addUIComponent(musicVolSlider);
         contentContainer.addUIComponent(soundVolLabel);
         contentContainer.addUIComponent(soundVolSlider);
         if(previousState.equals("Menu")) {
        	 contentContainer.addUIComponent(new UIButton("BACK", (state) -> ((MenuState)state).enterMenu(new UIMainMenu(windowSize))));        	 
         }
         if(previousState.equals("Game")) {
        	 contentContainer.addUIComponent(new UIButton("BACK", (state) -> state.setNextState(new GameState(windowSize, state.getInput(), state.getGameSettings()))));
         }

         addUIComponent(labelContainer);
         addUIComponent(contentContainer);
    }
    
    @Override
    public void update(State state) {
    	super.update(state);
    	handleVolume(state);
    }

	private void handleVolume(State state) {
		state.getGameSettings().getAudioSettings().setMusicVolume((float)musicVolSlider.getValue());
		musicVolLabel.setText(String.format("MUSIC VOL: %d", Math.round(musicVolSlider.getValue() *100)));
		
		state.getGameSettings().getAudioSettings().setSoundVolume((float) soundVolSlider.getValue());
        soundVolLabel.setText(String.format("SOUND VOL: %d", Math.round(soundVolSlider.getValue() * 100)));
	}
}
