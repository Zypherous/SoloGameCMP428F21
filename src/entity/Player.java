package entity;

import controller.Controller;
import core.Position;
import core.Size;
import display.Camera;
import entity.effect.Caffeinated;
import entity.effect.Giant;
import gfx.SpriteLibrary;




public class Player extends MovingEntity{

	
	public Player(Controller controller, SpriteLibrary spriteLibrary, Camera camera, Size size) {
		super(controller, spriteLibrary, camera);
		this.size = size;
		this.setPosition(new Position(1280/2,720/2));
		//TESTING RECT
		this.setRect(new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera));
		effects.add(new Caffeinated());
		effects.add(new Giant());
	}
//	@Override
//	public void update(State state) {
////		super.update(state);
////		rect.setX((int)this.position.getX()- (int)camera.getPosition().getX());
////		rect.setY((int)this.position.getY()- (int)camera.getPosition().getY());
//		
//	}

	public void playerLoc() {
		
		System.out.println(String.format("Player.java Line 36 ==> Player x: %d y:%d", 
				(int)this.getPosition().getX(), 
				(int)this.getPosition().getY()));
	}

	@Override
	public void handleCollision(GameObject other) {
		if(other instanceof NPC) {
//			this.movement.stop();
			NPC npc = (NPC) other;
			npc.clearEffect();
		}
		
	}	
	
//	@Override
//	public Image getSprite() {
//		return animationManager.getSprite(size);
//	}

}
