package entity;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.CollisionBox;
import core.Position;
import core.Size;
import gfx.ImageUtils;
import state.State;

public class SelectionCircle extends GameObject {

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = new Color(0,255,255);
        size = new Size(22, 16);
        renderOffset = new Position(size.getWidth() / 2, size.getHeight());
        collisionBoxOffset = renderOffset;
        renderOrder = 4;
        initializeSprite();
    }

    private void initializeSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = sprite.createGraphics();

        graphics.setColor(color);
        graphics.setStroke(new BasicStroke(2));
        graphics.drawOval(0, 0, size.getWidth(), size.getHeight());

        graphics.dispose();
    }

    @Override
    public void update(State state) {}

    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position position = getPosition();
        position.subtract(collisionBoxOffset);

        return CollisionBox.of(position, getSize());
    }
}
