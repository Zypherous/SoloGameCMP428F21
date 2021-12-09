package display;

import java.awt.Color;
import java.awt.Graphics;

import core.CollisionBox;
import game.state.State;

public class DebugRenderer {

	public void render(State state, Graphics graphics) {
		
		Camera camera = state.getCamera();

		state.getGameObjects().stream()
				.filter(gameObject -> camera.isInView(gameObject))
				.map(gameObject-> gameObject.getCurrentCollider())
				.forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
		
	}
	
	private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
		graphics.setColor(Color.RED);
		graphics.drawRect(
					(int)(collisionBox.getBounds().getX() - camera.getPosition().getX()),
					(int)(collisionBox.getBounds().getY() - camera.getPosition().getY()),
					(int)collisionBox.getBounds().getW(),
					(int)collisionBox.getBounds().getH()				
				);
	}
	
}
