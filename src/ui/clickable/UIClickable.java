package ui.clickable;

import java.awt.Rectangle;

import core.Position;
import entity.Rect;
import state.State;
import ui.UIComponent;

public abstract class UIClickable extends UIComponent {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void update(State state) {
        Position mousePosition = state.getInput().getMousePosition();

        hasFocus = getBounds().contains(mousePosition.intX(), mousePosition.intY());
        isPressed = hasFocus && state.getInput().isMousePressed();

        if(hasFocus && state.getInput().isMouseClicked()) {
            onClick();
        }
    }

    protected abstract void onClick();

    private Rect getBounds() {
        return new Rect(
                (int)absolutePosition.getX(),
                (int)absolutePosition.getY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
