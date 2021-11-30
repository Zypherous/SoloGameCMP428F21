package entity;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.Controller;
import core.Position;

import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends GameObject{

	private Controller controller;
	
	public Player(Controller controller) {
		super();
		this.controller = controller;
	}
	@Override
	public void update() {
		int deltaX = 0;
		int deltaY = 0;
		
		if(controller.isRequestingUp()) {
			deltaY -= 10;
		}
		if(controller.isRequestingDown()) {
			deltaY += 10;
		}if(controller.isRequestingRight()) {
			deltaX += 10;
		}if(controller.isRequestingLeft()) {
			deltaX -= 10;
		}
		position = new Position ( position.getX() + deltaX, position.getY() + deltaY);
	}

	@Override
	public Image getSprite() {
		
		BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB );
		Graphics2D graphics = image.createGraphics();
		
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0,0, size.getWidth(), size.getHeight());
		
		graphics.dispose();
		return image;
	}

}
