package entity;

import controller.Controller;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	public Player(Controller controller, SpriteLibrary spriteLibrary) {
		super(controller, spriteLibrary);
		this.rect = new Rect((int)this.position.getX(),
				(int)this.position.getY(),
				64, 
				64,
				0,
				0,
				this.getPosition()
				);
		
	}
	@Override
	public void update() {
		super.update();
		this.rect.setX((int)this.position.getX());
		this.rect.setY((int)this.position.getY());
	}



}
