package entity;

import ai.AIManager;
import controller.Controller;
import display.Camera;
import entity.action.Cough;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends MovingEntity {
	private AIManager aiManager;
	
    public NPC(Controller controller, SpriteLibrary spriteLibrary, Camera camera) {
        super(controller, spriteLibrary, camera);
        this.setRect(new Rect(
        		(int)this.getPosition().getX(),
        		(int)this.getPosition().getY(),
        		(int)this.getSize().getWidth(),
        		(int)this.getSize().getHeight()));
        animationManager = new AnimationManager(spriteLibrary.getUnit("matt"));
        aiManager = new AIManager();
    }
    @Override
    public void update(State state) {
    	super.update(state);
    	
    	aiManager.update(state, this);
    }
}
