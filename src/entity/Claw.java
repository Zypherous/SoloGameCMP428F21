package entity;

import controller.NPCController;
import core.CollisionBox;
import core.Movement;
import core.Position;
import core.Vector2D;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import gfx.SpriteSheet;
import state.State;

public class Claw extends MovingEntity{
	 	private NPCController controller;
	 	private Player player;
	    private boolean halted;

	    public Claw(NPCController npcController, SpriteLibrary spriteLibrary) {
	        super(npcController, spriteLibrary);
	        this.controller = npcController;
//	        this.player = this.parent;

	        this.animationManager = new AnimationManager(new SpriteSheet(spriteLibrary.getImage("claw")), false);
	        renderOffset = new Position(size.getWidth() / 2, size.getHeight() - 12);
	        collisionBoxOffset = renderOffset;
	    }

	    @Override
	    protected void handleCollision(GameObject other) {
	    	if(this.collidesWith(other) && other instanceof NPC){
	    		Movement movement = new Movement(2);
	    		movement.add(new Vector2D(3,3));
	    		((NPC)other).apply(movement);
	    	}
	    }

	    @Override
	    protected void handleMovement() {
//	    	if(!halted) {
//	    		movement.add(new Vector2D(0,-0.5 ));
//	    		// Since only one row in the sprite sheet
//	    	}
//	    	setHalted(false);
//	    	direction = Direction.S;
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
		protected void handleTileCollision(CollisionBox collisionBox) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void handleCollisions(State state) {
			// TODO Auto-generated method stub
			
		}

}
