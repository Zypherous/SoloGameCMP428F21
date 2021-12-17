package input.mouse.action;




import java.awt.event.KeyEvent;

import core.Position;
import entity.EventObject;
import entity.Rect;
import state.State;
import ui.UIImage;

public class DrawRect extends MouseAction{

	private Position dragPosition;
	private Position endPosition;
	
	@Override
	public void onClick(State state) {
		if(state.getInput().isPressed(KeyEvent.VK_CONTROL)) {
		}
		
		if(!state.getInput().isMousePressed()) {
			dragPosition = null;
		}
	}

	@Override
	public void onDrag(State state) {
		   if(dragPosition == null && state.getInput().isPressed(KeyEvent.VK_CONTROL)) {
	            dragPosition = Position.copyOf(state.getInput().getMousePosition());
	        } else {
	            endPosition = Position.copyOf(state.getInput().getMousePosition());
	            endPosition.subtract(state.getInput().getMousePosition());
	        }		
	}

	@Override
	public void onRelease(State state) {
		if(state.getInput().isPressed(KeyEvent.VK_CONTROL)) {
			 Rect drawRect = new Rect(
					dragPosition.intX(),
					dragPosition.intY(), 
					dragPosition.intX() - endPosition.intX(),
					dragPosition.intY() - endPosition.intY()
					);
			 EventObject invisibleRect = new EventObject("invisible",drawRect, true, state.getSpriteLibrary());
			 state.getGameObjects().add(invisibleRect);
			
		}
		dragPosition = null;
	}

	@Override
	public void update(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UIImage getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}
