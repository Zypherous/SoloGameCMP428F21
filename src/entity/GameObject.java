package entity;
import java.awt.*;

import core.Position;
import core.Size;
public abstract class GameObject {
	protected Position position;
	protected Size size;

	public GameObject() {
		position = new Position(0, 0);
		size = new Size (64, 64);
	}
	public abstract void update();
	public abstract Image getSprite();
	
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

	
	
	
}
