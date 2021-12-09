package core;

import java.awt.*;

import entity.Rect;

public class CollisionBox {

    private Rect bounds;

    public CollisionBox(Rect bounds) {
        this.bounds = bounds;
    }

    public boolean collidesWith(CollisionBox other) {
        return bounds.overlaps(other.getBounds());
    }

    public Rect getBounds() {
        return bounds;
    }

	public static CollisionBox of(Position position, Size size) {
		return new CollisionBox(
				new Rect(
						(int)position.getX(),
						(int)position.getY(),
						size.getWidth(),
						size.getHeight()
						)
				);
	}
}
