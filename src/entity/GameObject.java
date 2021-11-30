package entity;
import java.awt.*;

import core.Position;
import core.Size;
public abstract class GameObject {
	protected Position position;
	protected Size size;
	
	public GameObject() {
		position = new Position(50, 50);
		size = new Size (50, 50);
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public abstract void update();
	public abstract Image getSprite();
	
	
}
