package display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.stream.Collectors;

import core.CollisionBox;
import entity.humanoid.Humanoid;
import state.State;
import ui.UIText;

public class DebugRenderer {

    public void render(State state, Graphics graphics) {
        drawEffects(state, graphics);
    }

    private void drawEffects(State state, Graphics graphics) {
    	Camera camera = state.getCamera();
    	state.getGameObjectsOfClass(Humanoid.class).stream()
    	.forEach(humanoid -> {
    		drawCollisionBox(humanoid.getCollisionBox(),graphics,camera);
    		UIText effectsText = new UIText(
    					humanoid.getEffects().stream().map(effect -> effect.getClass().getSimpleName()).collect(Collectors.joining(","))
    					);
    	effectsText.update(state);
    	graphics.drawImage(
    			effectsText.getSprite(),
    			(int)humanoid.getPosition().getX() - (int) camera.getPosition().getX(),
    			(int)humanoid.getPosition().getY() - (int) camera.getPosition().getX(),
    			null);
    	});
    }
    private void drawCollisionBox(CollisionBox collisionBox, Graphics graphics, Camera camera) {
        graphics.setColor(Color.red);
        graphics.drawRect(
                (int) collisionBox.getBounds().getX() - camera.getPosition().intX(),
                (int) collisionBox.getBounds().getY() - camera.getPosition().intY(),
                (int) collisionBox.getBounds().getW(),
                (int) collisionBox.getBounds().getH()
        );
    }

}
