package game.ui;

import core.Size;
import entity.MovingEntity;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Sick;
import game.state.State;
import ui.*;
import ui.Alignment.Position;

public class UISicknessStatistics extends HorizontalContainer {

    private UIText numberOfSick;
    private UIText numberOfHealthy;

    public UISicknessStatistics(Size windowSize) {
        super(windowSize);
        this.numberOfSick = new UIText("");
        this.numberOfHealthy = new UIText("");

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
        
        addUIComponent(healthyContainer);
        addUIComponent(sickContainer);
    }

    @Override
    public void update(State state) {
        super.update(state);
        long sickCount = state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Sick.class))
                .count();

        long healthyCount = state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
                .count();

        numberOfSick.setText(String.valueOf(sickCount));
        numberOfHealthy.setText(String.valueOf(healthyCount));
    }
}
