package entity;
import java.awt.Image;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;
public abstract class GameObject {
	protected Position position;
	protected Size size;
	protected Rect rect;
	protected static int ID = 0;
	protected int thisID;
	protected Camera camera;
	protected boolean dead;
	
	public GameObject(Camera camera) {
		this.camera = camera;
		position = new Position(0, 0);
		size = new Size (64, 64);
		this.thisID = ID;
		this.dead = false;
		ID++;
	}
	public abstract void update(State state);
	public abstract Image getSprite();
	public abstract CollisionBox getCollider();
	public abstract boolean collidesWith(GameObject other);
	public abstract CollisionBox getCurrentCollider();
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
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public int getID() {
		return this.thisID;
	}
	
	
	
}
