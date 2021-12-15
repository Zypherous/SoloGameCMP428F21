package entity;

import ai.AIManager;
import controller.EntityController;
import entity.humanoid.Humanoid;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

public class NPC extends Humanoid {
    private AIManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        
        aiManager = new AIManager();
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player || (other instanceof Scenery && !((Scenery)other).isWalkable())) {
            movement.stop(willCollideX(other), willCollideY(other));
        }
    }
    
    
}
