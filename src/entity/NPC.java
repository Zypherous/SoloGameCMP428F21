package entity;

import ai.AIManager;
import controller.EntityController;
import entity.humanoid.Humanoid;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends Humanoid {
    private AIManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getSpriteSheet("matt"));
        aiManager = new AIManager();
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player) {
            movement.stop(willCollideX(other), willCollideY(other));
        }
    }
    
    
}
