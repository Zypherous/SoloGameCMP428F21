package entity;

import controller.Controller;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	private Rect rect;
	
	public Player(Controller controller, SpriteLibrary spriteLibrary) {
		super(controller, spriteLibrary);
		//TESTING RECT
		this.rect = new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0);
	}
	@Override
	public void update() {
		super.update();
		rect.setX((int)this.position.getX());
		rect.setY((int)this.position.getY());
	}



}
