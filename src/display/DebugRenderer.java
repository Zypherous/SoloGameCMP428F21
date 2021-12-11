package display;

import core.CollisionBox;
import game.state.State;

import java.awt.*;

public class DebugRenderer {

    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .filter(gameObject -> camera.isInView(gameObject))
                .map(gameObject -> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollisionBox(collisionBox, graphics, camera));
    }

    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        collisionBox.getBounds().draw(graphics,Color.red, camera);
    }

}
