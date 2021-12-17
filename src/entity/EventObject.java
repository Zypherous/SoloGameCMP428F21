package entity;

import java.awt.Image;

import core.CollisionBox;
import core.Size;
import gfx.SpriteLibrary;
import state.State;

public class EventObject extends GameObject{
	private Image sprite;
	private String name;
	private boolean walkable;
	
	public EventObject(String name,
			Rect box,
			boolean walkable,
			SpriteLibrary spriteLibrary) {
		this.name = name;
		this.collisionBoxSize = new Size(box.getW(), box.getH());
		this.size = collisionBoxSize;
		this.walkable = walkable;
		loadGraphics(spriteLibrary);
	}

	@Override
	public void update(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollisionBox getCollisionBox() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadGraphics(SpriteLibrary spriteLibrary) {
		sprite = spriteLibrary.getImage("invisible");
	}
}
