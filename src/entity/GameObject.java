package entity;
import java.awt.*;

import core.Position;
import core.Size;
public abstract class GameObject {
	protected Position position;
	protected Size size;
	protected Rect rect;
	
	public GameObject() {
		position = new Position(50, 50);
		size = new Size (50, 50);
		rect = new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, this.position);
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
	
	
	public Rect getRect() {
		return rect;
	}
	public void setRect(Rect rect) {
		this.rect = rect;
	}
	
	public void pushedBackby(int x, int y) {
			this.position.setX(x);
			this.position.setY(y);
			System.out.println("OVERLAPPING PLAYER");
	}

	
	
	
}
