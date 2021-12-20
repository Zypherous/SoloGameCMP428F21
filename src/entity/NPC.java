package entity;

import ai.AIManager;
import controller.EntityController;
import entity.humanoid.Humanoid;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;

public class NPC extends Humanoid {
    private AIManager aiManager;
    private int health;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary, int health) {
        super(entityController, spriteLibrary);
        this.health = health;
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
            movement.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
        }
    }
    
    
}
