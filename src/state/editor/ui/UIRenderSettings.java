package state.editor.ui;

import core.Size;
import game.settings.RenderSettings;
import map.GameMap;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UICheckbox;
import ui.clickable.UIMiniMap;

public class UIRenderSettings extends VerticalContainer {
    public UIRenderSettings(Size windowSize, RenderSettings renderSettings, GameMap gameMap) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
//        setCenterChildren(true);

        addUIComponent(new UIMiniMap(gameMap));
//        addUIComponent(new UIText("Render settings"));
        addUIComponent(new UICheckbox("GRID", renderSettings.getShouldRenderGrid()));
        addUIComponent(new UICheckbox("Col. Box", renderSettings.getCollisionBox()));
    }
}
