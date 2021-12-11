package entity;

import controller.NPCController;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSheet;

public class Bubble extends MovingEntity {

    private NPCController controller;

    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);
        this.controller = npcController;

        this.animationManager = new AnimationManager(new SpriteSheet(spriteLibrary.getImage("bubble")), false);
    }

    @Override
    protected void handleCollision(GameObject other) {}

    @Override
    protected void handleMovement() {
        movement.update(controller);
    }

    @Override
    protected String decideAnimation() {
        return "default";
    }

    public void insert(GameObject gameObject) {
        this.position = gameObject.getPosition();
        this.renderOffset = gameObject.getRenderOffset();
        gameObject.setParent(this);
    }
}
