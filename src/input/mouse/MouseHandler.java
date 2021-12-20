package input.mouse;

import input.Input;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

import java.util.Optional;

public class MouseHandler {

	// Different actions that can be attached to a mouse button
	// A mouse "consumer" that prevents multiple elements from listening to the mouse
	// at the same time be keeping track of what has focus of the mouse.
	
    private MouseAction primaryButtonAction;
    private MouseAction wheelMouseButtonAction;
    private MouseAction rightMouseButtonAction;
    private MouseConsumer activeConsumer;

    public void update(State state) {
        final Input input = state.getInput();

        handlePrimaryButton(state);
        handleRightButton(state);
        handleWheelButton(state);
        handleActiveConsumer(state, input);

        cleanUp(input);
    }

    
    private void handlePrimaryButton(State state) {
        if(primaryButtonAction != null) {
            setActiveConsumer(primaryButtonAction);
            primaryButtonAction.update(state);
        }
    }
    private void handleRightButton(State state) {
        if(rightMouseButtonAction != null) {
//            setActiveConsumer(rightMouseButtonAction);
            rightMouseButtonAction.update(state);
            if(state.getInput().isRightMouseClicked()) {
            	rightMouseButtonAction.onClick(state);
            }
            if(state.getInput().isRightMouseClicked()){
            	rightMouseButtonAction.onDrag(state);
            }
            if(state.getInput().isRightMouseReleased()){
            	rightMouseButtonAction.onRelease(state);
            }
        }
    }private void handleWheelButton(State state) {
        if(wheelMouseButtonAction != null) {
            wheelMouseButtonAction.update(state);
            if(state.getInput().isWheelMouseClicked()) {
            	wheelMouseButtonAction.onClick(state);
            }
            
            if(state.getInput().isWheelMousePressed()){
            	wheelMouseButtonAction.onDrag(state);
            }
            if(state.getInput().isWheelMouseReleased()){
            	wheelMouseButtonAction.onRelease(state);
            }
            
            
        }
    }

    private void cleanUp(Input input) {
        if(!input.isMousePressed()) {
            activeConsumer = null;
        }

        input.clearMouseClick();
    }

    private void handleActiveConsumer(State state, Input input) {
        if(activeConsumer != null) {
            if(input.isMouseClicked()) {
                activeConsumer.onClick(state);
            }
            if (input.isMousePressed()) {
                activeConsumer.onDrag(state);
            }
            
            if(input.isMouseReleased()) {
            	activeConsumer.onRelease(state);
            }
        }
    }

    public MouseConsumer getActiveConsumer() {
        return activeConsumer;
    }

    public void setActiveConsumer(MouseConsumer mouseConsumer) {
        if(activeConsumer == null) {
            activeConsumer = mouseConsumer;
        }
    }

    public void switchPrimaryButtonAction(MouseAction mouseAction) {
    	if(primaryButtonAction != null) {
    		primaryButtonAction.cleanUp();
    	}
    	this.primaryButtonAction = mouseAction;
    }

    public Optional<UIImage> getPrimaryButtonUI() {
        if(primaryButtonAction != null) {
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }

        return Optional.empty();
    }

	 public MouseAction getPrimaryButtonAction() {
        return primaryButtonAction;
    }


	public MouseAction getWheelMouseButtonAction() {
		return wheelMouseButtonAction;
	}


	public void setWheelMouseButtonAction(MouseAction wheelMouseButtonAction) {
		this.wheelMouseButtonAction = wheelMouseButtonAction;
	}


	public MouseAction getRightMouseButtonAction() {
		return rightMouseButtonAction;
	}


	public void setRightMouseButtonAction(MouseAction rightMouseButtonAction) {
		this.rightMouseButtonAction = rightMouseButtonAction;
	}
	
	 
}
