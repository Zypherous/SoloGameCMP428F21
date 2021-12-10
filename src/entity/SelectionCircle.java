package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.CollisionBox;
import core.Size;
import display.Camera;
import game.state.State;
import gfx.ImageUtils;
import java.awt.Graphics;

public class SelectionCircle extends GameObject {
	
	private Color color;
	private BufferedImage sprite;

	public SelectionCircle(Camera camera) {
		super(camera);
		color = Color.ORANGE;
		this.size = new Size(32, 16);
		initializeSprite();
	}

	private void initializeSprite() {
		sprite =  (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
		Graphics2D graphics = sprite.createGraphics();
		
		graphics.setColor(color);
		graphics.fillOval(0, 0, size.getWidth(), size.getHeight());
		graphics.dispose();
	}

	@Override
	public void update(State state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getSprite() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public CollisionBox getCollider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean collidesWith(GameObject other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CollisionBox getCurrentCollider() {
		// TODO Auto-generated method stub
		return CollisionBox.of(getPosition(), getSize());
	}
	
}
