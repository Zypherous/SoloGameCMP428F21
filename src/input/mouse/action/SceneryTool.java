package input.mouse.action;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import core.Position;
import entity.Scenery;
import entity.SelectionCircle;
import state.State;
import ui.UIImage;

public class SceneryTool  extends MouseAction{

	private Position dragPosition;
	private List<Scenery> selectedScenery;
	
	
	
	public SceneryTool() {
		selectedScenery = new ArrayList<>();
	}

	@Override
	public void onClick(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDrag(State state) {
		// Just starting to drag
		if(dragPosition == null) {
			dragPosition = Position.copyOf(state.getInput().getMousePosition());
			
			cleanUp();
			
			Position mousePosition = Position.copyOf(state.getInput().getMousePosition());
			mousePosition.add(state.getCamera().getPosition());
			
			state.getGameObjectsOfClass(Scenery.class).stream()
			.filter(scenery -> scenery.getCollisionBox().getBounds().contains(mousePosition.intX(), mousePosition.intY()))
			.forEach(this::select);
		}
		else {
			dragPosition.subtract(state.getInput().getMousePosition());
			//inverted to get the correct position since we need to change the scenery by the amount the mouse was dragged
			selectedScenery.forEach(scenery -> scenery.changePositionBY(new Position(-dragPosition.intX(),-dragPosition.intY())));
			dragPosition = Position.copyOf(state.getInput().getMousePosition());
		}
	}
	
	public void select(Scenery scenery) {
		scenery.attach(new SelectionCircle(2));
		selectedScenery.add(scenery);
	}
	public void deselect(Scenery scenery) {
		// No reference to scenery circle yet so clear all attachments 
//		selectedScenery.remove(scenery);
		scenery.clearattachments();
		selectedScenery.remove(scenery);
	}

	@Override
	public void update(State state) {
		if(state.getInput().isPressed(KeyEvent.VK_DELETE)) {
			selectedScenery.forEach(state::despawn);
		}
		
		if(!state.getInput().isMousePressed()) {
			dragPosition = null;
		}
		
		if(state.getMouseHandler().getPrimaryButtonAction() != this) {
			cleanUp();
		}
	}

	@Override
	public UIImage getSprite() {
		return null;
	}

	@Override
	public void cleanUp() {
		List.copyOf(selectedScenery).forEach(this::deselect);
		
	}
	
	

}
