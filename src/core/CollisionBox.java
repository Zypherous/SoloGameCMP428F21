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
}
