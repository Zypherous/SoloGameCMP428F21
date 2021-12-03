package entity;

import controller.Controller;
import display.Camera;
import game.state.State;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	private Rect rect;
	private Camera camera;
	public Player(Controller controller, SpriteLibrary spriteLibrary,Camera camera) {
		super(controller, spriteLibrary);
		//TESTING RECT
		this.camera = camera;
		this.rect = new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera);
	}
//	@Override
//	public void update(State state) {
//		super.update(state);
//		rect.setX((int)this.position.getX()- (int)camera.getPosition().getX());
//		rect.setY((int)this.position.getY()- (int)camera.getPosition().getY());
//	}



}
