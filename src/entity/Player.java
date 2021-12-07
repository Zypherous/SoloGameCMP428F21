package entity;

import controller.Controller;
import core.Position;
import display.Camera;
import game.state.State;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	
	public Player(Controller controller, SpriteLibrary spriteLibrary, Camera camera) {
		super(controller, spriteLibrary, camera);
		this.setPosition(new Position(1080/2,720/2));
		//TESTING RECT
		this.setRect(new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera));
	}
//	@Override
//	public void update(State state) {
////		super.update(state);
////		rect.setX((int)this.position.getX()- (int)camera.getPosition().getX());
////		rect.setY((int)this.position.getY()- (int)camera.getPosition().getY());
//		
//	}

	public void playerLoc() {
		
		System.out.println(String.format("Player x: %d y:%d", 
				(int)this.getPosition().getX(), 
				(int)this.getPosition().getY()));
	}


}
