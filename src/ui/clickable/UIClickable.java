package ui.clickable;

import core.Position;
import entity.Rect;
import input.mouse.MouseConsumer;
import state.State;
import ui.UIComponent;

public abstract class UIClickable extends UIComponent implements MouseConsumer{

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();

        boolean previousFocus = hasFocus;
        
        hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
        isPressed = hasFocus && state.getInput().isMousePressed();
        
        if(!previousFocus && hasFocus) {
        	onFocus(state);
        }
        
        if(hasFocus) {
        	state.getMouseHandler().setActiveConsumer(this);
        }
        
    }

    protected abstract void onFocus(State state);
    private Rect getBounds() {
        return new Rect(
                (int)absolutePosition.getX(),
                (int)absolutePosition.getY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
