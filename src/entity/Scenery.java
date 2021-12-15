package entity;

import java.awt.Image;

import core.CollisionBox;
import core.Position;
import core.Size;
import gfx.SpriteLibrary;
import io.Persistable;
import state.State;

public class Scenery extends GameObject implements Persistable{

	private Image sprite;
	private String name;
	private boolean walkable;
	
	
	public Scenery() {};
	
	public Scenery(String name,
				Size size,
				Position renderOffset,
				Size collisionBoxSize,
				Position collisionBoxOffset,
				boolean walkable,
				SpriteLibrary spriteLibrary) {
	
		this.name = name;
		this.size = size;
		this.renderOffset = renderOffset;
		this.collisionBoxSize = collisionBoxSize;
		this.collisionBoxOffset = collisionBoxOffset;
		this.walkable = walkable;
		
		loadGraphics(spriteLibrary);
	}

	public static Scenery copyOf(Scenery scenery) {
		Scenery copy = new Scenery();
		copy.name = scenery.name;
		copy.position = Position.copyOf(scenery.position);
		copy.size = Size.copyOf(scenery.size);
		copy.renderOffset = Position.copyOf(scenery.renderOffset);
		copy.collisionBoxOffset = Position.copyOf(scenery.collisionBoxOffset);
		copy.collisionBoxSize = Size.copyOf(scenery.collisionBoxSize);
		copy.sprite = scenery.sprite;
		copy.walkable = scenery.walkable;
		return copy;
	}
	public void loadGraphics(SpriteLibrary spriteLibrary) {
		sprite = spriteLibrary.getImage(name);
	}

	@Override
	public void update(State state) {}

	@Override
	public Image getSprite() {
		return sprite;
	}

	@Override
	public CollisionBox getCollisionBox() {
		Position position = Position.copyOf(getPosition());
		position.subtract(collisionBoxOffset);
        return new CollisionBox(
            new Rect(
                (int)position.getX() ,
                (int)position.getY() ,
                collisionBoxSize.getWidth(),
                collisionBoxSize.getHeight()
	            )
	        );
	    }
	@Override
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getClass().getSimpleName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(name);
        stringBuilder.append(DELIMITER);
        stringBuilder.append(position.serialize());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(size.serialize());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(renderOffset.serialize());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(collisionBoxOffset.serialize());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(collisionBoxSize.serialize());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(walkable);
        stringBuilder.append(DELIMITER);

        return stringBuilder.toString();
    }

    @Override
    public void applySerializedData(String serializedData) {
        String[] tokens = serializedData.split(DELIMITER);
        name = tokens[1];
        position.applySerializedData(tokens[2]);
        size.applySerializedData(tokens[3]);
        renderOffset.applySerializedData(tokens[4]);
        collisionBoxOffset.applySerializedData(tokens[5]);
        collisionBoxSize.applySerializedData(tokens[6]);
        walkable = Boolean.parseBoolean(tokens[7]);
    }

    public boolean isWalkable() {
        return walkable;
    }
	
}
