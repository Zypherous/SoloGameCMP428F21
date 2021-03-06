package entity;

import controller.NPCController;
import core.CollisionBox;
import core.Direction;
import core.Position;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSheet;
import state.State;

public class Bubble extends MovingEntity {

    private NPCController controller;
    private boolean halted;

    public Bubble(NPCController npcController, SpriteLibrary spriteLibrary) {
        super(npcController, spriteLibrary);
        this.controller = npcController;

        this.animationManager = new AnimationManager(new SpriteSheet(spriteLibrary.getImage("bubble")), false, 20);
        renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
        collisionBoxOffset = renderOffset;
    }

    @Override
    protected void handleCollision(GameObject other) {}

    @Override
    protected void handleMovement() {
    	if(!halted) {
    		movement.add(new Vector2D(0,-0.5 ));
    		// Since only one row in the sprite sheet
    	}
    	setHalted(false);
    	direction = Direction.S;
    }

    @Override
    protected String decideAnimation() {
        return "default";
    }

    public void insert(GameObject gameObject) {
        this.position = gameObject.getPosition();
        this.renderOffset = gameObject.getRenderOffset();
        this.collisionBoxOffset = this.renderOffset;
        gameObject.parent(this);
    }

	public boolean isHalted() {
		return halted;
	}

	public void setHalted(boolean halted) {
		this.halted = halted;
	}

	@Override
	protected void handleCollisions(State state) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void handleTileCollision(CollisionBox collisionBox) {
		// TODO Auto-generated method stub
		
	}
}
