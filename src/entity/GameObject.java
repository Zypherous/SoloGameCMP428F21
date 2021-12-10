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
	protected GameObject parent;
	protected Position renderOffset;
	
	public GameObject(Camera camera) {
		this.camera = camera;
		position = new Position(0, 0);
		renderOffset = new Position(0,0);
		size = new Size (64, 64);
		this.thisID = ID;
		this.dead = false;
		this.rect = new Rect((int)this.getRenderPosition(camera).getX(),(int)this.getRenderPosition(camera).getY(),this.size.getWidth(),this.size.getHeight());
		ID++;
	}
	public  boolean collidesWith(GameObject other) {
		return getCollider().collidesWith(other.getCollider());
	}
	public abstract void update(State state);
	public abstract Image getSprite();
	public abstract CollisionBox getCollider();
	public abstract CollisionBox getCurrentCollider();
	public Position getPosition() {
		Position finalPosition = Position.copyOf(position);
		
		if(parent != null) {
			finalPosition.add(parent.getPosition());
		}
		return finalPosition;
	}

	public GameObject getParent() {
		return parent;
	}
	public void setParent(GameObject parent) {
		this.parent = parent;
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
	
	public Position getRenderPosition(Camera camera) {
		 return new Position(
				 getPosition().getX() - camera.getPosition().getX(),
				 getPosition().getY() - camera.getPosition().getY()
				 );
	}
}
