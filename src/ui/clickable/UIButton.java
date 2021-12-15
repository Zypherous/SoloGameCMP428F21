package ui.clickable;

import core.Size;
import state.State;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends UIClickable {

    protected UIContainer container;
    protected UIText label;

    protected ClickAction clickAction;
    
    protected Color backgroundColor;

    public UIButton(String label, ClickAction clickAction) {
        this.label = new UIText(label);
        this.clickAction = clickAction;
        this.backgroundColor = Color.GRAY;

        container = new VerticalContainer(new Size(0, 0));
        container.setCenterChildren(true);
        container.addUIComponent(this.label);
        container.setFixedSize(new Size(150, 30));
    }

    @Override
    public void update(State state) {
        super.update(state);
        container.update(state);
        size = container.getSize();

        Color color = backgroundColor;

        if(hasFocus) {
            color = Color.LIGHT_GRAY;
        }

        if(isPressed) {
            color = Color.DARK_GRAY;
        }

        container.setBackgroundColor(color);
    }

    @Override
    protected void onFocus(State state) {
        state.getAudioPlayer().playSound("button.wav");
    }

    @Override
    public void onDrag(State state) {

    }

    @Override
    public void onClick(State state) {
    	if(hasFocus) {
    		clickAction.execute(state);
    	}
    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }
}
