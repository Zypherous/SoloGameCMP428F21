package state.game.ui;

import core.Size;
import state.State;
import state.game.GameState;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

public class UISicknessStatistics extends HorizontalContainer {

    private UIText numberOfSick;
    private UIText numberOfHealthy;
    private UIText numberOfHealthPoints;

    public UISicknessStatistics(Size windowSize) {
        super(windowSize);
        this.numberOfSick = new UIText("");
        this.numberOfHealthy = new UIText("");
        this.numberOfHealthPoints = new UIText("");

        UIContainer sickContainer = new VerticalContainer(windowSize);
        sickContainer.setPadding(new Spacing(0));
        sickContainer.addUIComponent(new UIText("SICK"));
        sickContainer.addUIComponent(numberOfSick);
        sickContainer.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.START));
        
        UIContainer healthyContainer = new VerticalContainer(windowSize);
        healthyContainer.setPadding(new Spacing(0));
        healthyContainer.addUIComponent(new UIText("HEALTHY"));
        healthyContainer.addUIComponent(numberOfHealthy);
        healthyContainer.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.START));
        
        UIContainer health = new VerticalContainer(windowSize);
        health.setPadding(new Spacing(0));
        health.addUIComponent(new UIText("Health"));
        health.addUIComponent(numberOfHealthPoints);
        healthyContainer.setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        
        addUIComponent(healthyContainer);
        addUIComponent(sickContainer);
        addUIComponent(health);
    }

    @Override
    public void update(State state) {
        super.update(state);
        if(state instanceof GameState) {
            GameState gameState = (GameState) state;
            numberOfSick.setText(String.format("%d (%d)", gameState.getNumberOfSick(), gameState.getNumberOfIsolated()));
            numberOfHealthy.setText(String.valueOf(gameState.getNumberOfHealthy()));
            numberOfHealthPoints.setText(String.valueOf(gameState.getHealth()));
        }

    }
} 