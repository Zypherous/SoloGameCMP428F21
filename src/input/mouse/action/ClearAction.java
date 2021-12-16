package input.mouse.action;

import state.State;
import ui.UIImage;

public class ClearAction extends MouseAction{

	@Override
	public void onClick(State state) {
		state.getMouseHandler().switchPrimaryButtonAction(new SceneryTool());
	}

	@Override
	public void onDrag(State State) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void onRelease(State state) {
		// TODO Auto-generated method stub
		
	}

}
