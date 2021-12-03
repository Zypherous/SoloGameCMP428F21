package display;

import java.util.Optional;

import core.Position;
import core.Size;
import entity.GameObject;
import game.state.State;

public class Camera {

	private Position position;
	private Size windowSize;
	
	private Optional<GameObject> objectWithFocus;
	
	public  Camera (Size windowSize){
		this.position = new Position(0,0);
		this.windowSize = windowSize;
	}
	public void focudOn(GameObject object) {
		this.objectWithFocus = Optional.of(object);
	}
	public void update(State state) {
		if(objectWithFocus.isPresent()) {
			Position objectPosition = objectWithFocus.get().getPosition();
			
			this.position.setX((int)objectPosition.getX() - windowSize.getWidth()  / 2);
			this.position.setY((int)objectPosition.getY() - windowSize.getHeight() / 2);
		}
	}
	public Position getPosition() {
		return position;
	}
}
