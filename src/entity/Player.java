package entity;

import java.util.Comparator;
import java.util.Optional;

import controller.EntityController;
import core.Position;
import core.Size;
import core.Vector2D;
import entity.humanoid.Humanoid;
import entity.humanoid.action.BlowBubble;
import entity.humanoid.action.WalkInDirection;
import entity.humanoid.effect.Caffeinated;
import entity.humanoid.effect.Giant;
import entity.humanoid.effect.Isolated;
import game.Game;
import gfx.AnimationManager;
import gfx.SpriteLibrary;
import state.State;




public class Player extends Humanoid{

	private NPC target;
	private double targetRange;
	private SelectionCircle selectionCircle;
	
	public Player(EntityController controller, SpriteLibrary spriteLibrary, Size size) {
		super(controller, spriteLibrary);
		this.size = size;
		this.selectionCircle = new SelectionCircle();
		
		this.setPosition(new Position(1280/2,960 - Game.SPRITE_SIZE));
		perform(new WalkInDirection(new Vector2D(0, -1)));
		animationManager = new AnimationManager(spriteLibrary.getSpriteSheet("dave"));
		
		this.targetRange = Game.SPRITE_SIZE;
		//TESTING RECT
//		this.setRect(new Rect((int)this.position.getX(), (int) this.position.getY(), this.size.getWidth(), this.getSize().getHeight(),0,0, camera));
		effects.add(new Caffeinated());
		effects.add(new Giant());
	}
	@Override
	public void update(State state) {
		super.update(state);
		handleTarget(state);
		handleInput(state);
	}

	private void handleInput(State state) {
		if(entityController.isRequestingAction()) {
			if(target != null) {
				this.perform(new BlowBubble(target));
			}
		}
	}
	private void handleTarget(State state) {
        Optional<NPC> closestNPC = findClosestNPC(state);

        if(closestNPC.isPresent()) {
            NPC npc = closestNPC.get();
            if(!npc.equals(target)) {
                if(target != null) {
                    target.detach(selectionCircle);
                }
                npc.attach(selectionCircle);
                target = npc;
            }
        } else {
            if(target != null) {
                target.detach(selectionCircle);
                target = null;
            }
        }
    }
	private Optional<NPC> findClosestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .filter(npc -> !npc.isAffectedBy(Isolated.class))
                .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    }
	public void playerLoc() {
		System.out.println(String.format(
				"Player X: %d  Y: %d",
				(int)this.getPosition().getX(),
				(int)this.getPosition().getY()
				));
	}

	@Override
	public void handleCollision(GameObject other) {
		if(other instanceof Scenery && !((Scenery)other).isWalkable()) {
            movement.stop(willCollideX(other.getCollisionBox()), willCollideY(other.getCollisionBox()));
        }
    
	}
		
}	
	
//	@Override
//	public Image getSprite() {
//		return animationManager.getSprite(size);
//	}
