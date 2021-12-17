package input.mouse.action;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import core.Position;
import entity.Scenery;
import entity.SelectionCircle;
import state.State;
import ui.UIImage;

public class SceneryTool  extends MouseAction{

	private Position dragPosition;
	
	// Hashing collection that does not care about insertion order, only hash code. Fixes issue with List since List allows duplicates, can be solved in another way
	// but for time this is easiest solution.
	private Set<Scenery> selectedScenery;
	
	
	
	public SceneryTool() {
		selectedScenery = new HashSet<>();
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
			if(!state.getInput().isCurrentlyPressed(KeyEvent.VK_SHIFT)) {
				cleanUp();
			}
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
		scenery.attach(new SelectionCircle(4));
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
		
	
	}

	@Override
	public UIImage getSprite() {
		return null;
	}

	@Override
	public void cleanUp() {
		List.copyOf(selectedScenery).forEach(this::deselect);
		
	}

	@Override
	public void onRelease(State state) {
		dragPosition = null;
	}
	
	

}
