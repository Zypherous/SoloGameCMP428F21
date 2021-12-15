package entity;
import java.awt.Image;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import state.State;
public abstract class GameObject {
	protected Position position;
	protected Position renderOffset;
	protected Position collisionBoxOffset;
	protected Size size;
//	protected Rect rect;
	protected static int ID = 0;
	protected int thisID;
	protected Camera camera;
	protected boolean dead;
	protected GameObject parent;
	protected int renderOrder;
    protected Size collisionBoxSize;

	
	public GameObject() {
		position = new Position(0, 0);
		size = new Size (64, 64);
		renderOffset = new Position(0,0);
		collisionBoxOffset = new Position(0,0);
		
        this.collisionBoxSize = new Size(size.getWidth(), size.getHeight());

		this.thisID = ID;
		this.dead = false;
//		this.rect = new Rect((int)this.getRenderPosition(camera).getX(),(int)this.getRenderPosition(camera).getY(),this.size.getWidth(),this.size.getHeight());
		ID++;
		renderOrder = 5;
	}
	
	
	public abstract void update(State state);
	public abstract Image getSprite();
	public abstract CollisionBox getCollisionBox();
//	public abstract CollisionBox getCurrentCollider();
	
	public  boolean collidesWith(GameObject other) {
		return getCollisionBox().collidesWith(other.getCollisionBox());
		
		
	}
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
	public void parent(GameObject parent) {
		this.position = new Position(0,0);
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
	

	public int getID() {
		return this.thisID;
	}
	
	public Position getRenderPosition(Camera camera) {
		 return new Position(
				 getPosition().getX() - camera.getPosition().getX() - renderOffset.getX(),
				 getPosition().getY() - camera.getPosition().getY()- renderOffset.getY()
				 );
	}
	public int getRenderOrder() {
		return renderOrder;
	}
	
	public void clearParent() {
		parent = null;
	}


	public Position getRenderOffset() {
		return renderOffset;
	}


	public void setRenderOrder(int renderOrder) {
		this.renderOrder = renderOrder;
	}


	public Position getCollisionBoxOffset() {
		return collisionBoxOffset;
	}

}
