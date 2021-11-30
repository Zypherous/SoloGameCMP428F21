package entity;
import java.awt.Image;
import java.awt.image.BufferedImage;

import controller.Controller;
import core.Position;

import java.awt.Graphics2D;
import java.awt.Color;


public class Player extends MovingEntity{

	
	public Player(Controller controller) {
		super(controller);
		
	}
	@Override
	public void update() {
		super.update();
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
