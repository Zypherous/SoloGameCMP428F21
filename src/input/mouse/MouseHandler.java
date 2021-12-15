package input.mouse;

import input.Input;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

import java.util.Optional;

public class MouseHandler {

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
            else if(state.getInput().isRightMouseClicked()){
            	rightMouseButtonAction.onDrag(state);
            }
        }
    }private void handleWheelButton(State state) {
        if(wheelMouseButtonAction != null) {
//            setActiveConsumer(wheelMouseButtonAction);
            wheelMouseButtonAction.update(state);
            if(state.getInput().isWheelMouseClicked()) {
            	wheelMouseButtonAction.onClick(state);
            }
            else if(state.getInput().isWheelMouseClicked()){
            	wheelMouseButtonAction.onDrag(state);
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
            } else if (input.isMousePressed()) {
                activeConsumer.onDrag(state);
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

    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
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
